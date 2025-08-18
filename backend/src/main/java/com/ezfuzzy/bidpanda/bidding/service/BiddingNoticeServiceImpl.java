package com.ezfuzzy.bidpanda.bidding.service;

import com.ezfuzzy.bidpanda.bidding.dto.BiddingNoticeDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingResultDto;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingNotice;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingResult;
import com.ezfuzzy.bidpanda.bidding.repository.BiddingNoticeRepository;
import com.ezfuzzy.bidpanda.common.mapper.BiddingNoticeMapper;
import com.ezfuzzy.bidpanda.common.mapper.BiddingResultMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BiddingNoticeService 인터페이스의 구현체입니다.
 * 입찰 공고에 대한 핵심 비즈니스 로직을 담당합니다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BiddingNoticeServiceImpl implements BiddingNoticeService {

    private final BiddingNoticeRepository biddingNoticeRepository;
    private final BiddingNoticeMapper biddingNoticeMapper;
    private final BiddingResultMapper biddingResultMapper;

    /**
     * 새로운 입찰 공고를 생성합니다.
     *
     * @param biddingNoticeDto 생성할 입찰 공고의 데이터
     * @return 생성된 입찰 공고 정보
     */
    @Override
    public BiddingNoticeDto createBiddingNotice(BiddingNoticeDto biddingNoticeDto) {

        // 공고 번호와 차수로 중복 체크
        if (biddingNoticeRepository.existsByBidNtceNoAndBidNtceOrd(biddingNoticeDto.getBidNtceNo(), biddingNoticeDto.getBidNtceOrd())) {
            throw new IllegalStateException("Bidding notice with same number and order already exists.");
        }

        // 입찰 공고 생성 시에는 결과 정보를 포함하지 않음
        biddingNoticeDto.setBiddingResult(null);

        // DTO를 Entity로 변환
        BiddingNotice biddingNotice = biddingNoticeMapper.toEntity(biddingNoticeDto);

        // 연관관계 설정 (자식 엔티티에 부모 엔티티 참조 설정)
        setupRelationships(biddingNotice);

        // 데이터베이스에 저장
        BiddingNotice savedBiddingNotice = biddingNoticeRepository.save(biddingNotice);

        // 저장된 Entity를 DTO로 변환하여 반환
        return biddingNoticeMapper.toDto(savedBiddingNotice);
    }

    /**
     * 여러 개의 입찰 공고를 한 번에 생성합니다.
     *
     * @param biddingNoticeDtos 생성할 입찰 공고들의 데이터 전송 객체 (DTO) 리스트
     * @return 생성된 입찰 공고들의 DTO 리스트
     * @throws IllegalStateException 중복된 입찰 공고 번호와 차수가 존재하는 경우
     */
    @Override
    public List<BiddingNoticeDto> createBiddingNotices(List<BiddingNoticeDto> biddingNoticeDtos) {
        List<BiddingNoticeDto> validBiddingNoticeDtos = new ArrayList<>();

        for (BiddingNoticeDto biddingNoticeDto : biddingNoticeDtos) {
            log.error("??? : {}", biddingNoticeDto.getIndstrytyCd() + biddingNoticeDto.getIndstrytyNm());
            // 중복 체크: 이미 존재하는 입찰 공고는 건너뛰기
            if (biddingNoticeRepository.existsByBidNtceNoAndBidNtceOrd(biddingNoticeDto.getBidNtceNo(), biddingNoticeDto.getBidNtceOrd())) {
                log.error("Bidding notice with same number and order already exists. {}", biddingNoticeDto.getBidNtceNm());
                continue; // 중복 공고는 건너뛰기
            }

            // 유효한 공고에 대해서만 처리
            biddingNoticeDto.setBiddingResult(null);  // 입찰 결과는 포함되지 않음
            validBiddingNoticeDtos.add(biddingNoticeDto);
        }



        // 유효한 입찰 공고만 Entity로 변환 후 저장
        List<BiddingNotice> biddingNotices = validBiddingNoticeDtos.stream()
                .map(biddingNoticeMapper::toEntity)
                .collect(Collectors.toList());

        // 연관 관계 설정
        biddingNotices.forEach(this::setupRelationships);

        // 데이터베이스에 일괄 저장
        List<BiddingNotice> savedBiddingNotices = biddingNoticeRepository.saveAll(biddingNotices);

        // 저장된 Entity들을 DTO로 변환하여 반환
        return savedBiddingNotices.stream()
                .map(biddingNoticeMapper::toDto)
                .collect(Collectors.toList());
    }


    /**
     * ID로 특정 입찰 공고를 조회합니다.
     *
     * @param id 조회할 입찰 공고의 ID
     * @return 조회된 입찰 공고 정보
     */
    @Override
    @Transactional(readOnly = true)
    public BiddingNoticeDto getBiddingNoticeById(Long id) {
        log.info("Fetching bidding notice with id: {}", id);

        // 모든 연관관계를 포함하여 엔티티 조회 (N+1 문제 방지)
        BiddingNotice biddingNotice = biddingNoticeRepository.findByIdWithAllRelationsWithoutResult(id)
                .orElseThrow(() -> new EntityNotFoundException("BiddingNotice not found with id: " + id));

        // Entity를 DTO로 변환하여 반환
        return biddingNoticeMapper.toDto(biddingNotice);
    }

    /**
     * 모든 입찰 공고를 페이지 단위로 조회합니다.
     *
     * @param pageable 페이징 정보
     * @return 페이징된 입찰 공고 목록
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BiddingNoticeDto> getAllBiddingNotices(Pageable pageable) {
        log.info("Fetching all bidding notices for page: {}", pageable.getPageNumber());

        // 모든 입찰 공고를 페이징하여 조회
        return biddingNoticeRepository.findAll(pageable).map(biddingNoticeMapper::toDto);
    }

    /**
     * 기존 입찰 공고를 수정합니다.
     *
     * @param id 수정할 입찰 공고의 ID
     * @param biddingNoticeDto 수정할 내용
     * @return 수정된 입찰 공고 정보
     */
    @Override
    public BiddingNoticeDto updateBiddingNotice(Long id, BiddingNoticeDto biddingNoticeDto) {
        log.info("Updating bidding notice with id: {}", id);

        // ID로 기존 엔티티 조회
        BiddingNotice existingBiddingNotice = biddingNoticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BiddingNotice not found with id: " + id));

        // DTO의 내용으로 엔티티 업데이트
        biddingNoticeMapper.updateEntityFromDto(biddingNoticeDto, existingBiddingNotice);

        // 연관관계 재설정
        setupRelationships(existingBiddingNotice);

        // 변경된 엔티티 저장
        BiddingNotice updatedBiddingNotice = biddingNoticeRepository.save(existingBiddingNotice);

        // 업데이트된 Entity를 DTO로 변환하여 반환
        return biddingNoticeMapper.toDto(updatedBiddingNotice);
    }

    /**
     * ID로 특정 입찰 공고를 삭제합니다.
     *
     * @param id 삭제할 입찰 공고의 ID
     */
    @Override
    public void deleteBiddingNotice(Long id) {
        log.info("Deleting bidding notice with id: {}", id);

        // 삭제할 엔티티가 존재하는지 확인
        if (!biddingNoticeRepository.existsById(id)) {
            throw new EntityNotFoundException("BiddingNotice not found with id: " + id);
        }

        // ID로 엔티티 삭제
        biddingNoticeRepository.deleteById(id);
    }

    /**
     * 특정 입찰 공고에 대한 결과를 등록합니다.
     *
     * @param noticeId 입찰 공고의 ID
     * @param resultDto 등록할 입찰 결과 데이터
     * @return 수정된 입찰 공고 정보
     */
    @Override
    public BiddingNoticeDto addBiddingResult(Long noticeId, BiddingResultDto resultDto) {
        log.info("Adding bidding result to notice with id: {}", noticeId);

        // 입찰 공고 엔티티 조회
        BiddingNotice biddingNotice = biddingNoticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("BiddingNotice not found with id: " + noticeId));

        // 이미 입찰 결과가 등록되어 있는지 확인
        if (biddingNotice.getBiddingResult() != null) {
            throw new IllegalStateException("Bidding result already exists for this notice.");
        }

        // DTO를 Entity로 변환
        BiddingResult biddingResult = biddingResultMapper.toEntity(resultDto);

        // 연관관계 설정
        biddingNotice.setBiddingResult(biddingResult);
        biddingResult.setBiddingNotice(biddingNotice);

        // 변경된 입찰 공고 저장 (Cascade 설정으로 결과도 함께 저장됨)
        BiddingNotice updatedBiddingNotice = biddingNoticeRepository.save(biddingNotice);

        return biddingNoticeMapper.toDto(updatedBiddingNotice);
    }

    /**
     * 자식 엔티티에 부모 엔티티(BiddingNotice)의 참조를 설정합니다.
     * 양방향 연관관계의 무결성을 유지하기 위해 필요합니다.
     *
     * @param biddingNotice 부모 엔티티
     */
    private void setupRelationships(BiddingNotice biddingNotice) {
        if (biddingNotice.getAgencyInfo() != null) {
            biddingNotice.getAgencyInfo().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getApplicationCondition() != null) {
            biddingNotice.getApplicationCondition().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getBudgetInfo() != null) {
            biddingNotice.getBudgetInfo().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getCategoryInfo() != null) {
            biddingNotice.getCategoryInfo().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getChangeHistory() != null) {
            biddingNotice.getChangeHistory().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getCondition() != null) {
            biddingNotice.getCondition().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getDocuments() != null) {
            biddingNotice.getDocuments().forEach(doc -> doc.setBiddingNotice(biddingNotice));
        }
        if (biddingNotice.getPersonInCharge() != null) {
            biddingNotice.getPersonInCharge().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getBiddingResult() != null) {
            biddingNotice.getBiddingResult().setBiddingNotice(biddingNotice);
        }
        if (biddingNotice.getSchedule() != null) {
            biddingNotice.getSchedule().setBiddingNotice(biddingNotice);
        }
    }

    // 모든 데이터를 삭제하는 메서드
    @Override
    public void deleteAllBiddingNotices() {
        biddingNoticeRepository.deleteAll();
    }

}

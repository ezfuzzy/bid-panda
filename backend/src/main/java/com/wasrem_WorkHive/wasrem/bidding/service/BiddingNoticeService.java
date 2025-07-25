package com.wasrem_WorkHive.wasrem.bidding.service;

import com.wasrem_WorkHive.wasrem.bidding.dto.BiddingNoticeDto;
import com.wasrem_WorkHive.wasrem.bidding.dto.BiddingResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 입찰 공고에 대한 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface BiddingNoticeService {

    /**
     * 새로운 입찰 공고를 생성합니다.
     *
     * @param biddingNoticeDto 생성할 입찰 공고의 데이터
     * @return 생성된 입찰 공고 정보
     */
    BiddingNoticeDto createBiddingNotice(BiddingNoticeDto biddingNoticeDto);

    /**
     * ID로 특정 입찰 공고를 조회합니다.
     *
     * @param id 조회할 입찰 공고의 ID
     * @return 조회된 입찰 공고 정보
     */
    BiddingNoticeDto getBiddingNoticeById(Long id);

    /**
     * 모든 입찰 공고를 페이지 단위로 조회합니다.
     *
     * @param pageable 페이징 정보
     * @return 페이징된 입찰 공고 목록
     */
    Page<BiddingNoticeDto> getAllBiddingNotices(Pageable pageable);

    /**
     * 기존 입찰 공고를 수정합니다.
     *
     * @param id 수정할 입찰 공고의 ID
     * @param biddingNoticeDto 수정할 내용
     * @return 수정된 입찰 공고 정보
     */
    BiddingNoticeDto updateBiddingNotice(Long id, BiddingNoticeDto biddingNoticeDto);

    /**
     * ID로 특정 입찰 공고를 삭제합니다.
     *
     * @param id 삭제할 입찰 공고의 ID
     */
    void deleteBiddingNotice(Long id);

    /**
     * 특정 입찰 공고에 대한 결과를 등록합니다.
     *
     * @param noticeId 입찰 공고의 ID
     * @param resultDto 등록할 입찰 결과 데이터
     * @return 수정된 입찰 공고 정보
     */
    BiddingNoticeDto addBiddingResult(Long noticeId, BiddingResultDto resultDto);
}

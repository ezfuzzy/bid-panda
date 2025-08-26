package com.ezfuzzy.bidpanda.bidding.controller;

import com.ezfuzzy.bidpanda.bidding.dto.BiddingNoticeDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingResultDto;
import com.ezfuzzy.bidpanda.bidding.scheduler.BiddingNoticeScheduler;
import com.ezfuzzy.bidpanda.bidding.service.BiddingNoticeService;
import com.ezfuzzy.bidpanda.bidding.service.G2BApiService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 입찰 공고에 대한 RESTful API를 제공하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/bidding-notices")
@RequiredArgsConstructor
@Slf4j
public class BiddingNoticeController {

    private final BiddingNoticeService biddingNoticeService;
    private final G2BApiService g2bApiService;

    /**
     *   test codes
     *
     */

    private final BiddingNoticeScheduler scheduler;


    @PostMapping("/collect")
    public ResponseEntity<String> manualCollect() {
        try {
//            scheduler.manualDataCollection();
            return ResponseEntity.ok("데이터 수집이 시작되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("데이터 수집 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test(HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String referer = request.getHeader("Referer");

        log.info("Client IP: {}", clientIp);
        log.info("User-Agent: {}", userAgent);
        log.info("Referer: {}", referer);

        return ResponseEntity.ok(Map.of("msg", "good", "clientIp", clientIp, "userAgent", userAgent));
    }

    @GetMapping("/g2b/test")
    public ResponseEntity<Map<String, String>> backendFetchTest(HttpServletRequest request) {

        String str = g2bApiService.fetchBiddingNotices();

        return ResponseEntity.ok(Map.of("msg", str));
    }



    // ==================================================================================

    /**
     * 새로운 입찰 공고를 생성합니다.
     *
     * @param biddingNoticeDto 생성할 입찰 공고의 데이터 전송 객체 (DTO)
     * @return 생성된 입찰 공고 정보와 HTTP 상태 코드 201 (Created)
     */
    @PostMapping
    public ResponseEntity<BiddingNoticeDto> createBiddingNotice(@RequestBody BiddingNoticeDto biddingNoticeDto) {
        // 서비스 레이어에 입찰 공고 생성을 위임합니다.
        BiddingNoticeDto createdBiddingNotice = biddingNoticeService.createBiddingNotice(biddingNoticeDto);
        // 생성된 리소스와 함께 201 Created 상태를 반환합니다.
        return new ResponseEntity<>(createdBiddingNotice, HttpStatus.CREATED);
    }

    /**
     * 여러 개의 입찰 공고를 한 번에 생성합니다.
     *
     * @param biddingNoticeDtos 생성할 입찰 공고들의 데이터 전송 객체 (DTO) 리스트
     * @return 생성된 입찰 공고들의 정보와 HTTP 상태 코드 201 (Created)
     */
    @PostMapping("/batch")
    public ResponseEntity<List<BiddingNoticeDto>> createBiddingNotices(@RequestBody List<BiddingNoticeDto> biddingNoticeDtos) {
        // 서비스 레이어에 입찰 공고 배치를 위임
        List<BiddingNoticeDto> createdBiddingNotices = biddingNoticeService.createBiddingNotices(biddingNoticeDtos);
        // 생성된 리소스와 함께 201 Created 상태 반환
        return new ResponseEntity<>(createdBiddingNotices, HttpStatus.CREATED);
    }

    /**
     * ID를 사용하여 특정 입찰 공고를 조회합니다.
     *
     * @param id 조회할 입찰 공고의 ID
     * @return 조회된 입찰 공고 정보와 HTTP 상태 코드 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<BiddingNoticeDto> getBiddingNoticeById(@PathVariable Long id) {
        // 서비스 레이어에서 ID로 입찰 공고를 조회합니다.
        BiddingNoticeDto biddingNoticeDto = biddingNoticeService.getBiddingNoticeById(id);
        // 조회된 정보와 함께 200 OK 상태를 반환합니다.
        return ResponseEntity.ok(biddingNoticeDto);
    }

    /**
     * 모든 입찰 공고를 페이지네이션하여 조회합니다.
     *
     * @param pageable 페이지네이션 정보 (페이지 번호, 페이지 크기 등)
     * @return 페이징 처리된 입찰 공고 목록과 HTTP 상태 코드 200 (OK)
     */
    @GetMapping
    public ResponseEntity<Page<BiddingNoticeDto>> getAllBiddingNotices(Pageable pageable) {
        // 서비스 레이어에서 모든 입찰 공고를 페이징하여 조회합니다.
        Page<BiddingNoticeDto> biddingNotices = biddingNoticeService.getAllBiddingNotices(pageable);
        // 조회된 목록과 함께 200 OK 상태를 반환합니다.
        return ResponseEntity.ok(biddingNotices);
    }

    /**
     * 기존 입찰 공고를 수정합니다.
     *
     * @param id 수정할 입찰 공고의 ID
     * @param biddingNoticeDto 수정할 입찰 공고의 데이터
     * @return 수정된 입찰 공고 정보와 HTTP 상태 코드 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<BiddingNoticeDto> updateBiddingNotice(@PathVariable Long id, @RequestBody BiddingNoticeDto biddingNoticeDto) {
        // 서비스 레이어에 입찰 공고 수정을 위임합니다.
        BiddingNoticeDto updatedBiddingNotice = biddingNoticeService.updateBiddingNotice(id, biddingNoticeDto);
        // 수정된 정보와 함께 200 OK 상태를 반환합니다.
        return ResponseEntity.ok(updatedBiddingNotice);
    }

    /**
     * ID를 사용하여 특정 입찰 공고를 삭제합니다.
     *
     * @param id 삭제할 입찰 공고의 ID
     * @return 내용 없이 HTTP 상태 코드 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiddingNotice(@PathVariable Long id) {
        // 서비스 레이어에 입찰 공고 삭제를 위임합니다.
        biddingNoticeService.deleteBiddingNotice(id);
        // 내용이 없음을 의미하는 204 No Content 상태를 반환합니다.
        return ResponseEntity.noContent().build();
    }

    /**
     * 특정 입찰 공고에 대한 결과를 등록합니다.
     *
     * @param id 입찰 공고의 ID
     * @param resultDto 등록할 입찰 결과 데이터
     * @return 수정된 입찰 공고 정보와 HTTP 상태 코드 200 (OK)
     */
    @PostMapping("/{id}/result")
    public ResponseEntity<BiddingNoticeDto> addBiddingResult(@PathVariable Long id, @RequestBody BiddingResultDto resultDto) {
        BiddingNoticeDto updatedBiddingNotice = biddingNoticeService.addBiddingResult(id, resultDto);
        return ResponseEntity.ok(updatedBiddingNotice);
    }

    /**
     * 특정 입찰 공고에 대한 결과를 등록합니다. with bidNtceNo
     *
     * @param resultDto 등록할 입찰 결과 데이터
     * @return 수정된 입찰 공고 정보와 HTTP 상태 코드 200 (OK)
     */
    @PostMapping("/by-bid-no/result")
    public ResponseEntity<BiddingNoticeDto> addBiddingResult(@RequestBody BiddingResultDto resultDto) {
        BiddingNoticeDto updatedBiddingNotice = biddingNoticeService.addBiddingResult(resultDto);
        return ResponseEntity.ok(updatedBiddingNotice);
    }

    /**
     *  admin
     */
    @DeleteMapping("/admin/delete-all")
    public ResponseEntity<String> deleteAllBiddingNotices() {
        try {
            biddingNoticeService.deleteAllBiddingNotices();
            return ResponseEntity.ok("All bidding notices have been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the data.");
        }
    }
}
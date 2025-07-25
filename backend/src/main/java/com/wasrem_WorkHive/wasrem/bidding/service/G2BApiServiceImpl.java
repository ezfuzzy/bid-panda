package com.wasrem_WorkHive.wasrem.bidding.service;

import com.wasrem_WorkHive.wasrem.common.util.BiddingNoticeParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public class G2BApiServiceImpl implements G2BApiService{

    @Value("${g2b.api.service-key}")
    private String serviceKey;

    @Value("${g2b.api.service-key-en}")
    private String serviceKey_en;

    @Value("${g2b.api.bidding-notice.url}")
    private String biddingNoticeUrl;

    @Autowired
    private RestTemplate restTemplate;

//    private final BiddingNoticePaer biddingNoticePaer;

    @Override
    public String fetchBiddingNotices() {
        // 1. basic
        basicApiCall();
        basicApiCall2();

        return "str";
    }


    private void basicApiCall() {
        try {
            log.info("=== 1. 기본 API 호출 ===");

            // URL 직접 구성
            String url = "https://apis.data.go.kr/1230000/ad/BidPublicInfoService/getBidPblancListInfoServcPPSSrch" +
                    "?inqryDiv=2" +
                    "&pageNo=1" +
                    "&numOfRows=5" +
                    "&inqryBgnDt=202507250000" +
                    "&inqryEndDt=202508242359" +
                    "&ServiceKey=" + serviceKey+
                    "&type=json";

            log.info("요청 URL: {}", url);

            // RestTemplate으로 호출
            String response = restTemplate.getForObject(url, String.class);

            log.info("\n기본 호출 응답: {}", response);
            processBiddingNoticeData(response);
        } catch (Exception e) {
            log.error("기본 API 호출 오류: {}", e.getMessage(), e);
        }
    }

    private void basicApiCall2() {
        try {
            log.info("=== 2. 기본 API 호출 ===");

            // URL 직접 구성
            String url = "https://apis.data.go.kr/1230000/ad/BidPublicInfoService/getBidPblancListInfoServcPPSSrch" +
                    "?inqryDiv=2" +
                    "&pageNo=1" +
                    "&numOfRows=5" +
                    "&inqryBgnDt=202507250000" +
                    "&inqryEndDt=202508242359" +
                    "&ServiceKey=" + serviceKey_en+
                    "&type=json";

            log.info("요청 URL: {}", url);

            // RestTemplate으로 호출
            String response = restTemplate.getForObject(url, String.class);

            log.info("\n기본 호출 응답: {}", response);
            processBiddingNoticeData(response);
        } catch (Exception e) {
            log.error("기본 API 호출 오류: {}", e.getMessage(), e);
        }
    }

    private void processBiddingNoticeData(String responseData) {
        log.info("입찰공고 응답 데이터 처리 시작");

        BiddingNoticeParser parser = new BiddingNoticeParser();
        parser.processBiddingNoticeData(responseData);

        log.info("입찰공고 응답 데이터 처리 완료");
    }

    @Override
    public void fetchAndSaveBiddingNotices() {

    }

    @Override
    public void fetchAndSaveBiddingNotices(LocalDateTime startDate, LocalDateTime endDate) {

    }

    @Override
    public boolean isApiHealthy() {
//        try {
//            LocalDate today = LocalDate.now();
//            G2BApiResponse response = fetchBiddingNotices(1, 1, today, today);
//            return response != null && response.getResponse().getHeader().getResultCode().equals("00");
//        } catch (Exception e) {
//            log.error("API 상태 확인 중 오류 발생", e);
//            return false;
//        }
        return false;
    }
}

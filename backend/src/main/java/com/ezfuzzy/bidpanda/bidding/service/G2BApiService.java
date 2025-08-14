package com.ezfuzzy.bidpanda.bidding.service;

import java.time.LocalDateTime;

public interface G2BApiService {


    /**
     * 나라장터 API에서 입찰공고 데이터를 수집하고 저장
     */
    String fetchBiddingNotices();

    /**
     * 나라장터 API에서 입찰공고 데이터를 수집하고 저장
     */
    void fetchAndSaveBiddingNotices();

    /**
     * 특정 날짜 범위의 입찰공고 데이터를 수집하고 저장
     * @param startDate 조회 시작일
     * @param endDate 조회 종료일
     */
    void fetchAndSaveBiddingNotices(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 나라장터 API 상태 확인
     * @return API 연결 상태
     */
    boolean isApiHealthy();
}

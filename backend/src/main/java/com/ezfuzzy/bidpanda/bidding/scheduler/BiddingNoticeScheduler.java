package com.ezfuzzy.bidpanda.bidding.scheduler;

import com.ezfuzzy.bidpanda.bidding.service.G2BApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class BiddingNoticeScheduler {

    private final G2BApiService g2BApiService;


}

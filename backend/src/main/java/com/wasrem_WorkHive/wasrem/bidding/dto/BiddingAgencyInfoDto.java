package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingAgencyInfoDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private String ntceInsttCd; // 공고기관코드
    private String ntceInsttNm; // 공고기관명
    private String dminsttCd;   // 수요기관코드
    private String dminsttNm;   // 수요기관명
}

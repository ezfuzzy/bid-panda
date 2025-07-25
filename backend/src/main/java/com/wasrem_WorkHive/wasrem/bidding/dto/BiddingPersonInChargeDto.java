package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingPersonInChargeDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private String ntceInsttOfclNm;        // 공고기관 담당자명
    private String ntceInsttOfclTelNo;     // 전화번호
    private String ntceInsttOfclEmailAdrs; // 이메일
    private String dminsttOfclEmailAdrs;   // 수요기관 담당자 이메일
    private String exctvNm;                // 집행관명
}

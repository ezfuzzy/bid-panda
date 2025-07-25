package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingApplicationConditionDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private String pqApplDocRcptMthdNm;     // PQ 신청서 접수 방법명
    private String tpEvalApplMthdNm;        // TP 심사 신청 방법명
    private String rsrvtnPrceReMkngMthdNm;  // 예비가격 재작성 방법명
    private String arsltApplDocRcptMthdNm;  // 실적 신청서 접수 방법명
    private String cmmnSpldmdMethdCd;       // 공동수급방식코드
    private String cmmnSpldmdMethdNm;       // 공동수급방식명
    private String jntcontrctDutyRgnNm1;    // 공동도급의무지역명1
    private String jntcontrctDutyRgnNm2;    // 공동도급의무지역명2
    private String jntcontrctDutyRgnNm3;    // 공동도급의무지역명3
    private BigDecimal rgnDutyJntcontrctRt; // 지역의무공동도급비율
    private BigDecimal sucsfbidLwltRate;    // 낙찰하한율
    private String sucsfbidMthdCd;          // 낙찰방법코드
    private String sucsfbidMthdNm;          // 낙찰방법명
}

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
public class BiddingBudgetInfoDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private BigDecimal asignBdgtAmt;         // 배정예산금액
    private BigDecimal presmptPrce;          // 추정가격
    private String prearngPrceDcsnMthdNm;     // 예정가격결정방법명
    private Integer totPrdprcNum;            // 총예가건수
    private Integer drwtPrdprcNum;           // 추첨예가건수
    private BigDecimal bidPrtcptFee;         // 입찰참가수수료
    private BigDecimal VAT;                  // 부가가치세
    private BigDecimal indutyVAT;            // 주공종부가가치세
}

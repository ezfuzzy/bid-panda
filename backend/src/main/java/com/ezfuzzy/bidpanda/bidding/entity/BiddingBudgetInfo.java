package com.ezfuzzy.bidpanda.bidding.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BiddingBudgetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bidding_notice_id", unique = true)
    private BiddingNotice biddingNotice;

    private BigDecimal asignBdgtAmt;         // 배정예산금액
    private BigDecimal presmptPrce;          // 추정가격
    private String prearngPrceDcsnMthdNm;    // 예정가격결정방법명
    private Integer totPrdprcNum;            // 총예가건수
    private Integer drwtPrdprcNum;           // 추첨예가건수
    private BigDecimal bidPrtcptFee;         // 입찰참가수수료
    private BigDecimal VAT;                  // 부가가치세
    private BigDecimal indutyVAT;            // 주공종부가가치세
}

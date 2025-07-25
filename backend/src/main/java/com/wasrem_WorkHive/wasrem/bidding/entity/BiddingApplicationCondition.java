package com.wasrem_WorkHive.wasrem.bidding.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BiddingApplicationCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bidding_notice_id", unique = true)
    private BiddingNotice biddingNotice;

    private String pqApplDocRcptMthdNm;     // PQ 신청서 접수 방법명
    private String tpEvalApplMthdNm;        // TP 심사 신청 방법명
    private String rsrvtnPrceReMkngMthdNm;  // 예비가격 재작성 방법명
    private String arsltApplDocRcptMthdNm;  // 실적 신청서 접수 방법명
    private String cmmnSpldmdMethdCd;       // 공동수급방식코드
    private String cmmnSpldmdMethdNm;       // 공동수급방식명
    private String jntcontrctDutyRgnNm1;    // 공동도급의무지역명1
    private String jntcontrctDutyRgnNm2;    // 공동도급의무지역명2
    private String jntcontrctDutyRgnNm3;    // 공동도급의무지역명3

    @Column(precision = 10, scale = 3)
    private BigDecimal rgnDutyJntcontrctRt; // 지역의무공동도급비율

    @Column(precision = 10, scale = 3)
    private BigDecimal sucsfbidLwltRate;    // 낙찰하한율
    private String sucsfbidMthdCd;          // 낙찰방법코드
    private String sucsfbidMthdNm;          // 낙찰방법명
}

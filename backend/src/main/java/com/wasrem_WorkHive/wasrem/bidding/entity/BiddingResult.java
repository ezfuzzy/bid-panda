package com.wasrem_WorkHive.wasrem.bidding.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BiddingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bidding_notice_id", unique = true)
    private BiddingNotice biddingNotice;

    private String bidNtceNo;          // 입찰공고번호
    private String bidNtceOrd;         // 입찰공고차수
    private String bidClsfcNo;         // 입찰분류번호
    private String rbidNo;             // 재입찰번호
    private String ntceDivCd;          // 공고구분코드
    private String bidNtceNm;          // 입찰공고명
    private String prtcptCnum;         // 참가업체수
    private String bidwinnrNm;         // 최종낙찰업체명
    private String bidwinnrBizno;      // 최종낙찰업체사업자등록번호
    private String bidwinnrCeoNm;      // 최종낙찰업체대표자명
    private String bidwinnrAdrs;       // 최종낙찰업체주소
    private String bidwinnrTelNo;      // 최종낙찰업체전화번호
    private String sucsfbidAmt;        // 최종낙찰금액
    private String sucsfbidRate;       // 최종낙찰률
    private String rlOpengDt;          // 실개찰일시
    private String dminsttCd;          // 수요기관코드
    private String dminsttNm;          // 수요기관명
    private String rgstDt;             // 등록일시
    private String fnlSucsfDate;       // 최종낙찰일자
    private String fnlSucsfCorpOfcl;   // 최종낙찰업체담당자
}

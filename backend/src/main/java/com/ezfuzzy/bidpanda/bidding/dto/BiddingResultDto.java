package com.ezfuzzy.bidpanda.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingResultDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private String bidNtceNo;                       // 입찰공고번호
    private String bidNtceOrd;                      // 입찰공고차수
    private String bidClsfcNo;                      // 입찰분류번호
    private String rbidNo;                          // 재입찰번호

    private String bidNtceNm;                       // 입찰공고명
    private String opengDt;                         // 실개찰일시
    private String prtcptCnum;                      // 참가업체수

    private String bidwinnrNm;                      // 최종낙찰업체명
    private String bidwinnrBizno;                   // 최종낙찰업체사업자등록번호
    private String bidwinnrCeoNm;                   // 최종낙찰업체대표자명
    private String sucsfbidAmt;                     // 최종낙찰금액
    private String sucsfbidRate;                    // 최종낙찰률

    private String progrsDivCdNm;                   // 진행구분코드명
    private String inptDt;                          // 입력일시
    private String rsrvtnPrceFileExistnceYn;        // 예비가격 파일 존재 여부

    private String ntceInsttCd;                     // 공고기관코드
    private String ntceInsttNm;                     // 공고기관명
    private String dminsttCd;                       // 수요기관코드
    private String dminsttNm;                       // 수요기관명

    private String opengRsltNtcCntnts;              // 개찰결과 공지내용
}

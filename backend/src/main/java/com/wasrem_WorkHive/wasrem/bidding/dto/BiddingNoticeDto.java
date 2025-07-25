package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingNoticeDto {

    private Long id; //                Int     [pk, increment]

    private String bidNtceNo;   // 입찰공고번호
    private String bidNtceOrd;  // 입찰공고차수
    private String bidNtceNm;   // 입찰공고명
    private String ntceKindNm;  // 공고종류명
    private String rgstTyNm;    // 등록유형명

    private Boolean reNtceYn;   // 재공고 여부
    private Boolean intrbidYn;  // 국제입찰 여부

    private String bidMethdNm;         // 입찰방식명
    private String cntrctCnclsMthdNm;  // 계약체결방법명
    private String refNo;              // 참조번호
    private String untyNtceNo;         // 통합공고번호


    private String indstrytyCd;        //  업종 코드
    private String indstrytyNm;        // 업종명
    private String prtcptLmtRgnCd;     // 참가 가능 지역 코드
    private String prtcptPsblRgnNm;    // 참가 가능 지역명

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private BiddingAgencyInfoDto agencyInfo;
    private BiddingPersonInChargeDto personInCharge;
    private BiddingScheduleDto schedule;
    private List<BiddingDocumentInfoDto> documents;
    private BiddingConditionDto condition;
    private BiddingBudgetInfoDto budgetInfo;
    private BiddingCategoryInfoDto categoryInfo;
    private BiddingApplicationConditionDto applicationCondition;
    private BiddingChangeHistoryDto changeHistory;
    private BiddingResultDto biddingResult;
}

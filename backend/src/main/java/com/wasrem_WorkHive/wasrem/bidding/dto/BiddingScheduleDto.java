package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingScheduleDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private LocalDateTime bidNtceDt;           // 입찰공고일시
    private LocalDateTime bidBeginDt;          // 입찰개시일시
    private LocalDateTime bidClseDt;           // 입찰마감일시
    private LocalDateTime opengDt;             // 개찰일시
    private LocalDateTime bidQlfctRgstDt;      // 자격 등록 마감
    private LocalDateTime pqApplDocRcptDt;     // PQ 접수일
    private LocalDateTime tpEvalApplClseDt;    // TP 마감일
    private LocalDateTime arsltReqstdocRcptDt; // 실적 서류 마감
    private LocalDateTime dcmtgOprtnDt;        // 설명회 일시
    private LocalDateTime rbidOpengDt;         // 재입찰 개찰일시
    private LocalDateTime rgstDt;              // 등록일
    private LocalDateTime chgDt;               // 변경일
}

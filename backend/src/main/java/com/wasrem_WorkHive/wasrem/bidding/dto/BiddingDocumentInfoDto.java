package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingDocumentInfoDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private List<String> specDocUrls;    // 규격서 URL 목록 (배열로 저장)
    private List<String> specFileNames;  // 규격서 파일명 목록 (배열로 저장)

    private String stdNtceDocUrl;        // 표준 공고서 URL
    private String dcmtgOprtnPlce;       // 설명회 장소
    private String bidNtceDtlUrl;        // 상세 URL
    private String bidNtceUrl;           // 기본 URL
}

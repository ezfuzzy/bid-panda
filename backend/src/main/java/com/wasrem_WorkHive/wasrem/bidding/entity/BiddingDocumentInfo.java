package com.wasrem_WorkHive.wasrem.bidding.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BiddingDocumentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidding_notice_id")
    private BiddingNotice biddingNotice;

    @ElementCollection
    private List<String> specDocUrls;    // 규격서 URL 목록 (배열로 저장)

    @ElementCollection
    private List<String> specFileNames;  // 규격서 파일명 목록 (배열로 저장)

    private String stdNtceDocUrl;        // 표준 공고서 URL
    private String dcmtgOprtnPlce;       // 설명회 장소
    private String bidNtceDtlUrl;        // 상세 URL
    private String bidNtceUrl;           // 기본 URL
}

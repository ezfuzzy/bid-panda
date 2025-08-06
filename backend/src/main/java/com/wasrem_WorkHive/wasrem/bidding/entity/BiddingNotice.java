package com.wasrem_WorkHive.wasrem.bidding.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EnableJpaAuditing
public class BiddingNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //                Int     [pk, increment]

    @Column(unique = true, nullable = false)
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

    private String indstrytyCd;        // 업종 코드
    private String indstrytyNm;        // 업종명
    private String prtcptLmtRgnCd;     // 참가 가능 지역 코드
    private String prtcptPsblRgnNm;    // 참가 가능 지역명

    @Column(columnDefinition = "TEXT")
    private String summary;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingAgencyInfo agencyInfo;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingApplicationCondition applicationCondition;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingBudgetInfo budgetInfo;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingCategoryInfo categoryInfo;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingChangeHistory changeHistory;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingCondition condition;

    @OneToMany(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BiddingDocumentInfo> documents;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingPersonInCharge personInCharge;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingResult biddingResult;

    @OneToOne(mappedBy = "biddingNotice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BiddingSchedule schedule;

}

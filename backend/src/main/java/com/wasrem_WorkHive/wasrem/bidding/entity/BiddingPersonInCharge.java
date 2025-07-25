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
public class BiddingPersonInCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bidding_notice_id", unique = true)
    private BiddingNotice biddingNotice;

    private String ntceInsttOfclNm;        // 공고기관 담당자명
    private String ntceInsttOfclTelNo;     // 전화번호
    private String ntceInsttOfclEmailAdrs; // 이메일
    private String dminsttOfclEmailAdrs;   // 수요기관 담당자 이메일
    private String exctvNm;                // 집행관명
}

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
public class BiddingChangeHistoryDto {

    private Long id;
    private Long biddingNoticeId;      // BiddingNotice의 ID만 포함

    private LocalDateTime chgDt;       // 변경일시
    private String chgNtceRsn;         // 변경공고사유

}

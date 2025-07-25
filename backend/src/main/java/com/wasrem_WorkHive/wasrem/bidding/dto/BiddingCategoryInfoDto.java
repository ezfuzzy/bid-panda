package com.wasrem_WorkHive.wasrem.bidding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BiddingCategoryInfoDto {

    private Long id;
    private Long biddingNoticeId;           // BiddingNotice의 ID만 포함

    private Boolean ppswGnrlSrvceYn;        // 조달청일반용역여부
    private String srvceDivNm;              // 용역구분명
    private Boolean prdctClsfcLmtYn;        // 물품분류제한여부
    private Boolean mnfctYn;                // 제조여부
    private String purchsObjPrdctList;      // 구매대상물품목록
    private String pubPrcrmntLrgclsfcNm;    // 공공조달대분류명
    private String pubPrcrmntMidclsfcNm;    // 공공조달중분류명
    private String pubPrcrmntClsfcNo;       // 공공조달분류번호
    private String pubPrcrmntClsfcNm;       // 공공조달분류명
}

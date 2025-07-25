package com.wasrem_WorkHive.wasrem.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wasrem_WorkHive.wasrem.bidding.dto.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BiddingNoticeParser {
    public void processBiddingNoticeData(String responseData) {
        log.info("입찰공고 응답 데이터 처리 시작");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseData);

            JsonNode itemsNode = rootNode.path("response").path("body").path("items");

            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    BiddingNoticeDto dto = convertJsonToBiddingNoticeDto(itemNode);

                    // DTO 사용 로직 (예: 저장, 출력)

                    log.info("파싱된 입찰공고: {}", dto.getBidNtceNo());
                }
            }
        } catch (Exception e) {
            log.error("입찰공고 데이터 파싱 오류: {}", e.getMessage(), e);
        }

        log.info("입찰공고 응답 데이터 처리 완료");
    }

    private BiddingNoticeDto convertJsonToBiddingNoticeDto(JsonNode node) {
        BiddingNoticeDto dto = new BiddingNoticeDto();

        dto.setBidNtceNo(getText(node, "bidNtceNo"));
        dto.setBidNtceOrd(getText(node, "bidNtceOrd"));
        dto.setReNtceYn(toBoolean(node, "reNtceYn"));
        dto.setRgstTyNm(getText(node, "rgstTyNm"));
        dto.setNtceKindNm(getText(node, "ntceKindNm"));
        dto.setIntrbidYn(toBoolean(node, "intrbidYn"));
        dto.setBidNtceNm(getText(node, "bidNtceNm"));
        dto.setRefNo(getText(node, "refNo"));
        dto.setUntyNtceNo(getText(node, "untyNtceNo"));
        dto.setBidMethdNm(getText(node, "bidMethdNm"));
        dto.setCntrctCnclsMthdNm(getText(node, "cntrctCnclsMthdNm"));
        dto.setPrtcptPsblRgnNm(getText(node, "prtcptPsblRgnNm"));

        BiddingAgencyInfoDto agency = new BiddingAgencyInfoDto();
        agency.setNtceInsttCd(getText(node, "ntceInsttCd"));
        agency.setNtceInsttNm(getText(node, "ntceInsttNm"));
        agency.setDminsttCd(getText(node, "dminsttCd"));
        agency.setDminsttNm(getText(node, "dminsttNm"));
        dto.setAgencyInfo(agency);

        BiddingPersonInChargeDto person = new BiddingPersonInChargeDto();
        person.setNtceInsttOfclNm(getText(node, "ntceInsttOfclNm"));
        person.setNtceInsttOfclTelNo(getText(node, "ntceInsttOfclTelNo"));
        person.setNtceInsttOfclEmailAdrs(getText(node, "ntceInsttOfclEmailAdrs"));
        person.setExctvNm(getText(node, "exctvNm"));
        dto.setPersonInCharge(person);

        BiddingScheduleDto schedule = new BiddingScheduleDto();
        schedule.setBidNtceDt(toLocalDateTime(getText(node, "bidNtceDt")));
        schedule.setBidBeginDt(toLocalDateTime(getText(node, "bidBeginDt")));
        schedule.setBidClseDt(toLocalDateTime(getText(node, "bidClseDt")));
        schedule.setOpengDt(toLocalDateTime(getText(node, "opengDt")));
        schedule.setBidQlfctRgstDt(toLocalDateTime(getText(node, "bidQlfctRgstDt")));
        schedule.setPqApplDocRcptDt(toLocalDateTime(getText(node, "pqApplDocRcptDt")));
        schedule.setTpEvalApplClseDt(toLocalDateTime(getText(node, "tpEvalApplClseDt")));
        schedule.setArsltReqstdocRcptDt(toLocalDateTime(getText(node, "arsltReqstdocRcptDt")));
        schedule.setDcmtgOprtnDt(toLocalDateTime(getText(node, "dcmtgOprtnDt")));
        schedule.setRbidOpengDt(toLocalDateTime(getText(node, "rbidOpengDt")));
        schedule.setRgstDt(toLocalDateTime(getText(node, "rgstDt")));
        schedule.setChgDt(toLocalDateTime(getText(node, "chgDt")));
        dto.setSchedule(schedule);

        List<String> specDocUrls = new ArrayList<>();
        List<String> specFileNames = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String url = getText(node, "ntceSpecDocUrl" + i);
            String fileName = getText(node, "ntceSpecFileNm" + i);
            if (url != null) specDocUrls.add(url);
            if (fileName != null) specFileNames.add(fileName);
        }
        BiddingDocumentInfoDto doc = new BiddingDocumentInfoDto();
        doc.setSpecDocUrls(specDocUrls.isEmpty() ? null : specDocUrls);
        doc.setSpecFileNames(specFileNames.isEmpty() ? null : specFileNames);
        doc.setStdNtceDocUrl(getText(node, "stdNtceDocUrl"));
        doc.setDcmtgOprtnPlce(getText(node, "dcmtgOprtnPlce"));
        doc.setBidNtceDtlUrl(getText(node, "bidNtceDtlUrl"));
        doc.setBidNtceUrl(getText(node, "bidNtceUrl"));
        dto.setDocuments(List.of(doc));

        BiddingConditionDto condition = new BiddingConditionDto();
        condition.setCmmnSpldmdAgrmntRcptdocMethd(getText(node, "cmmnSpldmdAgrmntRcptdocMethd"));
        condition.setCmmnSpldmdAgrmntClseDt(toLocalDateTime(getText(node, "cmmnSpldmdAgrmntClseDt")));
        condition.setCmmnSpldmdCorpRgnLmtYn(toBoolean(node, "cmmnSpldmdCorpRgnLmtYn"));
        condition.setRbidPermsnYn(toBoolean(node, "rbidPermsnYn"));
        condition.setBidPrtcptLmtYn(toBoolean(node, "bidPrtcptLmtYn"));
        condition.setIndstrytyLmtYn(toBoolean(node, "indstrytyLmtYn"));
        condition.setDtlsBidYn(toBoolean(node, "dtlsBidYn"));
        condition.setBrffcBidprcPermsnYn(toBoolean(node, "brffcBidprcPermsnYn"));
        condition.setDsgntCmptYn(toBoolean(node, "dsgntCmptYn"));
        condition.setArsltCmptYn(toBoolean(node, "arsltCmptYn"));
        condition.setPqEvalYn(toBoolean(node, "pqEvalYn"));
        condition.setTpEvalYn(toBoolean(node, "tpEvalYn"));
        condition.setNtceDscrptYn(toBoolean(node, "ntceDscrptYn"));
//        condition.setRsrvtnPrceReMkngMthdNm(getText(node, "rsrvtnPrceReMkngMthdNm"));
//        condition.setArsltApplDocRcptMthdNm(getText(node, "arsltApplDocRcptMthdNm"));
//        condition.setArsltReqstdocRcptDt(toLocalDateTime(getText(node, "arsltReqstdocRcptDt")));
        dto.setCondition(condition);

        BiddingBudgetInfoDto budget = new BiddingBudgetInfoDto();
        budget.setAsignBdgtAmt(toBigDecimal(getText(node, "asignBdgtAmt")));
        budget.setPresmptPrce(toBigDecimal(getText(node, "presmptPrce")));
        budget.setPrearngPrceDcsnMthdNm(getText(node, "prearngPrceDcsnMthdNm"));
        budget.setTotPrdprcNum(toInteger(getText(node, "totPrdprcNum")));
        budget.setDrwtPrdprcNum(toInteger(getText(node, "drwtPrdprcNum")));
        budget.setBidPrtcptFee(toBigDecimal(getText(node, "bidPrtcptFee")));
        budget.setVAT(toBigDecimal(getText(node, "VAT")));
        budget.setIndutyVAT(toBigDecimal(getText(node, "indutyVAT")));
        dto.setBudgetInfo(budget);

        BiddingCategoryInfoDto category = new BiddingCategoryInfoDto();
        category.setPpswGnrlSrvceYn(toBoolean(node, "ppswGnrlSrvceYn"));
        category.setSrvceDivNm(getText(node, "srvceDivNm"));
        category.setPrdctClsfcLmtYn(toBoolean(node, "prdctClsfcLmtYn"));
        category.setMnfctYn(toBoolean(node, "mnfctYn"));
        category.setPurchsObjPrdctList(getText(node, "purchsObjPrdctList"));
        category.setPubPrcrmntLrgclsfcNm(getText(node, "pubPrcrmntLrgClsfcNm"));
        category.setPubPrcrmntMidclsfcNm(getText(node, "pubPrcrmntMidClsfcNm"));
        category.setPubPrcrmntClsfcNo(getText(node, "pubPrcrmntClsfcNo"));
        category.setPubPrcrmntClsfcNm(getText(node, "pubPrcrmntClsfcNm"));
        dto.setCategoryInfo(category);

        BiddingApplicationConditionDto appCond = new BiddingApplicationConditionDto();
        appCond.setPqApplDocRcptMthdNm(getText(node, "pqApplDocRcptMthdNm"));
        appCond.setTpEvalApplMthdNm(getText(node, "tpEvalApplMthdNm"));
        appCond.setRsrvtnPrceReMkngMthdNm(getText(node, "rsrvtnPrceReMkngMthdNm"));
        appCond.setArsltApplDocRcptMthdNm(getText(node, "arsltApplDocRcptMthdNm"));
        appCond.setCmmnSpldmdMethdCd(getText(node, "cmmnSpldmdMethdCd"));
        appCond.setCmmnSpldmdMethdNm(getText(node, "cmmnSpldmdMethdNm"));
        appCond.setJntcontrctDutyRgnNm1(getText(node, "jntcontrctDutyRgnNm1"));
        appCond.setJntcontrctDutyRgnNm2(getText(node, "jntcontrctDutyRgnNm2"));
        appCond.setJntcontrctDutyRgnNm3(getText(node, "jntcontrctDutyRgnNm3"));
        appCond.setRgnDutyJntcontrctRt(toBigDecimal(getText(node, "rgnDutyJntcontrctRt")));
        appCond.setSucsfbidLwltRate(toBigDecimal(getText(node, "sucsfbidLwltRate")));
        appCond.setSucsfbidMthdCd(getText(node, "sucsfbidMthdCd"));
        appCond.setSucsfbidMthdNm(getText(node, "sucsfbidMthdNm"));
        dto.setApplicationCondition(appCond);

        BiddingChangeHistoryDto changeHistory = new BiddingChangeHistoryDto();
        changeHistory.setChgDt(toLocalDateTime(getText(node, "chgDt")));
        changeHistory.setChgNtceRsn(getText(node, "chgNtceRsn"));
        dto.setChangeHistory(changeHistory);

        dto.setBiddingResult(null);

        return dto;
    }

    private String getText(JsonNode node, String fieldName) {
        JsonNode val = node.get(fieldName);
        return (val != null && !val.isNull()) ? val.asText() : null;
    }

    private Boolean toBoolean(JsonNode node, String fieldName) {
        String val = getText(node, fieldName);
        return val != null && val.equalsIgnoreCase("Y");
    }

    private LocalDateTime toLocalDateTime(String str) {
        if (str == null || str.isEmpty()) return null;
        str = str.replace(" ", "T");
        try {
            return LocalDateTime.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    private BigDecimal toBigDecimal(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : new BigDecimal(str);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer toInteger(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
}

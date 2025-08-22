package com.ezfuzzy.bidpanda.common.mapper;

import com.ezfuzzy.bidpanda.bidding.dto.BiddingAgencyInfoDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingApplicationConditionDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingBudgetInfoDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingCategoryInfoDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingChangeHistoryDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingConditionDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingDocumentInfoDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingNoticeDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingPersonInChargeDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingResultDto;
import com.ezfuzzy.bidpanda.bidding.dto.BiddingScheduleDto;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingAgencyInfo;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingApplicationCondition;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingBudgetInfo;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingCategoryInfo;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingChangeHistory;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingCondition;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingDocumentInfo;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingNotice;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingPersonInCharge;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingResult;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingSchedule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T11:08:16+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class BiddingNoticeMapperImpl implements BiddingNoticeMapper {

    @Override
    public BiddingNotice toEntity(BiddingNoticeDto dto) {
        if ( dto == null ) {
            return null;
        }

        BiddingNotice.BiddingNoticeBuilder biddingNotice = BiddingNotice.builder();

        biddingNotice.id( dto.getId() );
        biddingNotice.bidNtceNo( dto.getBidNtceNo() );
        biddingNotice.bidNtceOrd( dto.getBidNtceOrd() );
        biddingNotice.bidNtceNm( dto.getBidNtceNm() );
        biddingNotice.ntceKindNm( dto.getNtceKindNm() );
        biddingNotice.rgstTyNm( dto.getRgstTyNm() );
        biddingNotice.reNtceYn( dto.getReNtceYn() );
        biddingNotice.intrbidYn( dto.getIntrbidYn() );
        biddingNotice.bidMethdNm( dto.getBidMethdNm() );
        biddingNotice.cntrctCnclsMthdNm( dto.getCntrctCnclsMthdNm() );
        biddingNotice.refNo( dto.getRefNo() );
        biddingNotice.untyNtceNo( dto.getUntyNtceNo() );
        biddingNotice.indstrytyCd( dto.getIndstrytyCd() );
        biddingNotice.indstrytyNm( dto.getIndstrytyNm() );
        biddingNotice.prtcptPsblRgnCd( dto.getPrtcptPsblRgnCd() );
        biddingNotice.prtcptPsblRgnNm( dto.getPrtcptPsblRgnNm() );
        biddingNotice.summary( dto.getSummary() );
        biddingNotice.createdAt( dto.getCreatedAt() );
        biddingNotice.updatedAt( dto.getUpdatedAt() );
        biddingNotice.agencyInfo( biddingAgencyInfoDtoToBiddingAgencyInfo( dto.getAgencyInfo() ) );
        biddingNotice.applicationCondition( biddingApplicationConditionDtoToBiddingApplicationCondition( dto.getApplicationCondition() ) );
        biddingNotice.budgetInfo( biddingBudgetInfoDtoToBiddingBudgetInfo( dto.getBudgetInfo() ) );
        biddingNotice.categoryInfo( biddingCategoryInfoDtoToBiddingCategoryInfo( dto.getCategoryInfo() ) );
        biddingNotice.changeHistory( biddingChangeHistoryDtoToBiddingChangeHistory( dto.getChangeHistory() ) );
        biddingNotice.condition( biddingConditionDtoToBiddingCondition( dto.getCondition() ) );
        biddingNotice.documents( biddingDocumentInfoDtoListToBiddingDocumentInfoList( dto.getDocuments() ) );
        biddingNotice.personInCharge( biddingPersonInChargeDtoToBiddingPersonInCharge( dto.getPersonInCharge() ) );
        biddingNotice.biddingResult( biddingResultDtoToBiddingResult( dto.getBiddingResult() ) );
        biddingNotice.schedule( biddingScheduleDtoToBiddingSchedule( dto.getSchedule() ) );

        return biddingNotice.build();
    }

    @Override
    public BiddingNoticeDto toDto(BiddingNotice entity) {
        if ( entity == null ) {
            return null;
        }

        BiddingNoticeDto.BiddingNoticeDtoBuilder biddingNoticeDto = BiddingNoticeDto.builder();

        biddingNoticeDto.id( entity.getId() );
        biddingNoticeDto.bidNtceNo( entity.getBidNtceNo() );
        biddingNoticeDto.bidNtceOrd( entity.getBidNtceOrd() );
        biddingNoticeDto.bidNtceNm( entity.getBidNtceNm() );
        biddingNoticeDto.ntceKindNm( entity.getNtceKindNm() );
        biddingNoticeDto.rgstTyNm( entity.getRgstTyNm() );
        biddingNoticeDto.reNtceYn( entity.getReNtceYn() );
        biddingNoticeDto.intrbidYn( entity.getIntrbidYn() );
        biddingNoticeDto.bidMethdNm( entity.getBidMethdNm() );
        biddingNoticeDto.cntrctCnclsMthdNm( entity.getCntrctCnclsMthdNm() );
        biddingNoticeDto.refNo( entity.getRefNo() );
        biddingNoticeDto.untyNtceNo( entity.getUntyNtceNo() );
        biddingNoticeDto.indstrytyCd( entity.getIndstrytyCd() );
        biddingNoticeDto.indstrytyNm( entity.getIndstrytyNm() );
        biddingNoticeDto.prtcptPsblRgnCd( entity.getPrtcptPsblRgnCd() );
        biddingNoticeDto.prtcptPsblRgnNm( entity.getPrtcptPsblRgnNm() );
        biddingNoticeDto.summary( entity.getSummary() );
        biddingNoticeDto.createdAt( entity.getCreatedAt() );
        biddingNoticeDto.updatedAt( entity.getUpdatedAt() );
        biddingNoticeDto.agencyInfo( biddingAgencyInfoToBiddingAgencyInfoDto( entity.getAgencyInfo() ) );
        biddingNoticeDto.personInCharge( biddingPersonInChargeToBiddingPersonInChargeDto( entity.getPersonInCharge() ) );
        biddingNoticeDto.schedule( biddingScheduleToBiddingScheduleDto( entity.getSchedule() ) );
        biddingNoticeDto.documents( biddingDocumentInfoListToBiddingDocumentInfoDtoList( entity.getDocuments() ) );
        biddingNoticeDto.condition( biddingConditionToBiddingConditionDto( entity.getCondition() ) );
        biddingNoticeDto.budgetInfo( biddingBudgetInfoToBiddingBudgetInfoDto( entity.getBudgetInfo() ) );
        biddingNoticeDto.categoryInfo( biddingCategoryInfoToBiddingCategoryInfoDto( entity.getCategoryInfo() ) );
        biddingNoticeDto.applicationCondition( biddingApplicationConditionToBiddingApplicationConditionDto( entity.getApplicationCondition() ) );
        biddingNoticeDto.changeHistory( biddingChangeHistoryToBiddingChangeHistoryDto( entity.getChangeHistory() ) );
        biddingNoticeDto.biddingResult( biddingResultToBiddingResultDto( entity.getBiddingResult() ) );

        return biddingNoticeDto.build();
    }

    @Override
    public void updateEntityFromDto(BiddingNoticeDto dto, BiddingNotice entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setBidNtceNo( dto.getBidNtceNo() );
        entity.setBidNtceOrd( dto.getBidNtceOrd() );
        entity.setBidNtceNm( dto.getBidNtceNm() );
        entity.setNtceKindNm( dto.getNtceKindNm() );
        entity.setRgstTyNm( dto.getRgstTyNm() );
        entity.setReNtceYn( dto.getReNtceYn() );
        entity.setIntrbidYn( dto.getIntrbidYn() );
        entity.setBidMethdNm( dto.getBidMethdNm() );
        entity.setCntrctCnclsMthdNm( dto.getCntrctCnclsMthdNm() );
        entity.setRefNo( dto.getRefNo() );
        entity.setUntyNtceNo( dto.getUntyNtceNo() );
        entity.setIndstrytyCd( dto.getIndstrytyCd() );
        entity.setIndstrytyNm( dto.getIndstrytyNm() );
        entity.setPrtcptPsblRgnCd( dto.getPrtcptPsblRgnCd() );
        entity.setPrtcptPsblRgnNm( dto.getPrtcptPsblRgnNm() );
        entity.setSummary( dto.getSummary() );
        entity.setCreatedAt( dto.getCreatedAt() );
        entity.setUpdatedAt( dto.getUpdatedAt() );
        if ( dto.getAgencyInfo() != null ) {
            if ( entity.getAgencyInfo() == null ) {
                entity.setAgencyInfo( BiddingAgencyInfo.builder().build() );
            }
            biddingAgencyInfoDtoToBiddingAgencyInfo1( dto.getAgencyInfo(), entity.getAgencyInfo() );
        }
        else {
            entity.setAgencyInfo( null );
        }
        if ( dto.getApplicationCondition() != null ) {
            if ( entity.getApplicationCondition() == null ) {
                entity.setApplicationCondition( BiddingApplicationCondition.builder().build() );
            }
            biddingApplicationConditionDtoToBiddingApplicationCondition1( dto.getApplicationCondition(), entity.getApplicationCondition() );
        }
        else {
            entity.setApplicationCondition( null );
        }
        if ( dto.getBudgetInfo() != null ) {
            if ( entity.getBudgetInfo() == null ) {
                entity.setBudgetInfo( BiddingBudgetInfo.builder().build() );
            }
            biddingBudgetInfoDtoToBiddingBudgetInfo1( dto.getBudgetInfo(), entity.getBudgetInfo() );
        }
        else {
            entity.setBudgetInfo( null );
        }
        if ( dto.getCategoryInfo() != null ) {
            if ( entity.getCategoryInfo() == null ) {
                entity.setCategoryInfo( BiddingCategoryInfo.builder().build() );
            }
            biddingCategoryInfoDtoToBiddingCategoryInfo1( dto.getCategoryInfo(), entity.getCategoryInfo() );
        }
        else {
            entity.setCategoryInfo( null );
        }
        if ( dto.getChangeHistory() != null ) {
            if ( entity.getChangeHistory() == null ) {
                entity.setChangeHistory( BiddingChangeHistory.builder().build() );
            }
            biddingChangeHistoryDtoToBiddingChangeHistory1( dto.getChangeHistory(), entity.getChangeHistory() );
        }
        else {
            entity.setChangeHistory( null );
        }
        if ( dto.getCondition() != null ) {
            if ( entity.getCondition() == null ) {
                entity.setCondition( BiddingCondition.builder().build() );
            }
            biddingConditionDtoToBiddingCondition1( dto.getCondition(), entity.getCondition() );
        }
        else {
            entity.setCondition( null );
        }
        if ( entity.getDocuments() != null ) {
            List<BiddingDocumentInfo> list = biddingDocumentInfoDtoListToBiddingDocumentInfoList( dto.getDocuments() );
            if ( list != null ) {
                entity.getDocuments().clear();
                entity.getDocuments().addAll( list );
            }
            else {
                entity.setDocuments( null );
            }
        }
        else {
            List<BiddingDocumentInfo> list = biddingDocumentInfoDtoListToBiddingDocumentInfoList( dto.getDocuments() );
            if ( list != null ) {
                entity.setDocuments( list );
            }
        }
        if ( dto.getPersonInCharge() != null ) {
            if ( entity.getPersonInCharge() == null ) {
                entity.setPersonInCharge( BiddingPersonInCharge.builder().build() );
            }
            biddingPersonInChargeDtoToBiddingPersonInCharge1( dto.getPersonInCharge(), entity.getPersonInCharge() );
        }
        else {
            entity.setPersonInCharge( null );
        }
        if ( dto.getBiddingResult() != null ) {
            if ( entity.getBiddingResult() == null ) {
                entity.setBiddingResult( BiddingResult.builder().build() );
            }
            biddingResultDtoToBiddingResult1( dto.getBiddingResult(), entity.getBiddingResult() );
        }
        else {
            entity.setBiddingResult( null );
        }
        if ( dto.getSchedule() != null ) {
            if ( entity.getSchedule() == null ) {
                entity.setSchedule( BiddingSchedule.builder().build() );
            }
            biddingScheduleDtoToBiddingSchedule1( dto.getSchedule(), entity.getSchedule() );
        }
        else {
            entity.setSchedule( null );
        }

        linkBiddingNotice( entity );
    }

    protected BiddingAgencyInfo biddingAgencyInfoDtoToBiddingAgencyInfo(BiddingAgencyInfoDto biddingAgencyInfoDto) {
        if ( biddingAgencyInfoDto == null ) {
            return null;
        }

        BiddingAgencyInfo.BiddingAgencyInfoBuilder biddingAgencyInfo = BiddingAgencyInfo.builder();

        biddingAgencyInfo.id( biddingAgencyInfoDto.getId() );
        biddingAgencyInfo.ntceInsttCd( biddingAgencyInfoDto.getNtceInsttCd() );
        biddingAgencyInfo.ntceInsttNm( biddingAgencyInfoDto.getNtceInsttNm() );
        biddingAgencyInfo.dminsttCd( biddingAgencyInfoDto.getDminsttCd() );
        biddingAgencyInfo.dminsttNm( biddingAgencyInfoDto.getDminsttNm() );

        return biddingAgencyInfo.build();
    }

    protected BiddingApplicationCondition biddingApplicationConditionDtoToBiddingApplicationCondition(BiddingApplicationConditionDto biddingApplicationConditionDto) {
        if ( biddingApplicationConditionDto == null ) {
            return null;
        }

        BiddingApplicationCondition.BiddingApplicationConditionBuilder biddingApplicationCondition = BiddingApplicationCondition.builder();

        biddingApplicationCondition.id( biddingApplicationConditionDto.getId() );
        biddingApplicationCondition.pqApplDocRcptMthdNm( biddingApplicationConditionDto.getPqApplDocRcptMthdNm() );
        biddingApplicationCondition.tpEvalApplMthdNm( biddingApplicationConditionDto.getTpEvalApplMthdNm() );
        biddingApplicationCondition.rsrvtnPrceReMkngMthdNm( biddingApplicationConditionDto.getRsrvtnPrceReMkngMthdNm() );
        biddingApplicationCondition.arsltApplDocRcptMthdNm( biddingApplicationConditionDto.getArsltApplDocRcptMthdNm() );
        biddingApplicationCondition.cmmnSpldmdMethdCd( biddingApplicationConditionDto.getCmmnSpldmdMethdCd() );
        biddingApplicationCondition.cmmnSpldmdMethdNm( biddingApplicationConditionDto.getCmmnSpldmdMethdNm() );
        biddingApplicationCondition.jntcontrctDutyRgnNm1( biddingApplicationConditionDto.getJntcontrctDutyRgnNm1() );
        biddingApplicationCondition.jntcontrctDutyRgnNm2( biddingApplicationConditionDto.getJntcontrctDutyRgnNm2() );
        biddingApplicationCondition.jntcontrctDutyRgnNm3( biddingApplicationConditionDto.getJntcontrctDutyRgnNm3() );
        biddingApplicationCondition.rgnDutyJntcontrctRt( biddingApplicationConditionDto.getRgnDutyJntcontrctRt() );
        biddingApplicationCondition.sucsfbidLwltRate( biddingApplicationConditionDto.getSucsfbidLwltRate() );
        biddingApplicationCondition.sucsfbidMthdCd( biddingApplicationConditionDto.getSucsfbidMthdCd() );
        biddingApplicationCondition.sucsfbidMthdNm( biddingApplicationConditionDto.getSucsfbidMthdNm() );

        return biddingApplicationCondition.build();
    }

    protected BiddingBudgetInfo biddingBudgetInfoDtoToBiddingBudgetInfo(BiddingBudgetInfoDto biddingBudgetInfoDto) {
        if ( biddingBudgetInfoDto == null ) {
            return null;
        }

        BiddingBudgetInfo.BiddingBudgetInfoBuilder biddingBudgetInfo = BiddingBudgetInfo.builder();

        biddingBudgetInfo.id( biddingBudgetInfoDto.getId() );
        biddingBudgetInfo.asignBdgtAmt( biddingBudgetInfoDto.getAsignBdgtAmt() );
        biddingBudgetInfo.presmptPrce( biddingBudgetInfoDto.getPresmptPrce() );
        biddingBudgetInfo.prearngPrceDcsnMthdNm( biddingBudgetInfoDto.getPrearngPrceDcsnMthdNm() );
        biddingBudgetInfo.totPrdprcNum( biddingBudgetInfoDto.getTotPrdprcNum() );
        biddingBudgetInfo.drwtPrdprcNum( biddingBudgetInfoDto.getDrwtPrdprcNum() );
        biddingBudgetInfo.bidPrtcptFee( biddingBudgetInfoDto.getBidPrtcptFee() );
        biddingBudgetInfo.VAT( biddingBudgetInfoDto.getVAT() );
        biddingBudgetInfo.indutyVAT( biddingBudgetInfoDto.getIndutyVAT() );

        return biddingBudgetInfo.build();
    }

    protected BiddingCategoryInfo biddingCategoryInfoDtoToBiddingCategoryInfo(BiddingCategoryInfoDto biddingCategoryInfoDto) {
        if ( biddingCategoryInfoDto == null ) {
            return null;
        }

        BiddingCategoryInfo.BiddingCategoryInfoBuilder biddingCategoryInfo = BiddingCategoryInfo.builder();

        biddingCategoryInfo.id( biddingCategoryInfoDto.getId() );
        biddingCategoryInfo.ppswGnrlSrvceYn( biddingCategoryInfoDto.getPpswGnrlSrvceYn() );
        biddingCategoryInfo.srvceDivNm( biddingCategoryInfoDto.getSrvceDivNm() );
        biddingCategoryInfo.prdctClsfcLmtYn( biddingCategoryInfoDto.getPrdctClsfcLmtYn() );
        biddingCategoryInfo.mnfctYn( biddingCategoryInfoDto.getMnfctYn() );
        biddingCategoryInfo.purchsObjPrdctList( biddingCategoryInfoDto.getPurchsObjPrdctList() );
        biddingCategoryInfo.pubPrcrmntLrgclsfcNm( biddingCategoryInfoDto.getPubPrcrmntLrgclsfcNm() );
        biddingCategoryInfo.pubPrcrmntMidclsfcNm( biddingCategoryInfoDto.getPubPrcrmntMidclsfcNm() );
        biddingCategoryInfo.pubPrcrmntClsfcNo( biddingCategoryInfoDto.getPubPrcrmntClsfcNo() );
        biddingCategoryInfo.pubPrcrmntClsfcNm( biddingCategoryInfoDto.getPubPrcrmntClsfcNm() );

        return biddingCategoryInfo.build();
    }

    protected BiddingChangeHistory biddingChangeHistoryDtoToBiddingChangeHistory(BiddingChangeHistoryDto biddingChangeHistoryDto) {
        if ( biddingChangeHistoryDto == null ) {
            return null;
        }

        BiddingChangeHistory.BiddingChangeHistoryBuilder biddingChangeHistory = BiddingChangeHistory.builder();

        biddingChangeHistory.id( biddingChangeHistoryDto.getId() );
        biddingChangeHistory.chgDt( biddingChangeHistoryDto.getChgDt() );
        biddingChangeHistory.chgNtceRsn( biddingChangeHistoryDto.getChgNtceRsn() );

        return biddingChangeHistory.build();
    }

    protected BiddingCondition biddingConditionDtoToBiddingCondition(BiddingConditionDto biddingConditionDto) {
        if ( biddingConditionDto == null ) {
            return null;
        }

        BiddingCondition.BiddingConditionBuilder biddingCondition = BiddingCondition.builder();

        biddingCondition.id( biddingConditionDto.getId() );
        biddingCondition.cmmnSpldmdAgrmntRcptdocMethd( biddingConditionDto.getCmmnSpldmdAgrmntRcptdocMethd() );
        biddingCondition.cmmnSpldmdAgrmntClseDt( biddingConditionDto.getCmmnSpldmdAgrmntClseDt() );
        biddingCondition.cmmnSpldmdCorpRgnLmtYn( biddingConditionDto.getCmmnSpldmdCorpRgnLmtYn() );
        biddingCondition.rbidPermsnYn( biddingConditionDto.getRbidPermsnYn() );
        biddingCondition.bidPrtcptLmtYn( biddingConditionDto.getBidPrtcptLmtYn() );
        biddingCondition.indstrytyLmtYn( biddingConditionDto.getIndstrytyLmtYn() );
        biddingCondition.dtlsBidYn( biddingConditionDto.getDtlsBidYn() );
        biddingCondition.bidGrntymnyPaymntYn( biddingConditionDto.getBidGrntymnyPaymntYn() );
        biddingCondition.bidPrtcptFeePaymntYn( biddingConditionDto.getBidPrtcptFeePaymntYn() );
        biddingCondition.brffcBidprcPermsnYn( biddingConditionDto.getBrffcBidprcPermsnYn() );
        biddingCondition.dsgntCmptYn( biddingConditionDto.getDsgntCmptYn() );
        biddingCondition.arsltCmptYn( biddingConditionDto.getArsltCmptYn() );
        biddingCondition.pqEvalYn( biddingConditionDto.getPqEvalYn() );
        biddingCondition.tpEvalYn( biddingConditionDto.getTpEvalYn() );
        biddingCondition.ntceDscrptYn( biddingConditionDto.getNtceDscrptYn() );

        return biddingCondition.build();
    }

    protected BiddingDocumentInfo biddingDocumentInfoDtoToBiddingDocumentInfo(BiddingDocumentInfoDto biddingDocumentInfoDto) {
        if ( biddingDocumentInfoDto == null ) {
            return null;
        }

        BiddingDocumentInfo.BiddingDocumentInfoBuilder biddingDocumentInfo = BiddingDocumentInfo.builder();

        biddingDocumentInfo.id( biddingDocumentInfoDto.getId() );
        List<String> list = biddingDocumentInfoDto.getSpecDocUrls();
        if ( list != null ) {
            biddingDocumentInfo.specDocUrls( new ArrayList<String>( list ) );
        }
        List<String> list1 = biddingDocumentInfoDto.getSpecFileNames();
        if ( list1 != null ) {
            biddingDocumentInfo.specFileNames( new ArrayList<String>( list1 ) );
        }
        biddingDocumentInfo.stdNtceDocUrl( biddingDocumentInfoDto.getStdNtceDocUrl() );
        biddingDocumentInfo.dcmtgOprtnPlce( biddingDocumentInfoDto.getDcmtgOprtnPlce() );
        biddingDocumentInfo.bidNtceDtlUrl( biddingDocumentInfoDto.getBidNtceDtlUrl() );
        biddingDocumentInfo.bidNtceUrl( biddingDocumentInfoDto.getBidNtceUrl() );

        return biddingDocumentInfo.build();
    }

    protected List<BiddingDocumentInfo> biddingDocumentInfoDtoListToBiddingDocumentInfoList(List<BiddingDocumentInfoDto> list) {
        if ( list == null ) {
            return null;
        }

        List<BiddingDocumentInfo> list1 = new ArrayList<BiddingDocumentInfo>( list.size() );
        for ( BiddingDocumentInfoDto biddingDocumentInfoDto : list ) {
            list1.add( biddingDocumentInfoDtoToBiddingDocumentInfo( biddingDocumentInfoDto ) );
        }

        return list1;
    }

    protected BiddingPersonInCharge biddingPersonInChargeDtoToBiddingPersonInCharge(BiddingPersonInChargeDto biddingPersonInChargeDto) {
        if ( biddingPersonInChargeDto == null ) {
            return null;
        }

        BiddingPersonInCharge.BiddingPersonInChargeBuilder biddingPersonInCharge = BiddingPersonInCharge.builder();

        biddingPersonInCharge.id( biddingPersonInChargeDto.getId() );
        biddingPersonInCharge.ntceInsttOfclNm( biddingPersonInChargeDto.getNtceInsttOfclNm() );
        biddingPersonInCharge.ntceInsttOfclTelNo( biddingPersonInChargeDto.getNtceInsttOfclTelNo() );
        biddingPersonInCharge.ntceInsttOfclEmailAdrs( biddingPersonInChargeDto.getNtceInsttOfclEmailAdrs() );
        biddingPersonInCharge.dminsttOfclEmailAdrs( biddingPersonInChargeDto.getDminsttOfclEmailAdrs() );
        biddingPersonInCharge.exctvNm( biddingPersonInChargeDto.getExctvNm() );

        return biddingPersonInCharge.build();
    }

    protected BiddingResult biddingResultDtoToBiddingResult(BiddingResultDto biddingResultDto) {
        if ( biddingResultDto == null ) {
            return null;
        }

        BiddingResult.BiddingResultBuilder biddingResult = BiddingResult.builder();

        biddingResult.id( biddingResultDto.getId() );
        biddingResult.bidNtceNo( biddingResultDto.getBidNtceNo() );
        biddingResult.bidNtceOrd( biddingResultDto.getBidNtceOrd() );
        biddingResult.bidClsfcNo( biddingResultDto.getBidClsfcNo() );
        biddingResult.rbidNo( biddingResultDto.getRbidNo() );
        biddingResult.ntceDivCd( biddingResultDto.getNtceDivCd() );
        biddingResult.bidNtceNm( biddingResultDto.getBidNtceNm() );
        biddingResult.prtcptCnum( biddingResultDto.getPrtcptCnum() );
        biddingResult.bidwinnrNm( biddingResultDto.getBidwinnrNm() );
        biddingResult.bidwinnrBizno( biddingResultDto.getBidwinnrBizno() );
        biddingResult.bidwinnrCeoNm( biddingResultDto.getBidwinnrCeoNm() );
        biddingResult.bidwinnrAdrs( biddingResultDto.getBidwinnrAdrs() );
        biddingResult.bidwinnrTelNo( biddingResultDto.getBidwinnrTelNo() );
        biddingResult.sucsfbidAmt( biddingResultDto.getSucsfbidAmt() );
        biddingResult.sucsfbidRate( biddingResultDto.getSucsfbidRate() );
        biddingResult.rlOpengDt( biddingResultDto.getRlOpengDt() );
        biddingResult.dminsttCd( biddingResultDto.getDminsttCd() );
        biddingResult.dminsttNm( biddingResultDto.getDminsttNm() );
        biddingResult.rgstDt( biddingResultDto.getRgstDt() );
        biddingResult.fnlSucsfDate( biddingResultDto.getFnlSucsfDate() );
        biddingResult.fnlSucsfCorpOfcl( biddingResultDto.getFnlSucsfCorpOfcl() );

        return biddingResult.build();
    }

    protected BiddingSchedule biddingScheduleDtoToBiddingSchedule(BiddingScheduleDto biddingScheduleDto) {
        if ( biddingScheduleDto == null ) {
            return null;
        }

        BiddingSchedule.BiddingScheduleBuilder biddingSchedule = BiddingSchedule.builder();

        biddingSchedule.id( biddingScheduleDto.getId() );
        biddingSchedule.bidNtceDt( biddingScheduleDto.getBidNtceDt() );
        biddingSchedule.bidBeginDt( biddingScheduleDto.getBidBeginDt() );
        biddingSchedule.bidClseDt( biddingScheduleDto.getBidClseDt() );
        biddingSchedule.opengDt( biddingScheduleDto.getOpengDt() );
        biddingSchedule.bidQlfctRgstDt( biddingScheduleDto.getBidQlfctRgstDt() );
        biddingSchedule.pqApplDocRcptDt( biddingScheduleDto.getPqApplDocRcptDt() );
        biddingSchedule.tpEvalApplClseDt( biddingScheduleDto.getTpEvalApplClseDt() );
        biddingSchedule.arsltReqstdocRcptDt( biddingScheduleDto.getArsltReqstdocRcptDt() );
        biddingSchedule.dcmtgOprtnDt( biddingScheduleDto.getDcmtgOprtnDt() );
        biddingSchedule.rbidOpengDt( biddingScheduleDto.getRbidOpengDt() );
        biddingSchedule.rgstDt( biddingScheduleDto.getRgstDt() );
        biddingSchedule.chgDt( biddingScheduleDto.getChgDt() );

        return biddingSchedule.build();
    }

    protected BiddingAgencyInfoDto biddingAgencyInfoToBiddingAgencyInfoDto(BiddingAgencyInfo biddingAgencyInfo) {
        if ( biddingAgencyInfo == null ) {
            return null;
        }

        BiddingAgencyInfoDto.BiddingAgencyInfoDtoBuilder biddingAgencyInfoDto = BiddingAgencyInfoDto.builder();

        biddingAgencyInfoDto.id( biddingAgencyInfo.getId() );
        biddingAgencyInfoDto.ntceInsttCd( biddingAgencyInfo.getNtceInsttCd() );
        biddingAgencyInfoDto.ntceInsttNm( biddingAgencyInfo.getNtceInsttNm() );
        biddingAgencyInfoDto.dminsttCd( biddingAgencyInfo.getDminsttCd() );
        biddingAgencyInfoDto.dminsttNm( biddingAgencyInfo.getDminsttNm() );

        return biddingAgencyInfoDto.build();
    }

    protected BiddingPersonInChargeDto biddingPersonInChargeToBiddingPersonInChargeDto(BiddingPersonInCharge biddingPersonInCharge) {
        if ( biddingPersonInCharge == null ) {
            return null;
        }

        BiddingPersonInChargeDto.BiddingPersonInChargeDtoBuilder biddingPersonInChargeDto = BiddingPersonInChargeDto.builder();

        biddingPersonInChargeDto.id( biddingPersonInCharge.getId() );
        biddingPersonInChargeDto.ntceInsttOfclNm( biddingPersonInCharge.getNtceInsttOfclNm() );
        biddingPersonInChargeDto.ntceInsttOfclTelNo( biddingPersonInCharge.getNtceInsttOfclTelNo() );
        biddingPersonInChargeDto.ntceInsttOfclEmailAdrs( biddingPersonInCharge.getNtceInsttOfclEmailAdrs() );
        biddingPersonInChargeDto.dminsttOfclEmailAdrs( biddingPersonInCharge.getDminsttOfclEmailAdrs() );
        biddingPersonInChargeDto.exctvNm( biddingPersonInCharge.getExctvNm() );

        return biddingPersonInChargeDto.build();
    }

    protected BiddingScheduleDto biddingScheduleToBiddingScheduleDto(BiddingSchedule biddingSchedule) {
        if ( biddingSchedule == null ) {
            return null;
        }

        BiddingScheduleDto.BiddingScheduleDtoBuilder biddingScheduleDto = BiddingScheduleDto.builder();

        biddingScheduleDto.id( biddingSchedule.getId() );
        biddingScheduleDto.bidNtceDt( biddingSchedule.getBidNtceDt() );
        biddingScheduleDto.bidBeginDt( biddingSchedule.getBidBeginDt() );
        biddingScheduleDto.bidClseDt( biddingSchedule.getBidClseDt() );
        biddingScheduleDto.opengDt( biddingSchedule.getOpengDt() );
        biddingScheduleDto.bidQlfctRgstDt( biddingSchedule.getBidQlfctRgstDt() );
        biddingScheduleDto.pqApplDocRcptDt( biddingSchedule.getPqApplDocRcptDt() );
        biddingScheduleDto.tpEvalApplClseDt( biddingSchedule.getTpEvalApplClseDt() );
        biddingScheduleDto.arsltReqstdocRcptDt( biddingSchedule.getArsltReqstdocRcptDt() );
        biddingScheduleDto.dcmtgOprtnDt( biddingSchedule.getDcmtgOprtnDt() );
        biddingScheduleDto.rbidOpengDt( biddingSchedule.getRbidOpengDt() );
        biddingScheduleDto.rgstDt( biddingSchedule.getRgstDt() );
        biddingScheduleDto.chgDt( biddingSchedule.getChgDt() );

        return biddingScheduleDto.build();
    }

    protected BiddingDocumentInfoDto biddingDocumentInfoToBiddingDocumentInfoDto(BiddingDocumentInfo biddingDocumentInfo) {
        if ( biddingDocumentInfo == null ) {
            return null;
        }

        BiddingDocumentInfoDto.BiddingDocumentInfoDtoBuilder biddingDocumentInfoDto = BiddingDocumentInfoDto.builder();

        biddingDocumentInfoDto.id( biddingDocumentInfo.getId() );
        List<String> list = biddingDocumentInfo.getSpecDocUrls();
        if ( list != null ) {
            biddingDocumentInfoDto.specDocUrls( new ArrayList<String>( list ) );
        }
        List<String> list1 = biddingDocumentInfo.getSpecFileNames();
        if ( list1 != null ) {
            biddingDocumentInfoDto.specFileNames( new ArrayList<String>( list1 ) );
        }
        biddingDocumentInfoDto.stdNtceDocUrl( biddingDocumentInfo.getStdNtceDocUrl() );
        biddingDocumentInfoDto.dcmtgOprtnPlce( biddingDocumentInfo.getDcmtgOprtnPlce() );
        biddingDocumentInfoDto.bidNtceDtlUrl( biddingDocumentInfo.getBidNtceDtlUrl() );
        biddingDocumentInfoDto.bidNtceUrl( biddingDocumentInfo.getBidNtceUrl() );

        return biddingDocumentInfoDto.build();
    }

    protected List<BiddingDocumentInfoDto> biddingDocumentInfoListToBiddingDocumentInfoDtoList(List<BiddingDocumentInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<BiddingDocumentInfoDto> list1 = new ArrayList<BiddingDocumentInfoDto>( list.size() );
        for ( BiddingDocumentInfo biddingDocumentInfo : list ) {
            list1.add( biddingDocumentInfoToBiddingDocumentInfoDto( biddingDocumentInfo ) );
        }

        return list1;
    }

    protected BiddingConditionDto biddingConditionToBiddingConditionDto(BiddingCondition biddingCondition) {
        if ( biddingCondition == null ) {
            return null;
        }

        BiddingConditionDto.BiddingConditionDtoBuilder biddingConditionDto = BiddingConditionDto.builder();

        biddingConditionDto.id( biddingCondition.getId() );
        biddingConditionDto.cmmnSpldmdAgrmntRcptdocMethd( biddingCondition.getCmmnSpldmdAgrmntRcptdocMethd() );
        biddingConditionDto.cmmnSpldmdAgrmntClseDt( biddingCondition.getCmmnSpldmdAgrmntClseDt() );
        biddingConditionDto.cmmnSpldmdCorpRgnLmtYn( biddingCondition.getCmmnSpldmdCorpRgnLmtYn() );
        biddingConditionDto.rbidPermsnYn( biddingCondition.getRbidPermsnYn() );
        biddingConditionDto.bidPrtcptLmtYn( biddingCondition.getBidPrtcptLmtYn() );
        biddingConditionDto.indstrytyLmtYn( biddingCondition.getIndstrytyLmtYn() );
        biddingConditionDto.dtlsBidYn( biddingCondition.getDtlsBidYn() );
        biddingConditionDto.bidGrntymnyPaymntYn( biddingCondition.getBidGrntymnyPaymntYn() );
        biddingConditionDto.bidPrtcptFeePaymntYn( biddingCondition.getBidPrtcptFeePaymntYn() );
        biddingConditionDto.brffcBidprcPermsnYn( biddingCondition.getBrffcBidprcPermsnYn() );
        biddingConditionDto.dsgntCmptYn( biddingCondition.getDsgntCmptYn() );
        biddingConditionDto.arsltCmptYn( biddingCondition.getArsltCmptYn() );
        biddingConditionDto.pqEvalYn( biddingCondition.getPqEvalYn() );
        biddingConditionDto.tpEvalYn( biddingCondition.getTpEvalYn() );
        biddingConditionDto.ntceDscrptYn( biddingCondition.getNtceDscrptYn() );

        return biddingConditionDto.build();
    }

    protected BiddingBudgetInfoDto biddingBudgetInfoToBiddingBudgetInfoDto(BiddingBudgetInfo biddingBudgetInfo) {
        if ( biddingBudgetInfo == null ) {
            return null;
        }

        BiddingBudgetInfoDto.BiddingBudgetInfoDtoBuilder biddingBudgetInfoDto = BiddingBudgetInfoDto.builder();

        biddingBudgetInfoDto.id( biddingBudgetInfo.getId() );
        biddingBudgetInfoDto.asignBdgtAmt( biddingBudgetInfo.getAsignBdgtAmt() );
        biddingBudgetInfoDto.presmptPrce( biddingBudgetInfo.getPresmptPrce() );
        biddingBudgetInfoDto.prearngPrceDcsnMthdNm( biddingBudgetInfo.getPrearngPrceDcsnMthdNm() );
        biddingBudgetInfoDto.totPrdprcNum( biddingBudgetInfo.getTotPrdprcNum() );
        biddingBudgetInfoDto.drwtPrdprcNum( biddingBudgetInfo.getDrwtPrdprcNum() );
        biddingBudgetInfoDto.bidPrtcptFee( biddingBudgetInfo.getBidPrtcptFee() );
        biddingBudgetInfoDto.VAT( biddingBudgetInfo.getVAT() );
        biddingBudgetInfoDto.indutyVAT( biddingBudgetInfo.getIndutyVAT() );

        return biddingBudgetInfoDto.build();
    }

    protected BiddingCategoryInfoDto biddingCategoryInfoToBiddingCategoryInfoDto(BiddingCategoryInfo biddingCategoryInfo) {
        if ( biddingCategoryInfo == null ) {
            return null;
        }

        BiddingCategoryInfoDto.BiddingCategoryInfoDtoBuilder biddingCategoryInfoDto = BiddingCategoryInfoDto.builder();

        biddingCategoryInfoDto.id( biddingCategoryInfo.getId() );
        biddingCategoryInfoDto.ppswGnrlSrvceYn( biddingCategoryInfo.getPpswGnrlSrvceYn() );
        biddingCategoryInfoDto.srvceDivNm( biddingCategoryInfo.getSrvceDivNm() );
        biddingCategoryInfoDto.prdctClsfcLmtYn( biddingCategoryInfo.getPrdctClsfcLmtYn() );
        biddingCategoryInfoDto.mnfctYn( biddingCategoryInfo.getMnfctYn() );
        biddingCategoryInfoDto.purchsObjPrdctList( biddingCategoryInfo.getPurchsObjPrdctList() );
        biddingCategoryInfoDto.pubPrcrmntLrgclsfcNm( biddingCategoryInfo.getPubPrcrmntLrgclsfcNm() );
        biddingCategoryInfoDto.pubPrcrmntMidclsfcNm( biddingCategoryInfo.getPubPrcrmntMidclsfcNm() );
        biddingCategoryInfoDto.pubPrcrmntClsfcNo( biddingCategoryInfo.getPubPrcrmntClsfcNo() );
        biddingCategoryInfoDto.pubPrcrmntClsfcNm( biddingCategoryInfo.getPubPrcrmntClsfcNm() );

        return biddingCategoryInfoDto.build();
    }

    protected BiddingApplicationConditionDto biddingApplicationConditionToBiddingApplicationConditionDto(BiddingApplicationCondition biddingApplicationCondition) {
        if ( biddingApplicationCondition == null ) {
            return null;
        }

        BiddingApplicationConditionDto.BiddingApplicationConditionDtoBuilder biddingApplicationConditionDto = BiddingApplicationConditionDto.builder();

        biddingApplicationConditionDto.id( biddingApplicationCondition.getId() );
        biddingApplicationConditionDto.pqApplDocRcptMthdNm( biddingApplicationCondition.getPqApplDocRcptMthdNm() );
        biddingApplicationConditionDto.tpEvalApplMthdNm( biddingApplicationCondition.getTpEvalApplMthdNm() );
        biddingApplicationConditionDto.rsrvtnPrceReMkngMthdNm( biddingApplicationCondition.getRsrvtnPrceReMkngMthdNm() );
        biddingApplicationConditionDto.arsltApplDocRcptMthdNm( biddingApplicationCondition.getArsltApplDocRcptMthdNm() );
        biddingApplicationConditionDto.cmmnSpldmdMethdCd( biddingApplicationCondition.getCmmnSpldmdMethdCd() );
        biddingApplicationConditionDto.cmmnSpldmdMethdNm( biddingApplicationCondition.getCmmnSpldmdMethdNm() );
        biddingApplicationConditionDto.jntcontrctDutyRgnNm1( biddingApplicationCondition.getJntcontrctDutyRgnNm1() );
        biddingApplicationConditionDto.jntcontrctDutyRgnNm2( biddingApplicationCondition.getJntcontrctDutyRgnNm2() );
        biddingApplicationConditionDto.jntcontrctDutyRgnNm3( biddingApplicationCondition.getJntcontrctDutyRgnNm3() );
        biddingApplicationConditionDto.rgnDutyJntcontrctRt( biddingApplicationCondition.getRgnDutyJntcontrctRt() );
        biddingApplicationConditionDto.sucsfbidLwltRate( biddingApplicationCondition.getSucsfbidLwltRate() );
        biddingApplicationConditionDto.sucsfbidMthdCd( biddingApplicationCondition.getSucsfbidMthdCd() );
        biddingApplicationConditionDto.sucsfbidMthdNm( biddingApplicationCondition.getSucsfbidMthdNm() );

        return biddingApplicationConditionDto.build();
    }

    protected BiddingChangeHistoryDto biddingChangeHistoryToBiddingChangeHistoryDto(BiddingChangeHistory biddingChangeHistory) {
        if ( biddingChangeHistory == null ) {
            return null;
        }

        BiddingChangeHistoryDto.BiddingChangeHistoryDtoBuilder biddingChangeHistoryDto = BiddingChangeHistoryDto.builder();

        biddingChangeHistoryDto.id( biddingChangeHistory.getId() );
        biddingChangeHistoryDto.chgDt( biddingChangeHistory.getChgDt() );
        biddingChangeHistoryDto.chgNtceRsn( biddingChangeHistory.getChgNtceRsn() );

        return biddingChangeHistoryDto.build();
    }

    protected BiddingResultDto biddingResultToBiddingResultDto(BiddingResult biddingResult) {
        if ( biddingResult == null ) {
            return null;
        }

        BiddingResultDto.BiddingResultDtoBuilder biddingResultDto = BiddingResultDto.builder();

        biddingResultDto.id( biddingResult.getId() );
        biddingResultDto.bidNtceNo( biddingResult.getBidNtceNo() );
        biddingResultDto.bidNtceOrd( biddingResult.getBidNtceOrd() );
        biddingResultDto.bidClsfcNo( biddingResult.getBidClsfcNo() );
        biddingResultDto.rbidNo( biddingResult.getRbidNo() );
        biddingResultDto.ntceDivCd( biddingResult.getNtceDivCd() );
        biddingResultDto.bidNtceNm( biddingResult.getBidNtceNm() );
        biddingResultDto.prtcptCnum( biddingResult.getPrtcptCnum() );
        biddingResultDto.bidwinnrNm( biddingResult.getBidwinnrNm() );
        biddingResultDto.bidwinnrBizno( biddingResult.getBidwinnrBizno() );
        biddingResultDto.bidwinnrCeoNm( biddingResult.getBidwinnrCeoNm() );
        biddingResultDto.bidwinnrAdrs( biddingResult.getBidwinnrAdrs() );
        biddingResultDto.bidwinnrTelNo( biddingResult.getBidwinnrTelNo() );
        biddingResultDto.sucsfbidAmt( biddingResult.getSucsfbidAmt() );
        biddingResultDto.sucsfbidRate( biddingResult.getSucsfbidRate() );
        biddingResultDto.rlOpengDt( biddingResult.getRlOpengDt() );
        biddingResultDto.dminsttCd( biddingResult.getDminsttCd() );
        biddingResultDto.dminsttNm( biddingResult.getDminsttNm() );
        biddingResultDto.rgstDt( biddingResult.getRgstDt() );
        biddingResultDto.fnlSucsfDate( biddingResult.getFnlSucsfDate() );
        biddingResultDto.fnlSucsfCorpOfcl( biddingResult.getFnlSucsfCorpOfcl() );

        return biddingResultDto.build();
    }

    protected void biddingAgencyInfoDtoToBiddingAgencyInfo1(BiddingAgencyInfoDto biddingAgencyInfoDto, BiddingAgencyInfo mappingTarget) {
        if ( biddingAgencyInfoDto == null ) {
            return;
        }

        mappingTarget.setId( biddingAgencyInfoDto.getId() );
        mappingTarget.setNtceInsttCd( biddingAgencyInfoDto.getNtceInsttCd() );
        mappingTarget.setNtceInsttNm( biddingAgencyInfoDto.getNtceInsttNm() );
        mappingTarget.setDminsttCd( biddingAgencyInfoDto.getDminsttCd() );
        mappingTarget.setDminsttNm( biddingAgencyInfoDto.getDminsttNm() );
    }

    protected void biddingApplicationConditionDtoToBiddingApplicationCondition1(BiddingApplicationConditionDto biddingApplicationConditionDto, BiddingApplicationCondition mappingTarget) {
        if ( biddingApplicationConditionDto == null ) {
            return;
        }

        mappingTarget.setId( biddingApplicationConditionDto.getId() );
        mappingTarget.setPqApplDocRcptMthdNm( biddingApplicationConditionDto.getPqApplDocRcptMthdNm() );
        mappingTarget.setTpEvalApplMthdNm( biddingApplicationConditionDto.getTpEvalApplMthdNm() );
        mappingTarget.setRsrvtnPrceReMkngMthdNm( biddingApplicationConditionDto.getRsrvtnPrceReMkngMthdNm() );
        mappingTarget.setArsltApplDocRcptMthdNm( biddingApplicationConditionDto.getArsltApplDocRcptMthdNm() );
        mappingTarget.setCmmnSpldmdMethdCd( biddingApplicationConditionDto.getCmmnSpldmdMethdCd() );
        mappingTarget.setCmmnSpldmdMethdNm( biddingApplicationConditionDto.getCmmnSpldmdMethdNm() );
        mappingTarget.setJntcontrctDutyRgnNm1( biddingApplicationConditionDto.getJntcontrctDutyRgnNm1() );
        mappingTarget.setJntcontrctDutyRgnNm2( biddingApplicationConditionDto.getJntcontrctDutyRgnNm2() );
        mappingTarget.setJntcontrctDutyRgnNm3( biddingApplicationConditionDto.getJntcontrctDutyRgnNm3() );
        mappingTarget.setRgnDutyJntcontrctRt( biddingApplicationConditionDto.getRgnDutyJntcontrctRt() );
        mappingTarget.setSucsfbidLwltRate( biddingApplicationConditionDto.getSucsfbidLwltRate() );
        mappingTarget.setSucsfbidMthdCd( biddingApplicationConditionDto.getSucsfbidMthdCd() );
        mappingTarget.setSucsfbidMthdNm( biddingApplicationConditionDto.getSucsfbidMthdNm() );
    }

    protected void biddingBudgetInfoDtoToBiddingBudgetInfo1(BiddingBudgetInfoDto biddingBudgetInfoDto, BiddingBudgetInfo mappingTarget) {
        if ( biddingBudgetInfoDto == null ) {
            return;
        }

        mappingTarget.setId( biddingBudgetInfoDto.getId() );
        mappingTarget.setAsignBdgtAmt( biddingBudgetInfoDto.getAsignBdgtAmt() );
        mappingTarget.setPresmptPrce( biddingBudgetInfoDto.getPresmptPrce() );
        mappingTarget.setPrearngPrceDcsnMthdNm( biddingBudgetInfoDto.getPrearngPrceDcsnMthdNm() );
        mappingTarget.setTotPrdprcNum( biddingBudgetInfoDto.getTotPrdprcNum() );
        mappingTarget.setDrwtPrdprcNum( biddingBudgetInfoDto.getDrwtPrdprcNum() );
        mappingTarget.setBidPrtcptFee( biddingBudgetInfoDto.getBidPrtcptFee() );
        mappingTarget.setVAT( biddingBudgetInfoDto.getVAT() );
        mappingTarget.setIndutyVAT( biddingBudgetInfoDto.getIndutyVAT() );
    }

    protected void biddingCategoryInfoDtoToBiddingCategoryInfo1(BiddingCategoryInfoDto biddingCategoryInfoDto, BiddingCategoryInfo mappingTarget) {
        if ( biddingCategoryInfoDto == null ) {
            return;
        }

        mappingTarget.setId( biddingCategoryInfoDto.getId() );
        mappingTarget.setPpswGnrlSrvceYn( biddingCategoryInfoDto.getPpswGnrlSrvceYn() );
        mappingTarget.setSrvceDivNm( biddingCategoryInfoDto.getSrvceDivNm() );
        mappingTarget.setPrdctClsfcLmtYn( biddingCategoryInfoDto.getPrdctClsfcLmtYn() );
        mappingTarget.setMnfctYn( biddingCategoryInfoDto.getMnfctYn() );
        mappingTarget.setPurchsObjPrdctList( biddingCategoryInfoDto.getPurchsObjPrdctList() );
        mappingTarget.setPubPrcrmntLrgclsfcNm( biddingCategoryInfoDto.getPubPrcrmntLrgclsfcNm() );
        mappingTarget.setPubPrcrmntMidclsfcNm( biddingCategoryInfoDto.getPubPrcrmntMidclsfcNm() );
        mappingTarget.setPubPrcrmntClsfcNo( biddingCategoryInfoDto.getPubPrcrmntClsfcNo() );
        mappingTarget.setPubPrcrmntClsfcNm( biddingCategoryInfoDto.getPubPrcrmntClsfcNm() );
    }

    protected void biddingChangeHistoryDtoToBiddingChangeHistory1(BiddingChangeHistoryDto biddingChangeHistoryDto, BiddingChangeHistory mappingTarget) {
        if ( biddingChangeHistoryDto == null ) {
            return;
        }

        mappingTarget.setId( biddingChangeHistoryDto.getId() );
        mappingTarget.setChgDt( biddingChangeHistoryDto.getChgDt() );
        mappingTarget.setChgNtceRsn( biddingChangeHistoryDto.getChgNtceRsn() );
    }

    protected void biddingConditionDtoToBiddingCondition1(BiddingConditionDto biddingConditionDto, BiddingCondition mappingTarget) {
        if ( biddingConditionDto == null ) {
            return;
        }

        mappingTarget.setId( biddingConditionDto.getId() );
        mappingTarget.setCmmnSpldmdAgrmntRcptdocMethd( biddingConditionDto.getCmmnSpldmdAgrmntRcptdocMethd() );
        mappingTarget.setCmmnSpldmdAgrmntClseDt( biddingConditionDto.getCmmnSpldmdAgrmntClseDt() );
        mappingTarget.setCmmnSpldmdCorpRgnLmtYn( biddingConditionDto.getCmmnSpldmdCorpRgnLmtYn() );
        mappingTarget.setRbidPermsnYn( biddingConditionDto.getRbidPermsnYn() );
        mappingTarget.setBidPrtcptLmtYn( biddingConditionDto.getBidPrtcptLmtYn() );
        mappingTarget.setIndstrytyLmtYn( biddingConditionDto.getIndstrytyLmtYn() );
        mappingTarget.setDtlsBidYn( biddingConditionDto.getDtlsBidYn() );
        mappingTarget.setBidGrntymnyPaymntYn( biddingConditionDto.getBidGrntymnyPaymntYn() );
        mappingTarget.setBidPrtcptFeePaymntYn( biddingConditionDto.getBidPrtcptFeePaymntYn() );
        mappingTarget.setBrffcBidprcPermsnYn( biddingConditionDto.getBrffcBidprcPermsnYn() );
        mappingTarget.setDsgntCmptYn( biddingConditionDto.getDsgntCmptYn() );
        mappingTarget.setArsltCmptYn( biddingConditionDto.getArsltCmptYn() );
        mappingTarget.setPqEvalYn( biddingConditionDto.getPqEvalYn() );
        mappingTarget.setTpEvalYn( biddingConditionDto.getTpEvalYn() );
        mappingTarget.setNtceDscrptYn( biddingConditionDto.getNtceDscrptYn() );
    }

    protected void biddingPersonInChargeDtoToBiddingPersonInCharge1(BiddingPersonInChargeDto biddingPersonInChargeDto, BiddingPersonInCharge mappingTarget) {
        if ( biddingPersonInChargeDto == null ) {
            return;
        }

        mappingTarget.setId( biddingPersonInChargeDto.getId() );
        mappingTarget.setNtceInsttOfclNm( biddingPersonInChargeDto.getNtceInsttOfclNm() );
        mappingTarget.setNtceInsttOfclTelNo( biddingPersonInChargeDto.getNtceInsttOfclTelNo() );
        mappingTarget.setNtceInsttOfclEmailAdrs( biddingPersonInChargeDto.getNtceInsttOfclEmailAdrs() );
        mappingTarget.setDminsttOfclEmailAdrs( biddingPersonInChargeDto.getDminsttOfclEmailAdrs() );
        mappingTarget.setExctvNm( biddingPersonInChargeDto.getExctvNm() );
    }

    protected void biddingResultDtoToBiddingResult1(BiddingResultDto biddingResultDto, BiddingResult mappingTarget) {
        if ( biddingResultDto == null ) {
            return;
        }

        mappingTarget.setId( biddingResultDto.getId() );
        mappingTarget.setBidNtceNo( biddingResultDto.getBidNtceNo() );
        mappingTarget.setBidNtceOrd( biddingResultDto.getBidNtceOrd() );
        mappingTarget.setBidClsfcNo( biddingResultDto.getBidClsfcNo() );
        mappingTarget.setRbidNo( biddingResultDto.getRbidNo() );
        mappingTarget.setNtceDivCd( biddingResultDto.getNtceDivCd() );
        mappingTarget.setBidNtceNm( biddingResultDto.getBidNtceNm() );
        mappingTarget.setPrtcptCnum( biddingResultDto.getPrtcptCnum() );
        mappingTarget.setBidwinnrNm( biddingResultDto.getBidwinnrNm() );
        mappingTarget.setBidwinnrBizno( biddingResultDto.getBidwinnrBizno() );
        mappingTarget.setBidwinnrCeoNm( biddingResultDto.getBidwinnrCeoNm() );
        mappingTarget.setBidwinnrAdrs( biddingResultDto.getBidwinnrAdrs() );
        mappingTarget.setBidwinnrTelNo( biddingResultDto.getBidwinnrTelNo() );
        mappingTarget.setSucsfbidAmt( biddingResultDto.getSucsfbidAmt() );
        mappingTarget.setSucsfbidRate( biddingResultDto.getSucsfbidRate() );
        mappingTarget.setRlOpengDt( biddingResultDto.getRlOpengDt() );
        mappingTarget.setDminsttCd( biddingResultDto.getDminsttCd() );
        mappingTarget.setDminsttNm( biddingResultDto.getDminsttNm() );
        mappingTarget.setRgstDt( biddingResultDto.getRgstDt() );
        mappingTarget.setFnlSucsfDate( biddingResultDto.getFnlSucsfDate() );
        mappingTarget.setFnlSucsfCorpOfcl( biddingResultDto.getFnlSucsfCorpOfcl() );
    }

    protected void biddingScheduleDtoToBiddingSchedule1(BiddingScheduleDto biddingScheduleDto, BiddingSchedule mappingTarget) {
        if ( biddingScheduleDto == null ) {
            return;
        }

        mappingTarget.setId( biddingScheduleDto.getId() );
        mappingTarget.setBidNtceDt( biddingScheduleDto.getBidNtceDt() );
        mappingTarget.setBidBeginDt( biddingScheduleDto.getBidBeginDt() );
        mappingTarget.setBidClseDt( biddingScheduleDto.getBidClseDt() );
        mappingTarget.setOpengDt( biddingScheduleDto.getOpengDt() );
        mappingTarget.setBidQlfctRgstDt( biddingScheduleDto.getBidQlfctRgstDt() );
        mappingTarget.setPqApplDocRcptDt( biddingScheduleDto.getPqApplDocRcptDt() );
        mappingTarget.setTpEvalApplClseDt( biddingScheduleDto.getTpEvalApplClseDt() );
        mappingTarget.setArsltReqstdocRcptDt( biddingScheduleDto.getArsltReqstdocRcptDt() );
        mappingTarget.setDcmtgOprtnDt( biddingScheduleDto.getDcmtgOprtnDt() );
        mappingTarget.setRbidOpengDt( biddingScheduleDto.getRbidOpengDt() );
        mappingTarget.setRgstDt( biddingScheduleDto.getRgstDt() );
        mappingTarget.setChgDt( biddingScheduleDto.getChgDt() );
    }
}

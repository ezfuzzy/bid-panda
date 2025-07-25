package com.wasrem_WorkHive.wasrem.common.mapper;

import com.wasrem_WorkHive.wasrem.bidding.dto.BiddingResultDto;
import com.wasrem_WorkHive.wasrem.bidding.entity.BiddingResult;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-24T10:31:13+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class BiddingResultMapperImpl implements BiddingResultMapper {

    @Override
    public BiddingResult toEntity(BiddingResultDto dto) {
        if ( dto == null ) {
            return null;
        }

        BiddingResult.BiddingResultBuilder biddingResult = BiddingResult.builder();

        biddingResult.id( dto.getId() );
        biddingResult.bidNtceNo( dto.getBidNtceNo() );
        biddingResult.bidNtceOrd( dto.getBidNtceOrd() );
        biddingResult.bidClsfcNo( dto.getBidClsfcNo() );
        biddingResult.rbidNo( dto.getRbidNo() );
        biddingResult.ntceDivCd( dto.getNtceDivCd() );
        biddingResult.bidNtceNm( dto.getBidNtceNm() );
        biddingResult.prtcptCnum( dto.getPrtcptCnum() );
        biddingResult.bidwinnrNm( dto.getBidwinnrNm() );
        biddingResult.bidwinnrBizno( dto.getBidwinnrBizno() );
        biddingResult.bidwinnrCeoNm( dto.getBidwinnrCeoNm() );
        biddingResult.bidwinnrAdrs( dto.getBidwinnrAdrs() );
        biddingResult.bidwinnrTelNo( dto.getBidwinnrTelNo() );
        biddingResult.sucsfbidAmt( dto.getSucsfbidAmt() );
        biddingResult.sucsfbidRate( dto.getSucsfbidRate() );
        biddingResult.rlOpengDt( dto.getRlOpengDt() );
        biddingResult.dminsttCd( dto.getDminsttCd() );
        biddingResult.dminsttNm( dto.getDminsttNm() );
        biddingResult.rgstDt( dto.getRgstDt() );
        biddingResult.fnlSucsfDate( dto.getFnlSucsfDate() );
        biddingResult.fnlSucsfCorpOfcl( dto.getFnlSucsfCorpOfcl() );

        return biddingResult.build();
    }

    @Override
    public BiddingResultDto toDto(BiddingResult entity) {
        if ( entity == null ) {
            return null;
        }

        BiddingResultDto.BiddingResultDtoBuilder biddingResultDto = BiddingResultDto.builder();

        biddingResultDto.id( entity.getId() );
        biddingResultDto.bidNtceNo( entity.getBidNtceNo() );
        biddingResultDto.bidNtceOrd( entity.getBidNtceOrd() );
        biddingResultDto.bidClsfcNo( entity.getBidClsfcNo() );
        biddingResultDto.rbidNo( entity.getRbidNo() );
        biddingResultDto.ntceDivCd( entity.getNtceDivCd() );
        biddingResultDto.bidNtceNm( entity.getBidNtceNm() );
        biddingResultDto.prtcptCnum( entity.getPrtcptCnum() );
        biddingResultDto.bidwinnrNm( entity.getBidwinnrNm() );
        biddingResultDto.bidwinnrBizno( entity.getBidwinnrBizno() );
        biddingResultDto.bidwinnrCeoNm( entity.getBidwinnrCeoNm() );
        biddingResultDto.bidwinnrAdrs( entity.getBidwinnrAdrs() );
        biddingResultDto.bidwinnrTelNo( entity.getBidwinnrTelNo() );
        biddingResultDto.sucsfbidAmt( entity.getSucsfbidAmt() );
        biddingResultDto.sucsfbidRate( entity.getSucsfbidRate() );
        biddingResultDto.rlOpengDt( entity.getRlOpengDt() );
        biddingResultDto.dminsttCd( entity.getDminsttCd() );
        biddingResultDto.dminsttNm( entity.getDminsttNm() );
        biddingResultDto.rgstDt( entity.getRgstDt() );
        biddingResultDto.fnlSucsfDate( entity.getFnlSucsfDate() );
        biddingResultDto.fnlSucsfCorpOfcl( entity.getFnlSucsfCorpOfcl() );

        return biddingResultDto.build();
    }
}

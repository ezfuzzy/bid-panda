package com.ezfuzzy.bidpanda.common.mapper;

import com.ezfuzzy.bidpanda.bidding.dto.BiddingResultDto;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingNotice;
import com.ezfuzzy.bidpanda.bidding.entity.BiddingResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BiddingResultMapper {

    @Mapping(target = "biddingNoticeId", source = "biddingNotice.id")
    BiddingResultDto toDto(BiddingResult entity);

    @Mapping(target = "biddingNotice", source = "biddingNoticeId")
    BiddingResult toEntity(BiddingResultDto dto);

    default BiddingNotice map(Long id) {
        if (id == null) {
            return null;
        }
        BiddingNotice biddingNotice = new BiddingNotice();
        biddingNotice.setId(id);
        return biddingNotice;
    }
}
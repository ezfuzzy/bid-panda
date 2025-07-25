package com.wasrem_WorkHive.wasrem.common.mapper;

import com.wasrem_WorkHive.wasrem.bidding.dto.BiddingResultDto;
import com.wasrem_WorkHive.wasrem.bidding.entity.BiddingResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BiddingResultMapper {

    BiddingResult toEntity(BiddingResultDto dto);
    BiddingResultDto toDto(BiddingResult entity);
}
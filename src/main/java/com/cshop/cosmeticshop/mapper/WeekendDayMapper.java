package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.WeekendDayDto;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeekendDayMapper {

    WeekendDayDto toDto(WeekendDay weekendDay);

    WeekendDay fromDto(WeekendDayDto weekendDayDto);
}

package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkWeekMapper {

    WorkWeek fromDto(WorkWeekDto workWeekDto);

    WorkWeekDto toDto(WorkWeek workWeek);
}

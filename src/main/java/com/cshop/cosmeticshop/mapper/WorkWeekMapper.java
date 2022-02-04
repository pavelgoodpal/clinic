package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface WorkWeekMapper {

    WorkWeek fromDto(WorkWeekDto workWeekDto);

    WorkWeekDto toDto(WorkWeek workWeek);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WorkWeek marge(@MappingTarget WorkWeek target, WorkWeek source);
}

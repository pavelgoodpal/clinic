package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * WorkWeek mapper
 *
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface WorkWeekMapper {

    /**
     * Convert WorkWeekDto to WorkWeek object.
     *
     * @param workWeekDto dto
     * @return WorkWeek object
     */
    WorkWeek fromDto(WorkWeekDto workWeekDto);

    /**
     * Convert WorkWeek to WorkWeekDto.
     *
     * @param workWeek object
     * @return WorkWeekDto
     */
    WorkWeekDto toDto(WorkWeek workWeek);

    /**
     * Merge source WorkWeek to target WorkWeek.
     *
     * @param target WorkWeek
     * @param source WorkWeek
     * @return marged WorkWeek
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WorkWeek merge(@MappingTarget WorkWeek target, WorkWeek source);
}

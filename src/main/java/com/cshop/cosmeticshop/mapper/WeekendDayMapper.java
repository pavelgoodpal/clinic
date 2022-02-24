package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.WeekendDayDto;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.mapstruct.Mapper;

/**
 * Weekend day mapper
 *
 * @author PavelPa1
 */
@Mapper(componentModel = "spring")
public interface WeekendDayMapper {

    /**
     * Convert from weekend day to weekend day dto
     *
     * @param weekendDay weekend day object
     * @return weekend day dto
     */
    WeekendDayDto toDto(WeekendDay weekendDay);

    /**
     * Convert from weekend day dto to weekend day
     *
     * @param weekendDayDto weekend day dto
     * @return weekend day
     */
    WeekendDay fromDto(WeekendDayDto weekendDayDto);
}

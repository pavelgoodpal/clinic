package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.TreatmentDto;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import org.mapstruct.Mapper;

/**
 * Mapper for Treatment and TreatmentDto
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    /**
     * Convert TreatmentDto into Treatment
     * @param treatmentDto TreatmentDto convert into Treatment
     * @return TreatmentDto
     */
    Treatment fromDto(TreatmentDto treatmentDto);

    /**
     * Convert Treatment into TreatmentDto
     * @param treatment Treatment convert into TreatmentDto
     * @return Treatment
     */
    TreatmentDto toDto(Treatment treatment);
}

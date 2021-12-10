package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.TreatmentDto;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    Treatment TreatmentDtoToTreatment(TreatmentDto treatmentDto);
    TreatmentDto TreatmentToTreatmentDto(Treatment treatment);
}

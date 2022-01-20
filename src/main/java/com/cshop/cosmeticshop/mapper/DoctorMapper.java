package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.DoctorDto;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDto toDto(Doctor doctor);

    Doctor fromDto(DoctorDto doctorDto);
}

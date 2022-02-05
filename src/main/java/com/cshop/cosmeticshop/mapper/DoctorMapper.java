package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.DoctorDto;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import org.mapstruct.Mapper;

/**
 * Doctor mapper class.
 *
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    /**
     * Convert Doctor object to DoctorDto.
     *
     * @param doctor object
     * @return doctorDto
     */
    DoctorDto toDto(Doctor doctor);

    /**
     * Convert DoctorDto to Doctor object.
     *
     * @param doctorDto dto
     * @return Doctor object
     */
    Doctor fromDto(DoctorDto doctorDto);
}

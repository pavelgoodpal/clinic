package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDoctorMapper {

    @Mapping(target = "workWeek", ignore = true)
    @Mapping(target = "speciality", ignore = true)
    Doctor toDoctor(User user);


    User toUser(Doctor doctor);
}

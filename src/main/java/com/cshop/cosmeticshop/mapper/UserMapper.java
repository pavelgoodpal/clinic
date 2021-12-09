package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.UserDto;
import com.cshop.cosmeticshop.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

}

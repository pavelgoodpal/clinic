package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.UserDto;
import com.cshop.cosmeticshop.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

}

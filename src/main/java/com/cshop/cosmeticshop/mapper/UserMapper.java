package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.UserDto;
import com.cshop.cosmeticshop.domain.entity.User;
import org.mapstruct.Mapper;

/**
 * Mapper for User and UserDto
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convert User into UserDto
     * @param user User convert into UserDto
     * @return UserDto
     */
    UserDto UserToUserDto(User user);

    /**
     * Convert User into UserDto
     * @param userDto UserDto convert into User
     * @return User
     */
    User UserDtoToUser(UserDto userDto);

}

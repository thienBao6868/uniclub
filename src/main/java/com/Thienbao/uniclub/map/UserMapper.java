package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.UserDto;
import com.Thienbao.uniclub.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto convertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}

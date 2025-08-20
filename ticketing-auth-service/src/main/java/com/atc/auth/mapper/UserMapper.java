package com.atc.auth.mapper;

import com.atc.auth.dto.UserDto;
import com.atc.auth.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername());
    }
}


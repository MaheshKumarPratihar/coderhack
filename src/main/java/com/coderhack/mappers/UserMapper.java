package com.coderhack.mappers;

import com.coderhack.entities.User;
import com.coderhack.exchanges.user.UserRequest;
import com.coderhack.exchanges.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Component
public class UserMapper {
    public User toUser(UserRequest request) {
        if(request == null) return null;

        return User.builder()
                .id(request.id())
                .userName(request.userName())
                .score(request.score())
                .badges(new LinkedHashSet<>())
                .build();
    }

    public UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getScore(),
                user.getBadges()
        );
    }
}

package com.coderhack.services.user;

import com.coderhack.exchanges.user.UserRequest;
import com.coderhack.exchanges.user.UserResponse;

import java.util.List;

public interface IUserService {
    String register(UserRequest user);

    UserResponse getUserById(String userId);

    List<UserResponse> getAllUsers();

    UserResponse updateScore(String userId, int score);

    String deleteUser(String userId);
}

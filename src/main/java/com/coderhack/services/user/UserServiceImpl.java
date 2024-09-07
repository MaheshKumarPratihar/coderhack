package com.coderhack.services.user;

import com.coderhack.entities.Badge;
import com.coderhack.entities.User;
import com.coderhack.exceptions.UserAlreadyExistsException;
import com.coderhack.exceptions.UserNotFoundException;
import com.coderhack.exchanges.user.UserRequest;
import com.coderhack.exchanges.user.UserResponse;
import com.coderhack.mappers.UserMapper;
import com.coderhack.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public String register(UserRequest request) {
        if(this.userRepository.findById(request.id()).isPresent()){
            throw new UserAlreadyExistsException(String.format("User with id %s already exists !!", request.id()));
        }
        User user = this.userRepository.save(this.userMapper.toUser(request));
        return user.getId();
    }

    @Override
    public UserResponse getUserById(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with id %s not found", userId)
                        )
                );

        return this.userMapper
                .fromUser(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return this.userRepository
                .findAllByOrderByScore()
                .stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateScore(String userId, int score) {
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %s not found", userId))
        );

        user.setScore(user.getScore() + score);

        if(user.getScore() >= 1 && user.getScore() < 30){
            user.getBadges().add(Badge.CODE_NINJA);
        }else if(user.getScore() >= 30 && user.getScore() < 60){
            user.getBadges().add(Badge.CODE_CHAMP);
        }else if(user.getScore() >= 60 && user.getScore() <= 100){
            user.getBadges().add(Badge.CODE_MASTER);
        }

        this.userRepository.save(user);
        return this.userMapper.fromUser(user);
    }

    @Override
    public String deleteUser(String userId) {
        this.userRepository.deleteById(userId);
        return "Successfully deleted user !!";
    }
}

package one.terenin.service.impl;

import lombok.RequiredArgsConstructor;
import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import one.terenin.mapper.UserMapper;
import one.terenin.repository.UserRepository;
import one.terenin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        return null;
    }

    @Override
    public UserResponse login(UserRequest request) {
        return null;
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        return false;
    }

    @Override
    public UserResponse updateUserInfo(UserRegisterRequest request) {
        return null;
    }

    @Override
    public boolean makeSubscription(UserRequest request) {
        return false;
    }

    @Override
    public boolean bindCreditCard(UUID creditCardId) {
        return false;
    }
}

package one.terenin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import one.terenin.entity.UserEntity;
import one.terenin.exception.children.ServiceCallException;
import one.terenin.exception.common.ErrorCode;
import one.terenin.mapper.UserMapper;
import one.terenin.repository.UserRepository;
import one.terenin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())){
            log.error("{}{}{}", "User with username: ", request.getUsername(), " already exists");
            throw  new ServiceCallException(ErrorCode.SERVICE_CALL_REJECTED);
        }
        UserEntity entity = repository.save(mapper.map(request));
        return mapper.map(entity);
    }

    @Override
    public UserResponse login(UserRequest request) {
        UserEntity entity = repository.findUserEntityByUsername(request.getUsername())
                .orElseThrow(() -> new ServiceCallException(ErrorCode.SERVICE_CALL_REJECTED));
        return mapper.map(entity);
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

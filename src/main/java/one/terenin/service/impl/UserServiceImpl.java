package one.terenin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.terenin.dto.common.UserRole;
import one.terenin.dto.request.CreditCardRequest;
import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import one.terenin.entity.UserEntity;
import one.terenin.exception.BaseException;
import one.terenin.exception.children.ServiceCallException;
import one.terenin.exception.children.ServiceNotFoundException;
import one.terenin.exception.common.ErrorCode;
import one.terenin.mapper.UserMapper;
import one.terenin.repository.UserRepository;
import one.terenin.security.details.user.UserDetailsImpl;
import one.terenin.service.UserService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.security.Security;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final DiscoveryClient client;
    private final RestTemplate restTemplate;
    private final PasswordEncoder encoder;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())){
            log.error("{}{}{}", "User with username: ", request.getUsername(), " already exists");
            throw  new ServiceCallException(ErrorCode.SERVICE_CALL_REJECTED);
        }
        UserRegisterRequest withHashedPassword = request.toBuilder().password(encoder.encode(request.getPassword())).build();
        UserEntity entity = repository.save(mapper.map(withHashedPassword));
        return mapper.map(entity);
    }

    @Override
    public UserResponse login(UserRequest request) {
        UserEntity entity = repository.findUserEntityByUsername(request.getUsername())
                .orElseThrow(() -> new ServiceCallException(ErrorCode.SERVICE_CALL_REJECTED));
        if (encoder.matches(request.getPassword() ,entity.getEncodedPassword())){
            return mapper.map(entity);
        } else {
            throw new BaseException(ErrorCode.USER_PASSWORD_INVALID);
        }
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        return false;
    }

    @Override
    public UserResponse updateUserInfo(UserRegisterRequest request) {
        return null;
    }

    // return the confirmation url from yookassa to client browser or frontend
    @Override
    public String makeSubscription() {
       URI serviceURI = client.getInstances("musalog-payment-service").stream()
               .map(ServiceInstance::getUri)
               .findFirst()
               .map(si -> si.resolve("/api/v1/payment/pay/for/subscription"))
               .orElseThrow(() -> new ServiceNotFoundException(ErrorCode.SERVICE_NOT_FOUND));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(serviceURI, String.class);
        if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()){
            throw new ServiceCallException(ErrorCode.SERVICE_CALL_REJECTED);
        }else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
            UserEntity entity = details.getEntity();
            entity.setRole(UserRole.SUBSCRIBER);
            repository.save(entity);
            return responseEntity.getBody();
        }
    }

    @Override
    public boolean bindCreditCard(UUID creditCardId) {
        return false;
    }

    @Override
    public boolean registerCreditCard(CreditCardRequest request) {
        return false;
    }
}

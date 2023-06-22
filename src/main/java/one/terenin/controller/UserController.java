package one.terenin.controller;

import lombok.RequiredArgsConstructor;
import one.terenin.api.UserApi;
import one.terenin.dto.request.CreditCardRequest;
import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import one.terenin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<UserResponse> register(UserRegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    public ResponseEntity<UserResponse> login(UserRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @Override
    public ResponseEntity<UserResponse> loginByUsername(String username) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> existsByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> updateUserInfo(UserRegisterRequest request) {
        return ResponseEntity.ok(service.updateUserInfo(request));
    }

    @Override
    public ResponseEntity<Boolean> makeSubscription(UserRequest request) {
        return ResponseEntity.ok(service.makeSubscription(request));
    }

    @Override
    public ResponseEntity<Boolean> bindCreditCard(UUID cardId) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> registerCreditCard(CreditCardRequest request) {
        return null;
    }
}

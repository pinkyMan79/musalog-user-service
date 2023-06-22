package one.terenin.service;

import one.terenin.dto.request.CreditCardRequest;
import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse register(UserRegisterRequest request);
    UserResponse login(UserRequest request);
    boolean existsByUsernameAndPassword(String username, String password);
    UserResponse updateUserInfo(UserRegisterRequest request);
    boolean makeSubscription(UserRequest request);
    boolean bindCreditCard(UUID creditCardId);
    boolean registerCreditCard(CreditCardRequest request);

}

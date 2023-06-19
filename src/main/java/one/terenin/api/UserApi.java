package one.terenin.api;

import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/v1/user")
public interface UserApi {

    @PostMapping("/register")
    ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request);

    @GetMapping("/login")
    ResponseEntity<UserResponse> login(@RequestBody UserRequest request);

    @GetMapping("/exists/by/{username}/{password}")
    ResponseEntity<Boolean> existsByUsernameAndPassword(@PathVariable String username,
                                                     @PathVariable String password);

    @PatchMapping("/update")
    ResponseEntity<UserResponse> updateUserInfo(@RequestBody UserRegisterRequest request);

    // able to checks from token in payment service
    @PatchMapping("/check/subscription")
    ResponseEntity<Boolean> makeSubscription(UserRequest request);

    // boolean -> rejected or no
    @PatchMapping("/bind/card/{card_id}")
    ResponseEntity<Boolean> bindCreditCard(@PathVariable("card_id") UUID cardId);

}

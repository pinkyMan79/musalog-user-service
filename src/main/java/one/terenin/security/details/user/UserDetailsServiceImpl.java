package one.terenin.security.details.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.terenin.entity.UserEntity;
import one.terenin.exception.children.UserException;
import one.terenin.exception.common.ErrorCode;
import one.terenin.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = repository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        log.info("{}{}", "INFO: find user entity with login: ", entity.toString());
        return new UserDetailsImpl(entity);
    }
}


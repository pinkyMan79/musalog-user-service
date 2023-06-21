package one.terenin.repository;

import one.terenin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByUsernameAndEncodedPassword(String username, String encodedPassword);
    boolean existsByUsername(String username);
    Optional<UserEntity> findUserEntityById(UUID id);
    Optional<UserEntity> findUserEntityByUsernameAndEncodedPassword(String username, String encodedPassword);
    //void updateUserEntityByUsername(String username);
    Optional<UserEntity> findUserEntityByUsername(String username);

}

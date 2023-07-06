package one.terenin.entity;

import com.technokratos.springbootadministrationpanel.annotations.GenerateCRUD;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.terenin.dto.common.UserRole;
import one.terenin.entity.parent.AbstractEntity;
import one.terenin.repository.UserRepository;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ussr_table")
@GenerateCRUD(repositoryClass = UserRepository.class)
public class UserEntity extends AbstractEntity {
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "credit_card_id", nullable = true)
    private UUID creditCardId;
    @Column(name = "password", nullable = false)
    private String encodedPassword;
}

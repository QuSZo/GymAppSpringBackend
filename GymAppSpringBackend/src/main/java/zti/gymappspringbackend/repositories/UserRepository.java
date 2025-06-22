package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.User;

import java.util.UUID;

/**
 * Repozytorium JPA dla encji User.
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}

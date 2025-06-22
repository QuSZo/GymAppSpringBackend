package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.User;
import zti.gymappspringbackend.entities.Workout;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    List<Workout> findAllByUser(User user);
    Optional<Workout> findByIdAndUser(UUID id, User user);
    Optional<Workout> findByDateAndUser(LocalDate date, User user);
    boolean existsByIdAndUser(UUID id, User user);
}

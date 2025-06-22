package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.Exercise;

import java.util.UUID;

/**
 * Repozytorium JPA dla encji Exercise.
 */
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}

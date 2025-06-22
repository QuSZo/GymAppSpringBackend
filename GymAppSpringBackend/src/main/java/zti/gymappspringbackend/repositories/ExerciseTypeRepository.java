package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.ExerciseType;

import java.util.UUID;

/**
 * Repozytorium JPA dla encji ExerciseType.
 */
public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, UUID> {
}

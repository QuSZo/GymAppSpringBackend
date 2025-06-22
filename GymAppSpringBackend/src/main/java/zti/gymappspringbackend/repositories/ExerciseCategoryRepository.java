package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.ExerciseCategory;

import java.util.UUID;

/**
 * Repozytorium JPA dla encji ExerciseCategory.
 */
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, UUID> {
    ExerciseCategory findByName(String name);
}

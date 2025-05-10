package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.ExerciseCategory;

import java.util.UUID;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, UUID> {
    ExerciseCategory findByName(String name);
}

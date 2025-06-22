package zti.gymappspringbackend.dtos.exercise;

import lombok.Data;
import zti.gymappspringbackend.entities.ExerciseType;

import java.util.UUID;

/**
 * DTO służące do tworzenia nowego ćwiczenia.
 */
@Data
public class CreateExerciseDto {
    private UUID exerciseTypeId;
    private UUID workoutId;
}

package zti.gymappspringbackend.dtos.exerciseSet;

import lombok.Data;

import java.util.UUID;

/**
 * DTO służące do tworzenia lub aktualizacji serii ćwiczeniowych.
 */
@Data
public class CreateOrUpdateExerciseSetDto {
    private int quantity;
    private int reps;
    private UUID exerciseId;
}

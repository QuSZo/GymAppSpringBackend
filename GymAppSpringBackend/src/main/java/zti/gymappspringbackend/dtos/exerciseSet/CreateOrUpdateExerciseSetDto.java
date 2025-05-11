package zti.gymappspringbackend.dtos.exerciseSet;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrUpdateExerciseSetDto {
    private int quantity;
    private int reps;
    private UUID exerciseId;
}

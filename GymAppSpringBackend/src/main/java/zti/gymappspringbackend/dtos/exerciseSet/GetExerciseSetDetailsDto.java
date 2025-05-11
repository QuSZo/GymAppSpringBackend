package zti.gymappspringbackend.dtos.exerciseSet;

import lombok.Data;

import java.util.UUID;

@Data
public class GetExerciseSetDetailsDto {
    private UUID id;
    private int setNumber;
    private int quantity;
    private int reps;
}

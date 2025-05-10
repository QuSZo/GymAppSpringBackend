package zti.gymappspringbackend.dtos.exerciseType;

import lombok.Data;

import java.util.UUID;

@Data
public class GetExerciseTypeDto {
    private UUID id;
    private String name;
    private UUID exerciseCategoryId;
}

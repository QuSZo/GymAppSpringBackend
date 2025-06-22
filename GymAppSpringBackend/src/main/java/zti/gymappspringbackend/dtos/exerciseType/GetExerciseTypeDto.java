package zti.gymappspringbackend.dtos.exerciseType;

import lombok.Data;

import java.util.UUID;

/**
 * DTO służące do pobierania typów ćwiczeń.
 */
@Data
public class GetExerciseTypeDto {
    private UUID id;
    private String name;
    private UUID exerciseCategoryId;
}

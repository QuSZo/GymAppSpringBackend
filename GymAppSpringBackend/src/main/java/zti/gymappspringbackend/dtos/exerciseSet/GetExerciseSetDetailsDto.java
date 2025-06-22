package zti.gymappspringbackend.dtos.exerciseSet;

import lombok.Data;

import java.util.UUID;

/**
 * DTO służące do pobierania serii ćwiczeniowych.
 */
@Data
public class GetExerciseSetDetailsDto {
    private UUID id;
    private int setNumber;
    private int quantity;
    private int reps;
}

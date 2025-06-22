package zti.gymappspringbackend.dtos.workout;

import lombok.Data;
import zti.gymappspringbackend.dtos.exercise.GetExerciseDetailsDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * DTO służące do pobierania szczegółów treningu.
 */
@Data
public class GetWorkoutDetailsDto {
    private UUID id;
    private LocalDate date;
    private List<GetExerciseDetailsDto> exercises;
}
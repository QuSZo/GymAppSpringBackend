package zti.gymappspringbackend.dtos.exercise;

import lombok.Data;
import zti.gymappspringbackend.dtos.exerciseSet.GetExerciseSetDetailsDto;
import zti.gymappspringbackend.dtos.exerciseType.GetExerciseTypeDto;

import java.util.List;
import java.util.UUID;

/**
 * DTO służące do pobierania ćwiczeń.
 */
@Data
public class GetExerciseDetailsDto {
    private UUID id;
    private int exerciseNumber;
    private String exerciseTypeName;
    private List<GetExerciseSetDetailsDto> exerciseSets;
}

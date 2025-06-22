package zti.gymappspringbackend.dtos.exerciseSet;

import zti.gymappspringbackend.entities.ExerciseSet;

/**
 * Klasa pomocnicza (mapper) służąca do konwersji obiektów ExerciseSet na obiekty DTO
 */
public class ExerciseSetMapper {
    public static GetExerciseSetDetailsDto toGetDetailsDto(ExerciseSet exerciseSet){
        GetExerciseSetDetailsDto dto = new GetExerciseSetDetailsDto();
        dto.setId(exerciseSet.getId());
        dto.setSetNumber(exerciseSet.getSetNumber());
        dto.setQuantity(exerciseSet.getQuantity());
        dto.setReps(exerciseSet.getReps());
        return dto;
    }
}

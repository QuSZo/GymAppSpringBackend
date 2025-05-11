package zti.gymappspringbackend.dtos.exercise;

import zti.gymappspringbackend.dtos.exerciseSet.ExerciseSetMapper;
import zti.gymappspringbackend.dtos.exerciseType.ExerciseTypeMapper;
import zti.gymappspringbackend.entities.Exercise;

public class ExerciseMapper {

    public static GetExerciseDetailsDto toGetDetailsDto(Exercise exercise){
        GetExerciseDetailsDto dto = new GetExerciseDetailsDto();
        dto.setId(exercise.getId());
        dto.setExerciseNumber(exercise.getExerciseNumber());
        dto.setExerciseTypeName(exercise.getExerciseType().getName());
        dto.setExerciseSets(exercise.getExerciseSets().stream().map(ExerciseSetMapper::toGetDetailsDto).toList());
        return dto;
    }
}

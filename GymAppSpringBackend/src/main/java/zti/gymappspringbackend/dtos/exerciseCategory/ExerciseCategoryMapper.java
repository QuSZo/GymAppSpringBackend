package zti.gymappspringbackend.dtos.exerciseCategory;

import org.springframework.stereotype.Component;
import zti.gymappspringbackend.dtos.exerciseType.ExerciseTypeMapper;
import zti.gymappspringbackend.entities.ExerciseCategory;

@Component
public class ExerciseCategoryMapper {

    public static GetExerciseCategoryDto toGetExerciseCategoryDto(ExerciseCategory exerciseCategory){
        GetExerciseCategoryDto dto = new GetExerciseCategoryDto();
        dto.setId(exerciseCategory.getId());
        dto.setName(exerciseCategory.getName());
        dto.setGetExerciseTypesDto(exerciseCategory.getExerciseTypes().stream().map(ExerciseTypeMapper::toGetExerciseTypeDto).toList());
        return dto;
    }
}

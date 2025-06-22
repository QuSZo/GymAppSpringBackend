package zti.gymappspringbackend.dtos.exerciseType;

import org.springframework.stereotype.Component;
import zti.gymappspringbackend.entities.ExerciseType;

/**
 * Klasa pomocnicza (mapper) służąca do konwersji obiektów ExerciseType na obiekty DTO
 */
@Component
public class ExerciseTypeMapper {

    public static GetExerciseTypeDto toGetExerciseTypeDto(ExerciseType exerciseType){
        GetExerciseTypeDto dto = new GetExerciseTypeDto();
        dto.setId(exerciseType.getId());
        dto.setName(exerciseType.getName());
        dto.setExerciseCategoryId(exerciseType.getExerciseCategory().getId());
        return dto;
    }
}

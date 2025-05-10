package zti.gymappspringbackend.dtos.exerciseCategory;

import lombok.Data;
import zti.gymappspringbackend.dtos.exerciseType.GetExerciseTypeDto;

import java.util.List;
import java.util.UUID;

@Data
public class GetExerciseCategoryDto {
    private UUID id;
    private String name;
    private List<GetExerciseTypeDto> getExerciseTypesDto;
}

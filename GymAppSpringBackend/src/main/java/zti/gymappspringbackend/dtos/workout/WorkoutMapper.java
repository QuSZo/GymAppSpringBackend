package zti.gymappspringbackend.dtos.workout;

import org.springframework.stereotype.Component;
import zti.gymappspringbackend.dtos.exercise.ExerciseMapper;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.Workout;

import java.util.Comparator;

/**
 * Klasa pomocnicza (mapper) służąca do konwersji obiektów Workout na obiekty DTO
 */
@Component
public class WorkoutMapper {

    public static GetWorkoutDto toGetDto(Workout workout) {
        GetWorkoutDto dto = new GetWorkoutDto();
        dto.setId(workout.getId());
        dto.setDate(workout.getDate());
        return dto;
    }

    public static GetWorkoutDetailsDto toGetDetailsDto(Workout workout){
        GetWorkoutDetailsDto dto = new GetWorkoutDetailsDto();
        dto.setId(workout.getId());
        dto.setDate(workout.getDate());
        dto.setExercises(workout.getExercises().stream()
                .sorted(Comparator.comparing(Exercise::getExerciseNumber))
                .map(ExerciseMapper::toGetDetailsDto)
                .toList());
        return dto;
    }
}

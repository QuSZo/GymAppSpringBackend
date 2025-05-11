package zti.gymappspringbackend.dtos.workout;

import org.springframework.stereotype.Component;
import zti.gymappspringbackend.dtos.exercise.ExerciseMapper;
import zti.gymappspringbackend.entities.Workout;

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
        dto.setExercises(workout.getExercises().stream().map(ExerciseMapper::toGetDetailsDto).toList());
        return dto;
    }
}

package zti.gymappspringbackend.dtos.workout;

import org.springframework.stereotype.Component;
import zti.gymappspringbackend.entities.Workout;

@Component
public class WorkoutMapper {

    public static GetWorkoutDto toGetDto(Workout workout) {
        GetWorkoutDto dto = new GetWorkoutDto();
        dto.setId(workout.getId());
        dto.setDate(workout.getDate());
        return dto;
    }
}

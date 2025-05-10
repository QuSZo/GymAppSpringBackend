package zti.gymappspringbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zti.gymappspringbackend.dtos.workout.CreateWorkoutDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;
import zti.gymappspringbackend.repositories.WorkoutRepository;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;

    public Workout createWorkout(CreateWorkoutDto request) {
        if (workoutRepository.findByDate(request.getDate()) != null) {
            throw new BadRequestGymAppException("Workout of " + request.getDate() + " already exists");
        }

        Workout workout = new Workout(request.getDate());

        ExerciseType exerciseType = exerciseTypeRepository.findById(request.getExerciseTypeId())
                .orElseThrow(() -> new BadRequestGymAppException("Exercise type not found"));

        Exercise exercise = new Exercise(1, exerciseType, workout);
        workout.addExercise(exercise);

        return workoutRepository.save(workout);
    }
}

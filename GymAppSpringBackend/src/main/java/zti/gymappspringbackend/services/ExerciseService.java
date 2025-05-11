package zti.gymappspringbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zti.gymappspringbackend.dtos.exercise.CreateExerciseDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseRepository;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;
import zti.gymappspringbackend.repositories.WorkoutRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;

    public Exercise saveExercise(CreateExerciseDto exerciseDto) {
        Workout workout = workoutRepository.findById(exerciseDto.getWorkoutId())
                .orElseThrow(() -> new BadRequestGymAppException("Workout not found"));

//        if (!workout.getUser().getId().equals(currentUserService.getUserId())) {
//            throw new WorkoutNotFoundException(command.getWorkoutId());
//        }

        ExerciseType exerciseType = exerciseTypeRepository.findById(exerciseDto.getExerciseTypeId())
                .orElseThrow(() -> new BadRequestGymAppException("Exercise type not found"));

        List<Exercise> exercises = workoutRepository.findById(exerciseDto.getWorkoutId()).get().getExercises();
        int nextExerciseNumber = exercises.isEmpty() ? 1 :
                exercises.get(exercises.size() - 1).getExerciseNumber() + 1;

        Exercise exercise = new Exercise(nextExerciseNumber, exerciseType, workout);
        exerciseRepository.save(exercise);

        return exercise;
    }
}

package zti.gymappspringbackend.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zti.gymappspringbackend.dtos.exercise.ChangeDirectionEnum;
import zti.gymappspringbackend.dtos.exercise.CreateExerciseDto;
import zti.gymappspringbackend.dtos.exercise.PatchExerciseDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseRepository;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;
import zti.gymappspringbackend.repositories.WorkoutRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

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

    public Exercise updateExerciseNumber(UUID exerciseId, PatchExerciseDto dto){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new BadRequestGymAppException("Exercise not found"));

//        UUID currentUserId = currentUserService.getUserId();
//        if (!exercise.getWorkout().getUser().getId().equals(currentUserId)) {
//            throw new ExerciseNotFoundException(command.getId());
//        }

        List<Exercise> exercises = workoutRepository.findById(exercise.getWorkout().getId())
                .get().getExercises()
                .stream().sorted(Comparator.comparing(Exercise::getExerciseNumber))
                .toList();

        int currentIndex = IntStream.range(0, exercises.size())
                .filter(i -> exercises.get(i).getId().equals(exerciseId))
                .findFirst()
                .orElseThrow(() -> new BadRequestGymAppException("Exercise not found"));

        int swapIndex = switch (dto.getChangeDirection()) {
            case UP -> currentIndex - 1;
            case DOWN -> currentIndex + 1;
        };

        if (swapIndex < 0 || swapIndex >= exercises.size()) {
            throw new BadRequestGymAppException("Invalid change direction value");
        }

        Exercise exerciseToSwap = exercises.get(swapIndex);

        int tempNumber = exerciseToSwap.getExerciseNumber();
        exerciseToSwap.updateExerciseNumber(exercise.getExerciseNumber());
        exercise.updateExerciseNumber(tempNumber);

        exerciseRepository.saveAll(List.of(exercise, exerciseToSwap));

        return exercise;
    }

    @Transactional
    public void deleteExercise(UUID exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new BadRequestGymAppException("Exercise not found"));

//        UUID currentUserId = currentUserService.getUserId();
//        if (!exercise.getWorkout().getUser().getId().equals(currentUserId)) {
//            throw new ExerciseNotFoundException(exerciseId);
//        }

        List<Exercise> exercises = workoutRepository.findById(exercise.getWorkout().getId()).get().getExercises();

        if (exercises.size() == 1) {
            workoutRepository.deleteById(exercise.getWorkout().getId());
        } else {
            Workout workout = exercise.getWorkout();
            workout.getExercises().removeIf(e -> e.getId().equals(exerciseId));
            workoutRepository.save(workout);
        }
    }
}

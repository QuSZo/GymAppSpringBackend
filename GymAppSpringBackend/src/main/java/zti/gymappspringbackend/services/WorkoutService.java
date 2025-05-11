package zti.gymappspringbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zti.gymappspringbackend.dtos.workout.CopyWorkoutDto;
import zti.gymappspringbackend.dtos.workout.CreateWorkoutDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseSet;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;
import zti.gymappspringbackend.repositories.WorkoutRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;

    public Workout createWorkout(CreateWorkoutDto request) {
        if(workoutRepository.findByDate(request.getDate()).isPresent())
        {
            throw new BadRequestGymAppException("Workout of " + request.getDate() + " already exists");
        }

        Workout workout = new Workout(request.getDate());

        ExerciseType exerciseType = exerciseTypeRepository.findById(request.getExerciseTypeId())
                .orElseThrow(() -> new BadRequestGymAppException("Exercise type not found"));

        Exercise exercise = new Exercise(1, exerciseType, workout);
        workout.addExercise(exercise);

        return workoutRepository.save(workout);
    }

    public Workout copyWorkout(CopyWorkoutDto dto) {
//        UUID userId = currentUserService.getUserId();
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException(userId));
//
//        workoutRepository.findByDateAndUserId(destinationDate, userId)
//                .ifPresent(w -> { throw new WorkoutWithTheSameDateException(sourceDate); });

//        Workout copyWorkoutFrom = workoutRepository.findByDateAndUserId(sourceDate, userId)
//                .orElseThrow(() -> new WorkoutByDateNotFoundException(sourceDate));

        workoutRepository.findByDate(dto.getDestinationDate())
                .ifPresent(w -> { throw new BadRequestGymAppException("Workout of " + dto.getDestinationDate() + " already exists"); });

        Workout copyWorkoutFrom = workoutRepository.findByDate(dto.getSourceDate())
                .orElseThrow(() -> new BadRequestGymAppException("Source workout not found"));

        Workout newWorkout = new Workout(dto.getDestinationDate());

        List<Exercise> exercisesToCopy = copyWorkoutFrom.getExercises().stream()
                .sorted(Comparator.comparing(Exercise::getExerciseNumber))
                .toList();

        int exerciseNumber = 1;
        for (Exercise exercise : exercisesToCopy) {
            Exercise newExercise = new Exercise(exerciseNumber++, exercise.getExerciseType(), newWorkout);

            List<ExerciseSet> orderedSets = exercise.getExerciseSets().stream()
                    .sorted(Comparator.comparing(ExerciseSet::getSetNumber))
                    .toList();

            int setNumber = 1;
            for (ExerciseSet set : orderedSets) {
                ExerciseSet newSet = new ExerciseSet(setNumber++, set.getQuantity(), set.getReps(), newExercise);
                newExercise.addExerciseSet(newSet);
            }

            newWorkout.addExercise(newExercise);
        }

        workoutRepository.save(newWorkout);
        return newWorkout;
    }
}

package zti.gymappspringbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zti.gymappspringbackend.dtos.exerciseSet.CreateOrUpdateExerciseSetDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseSet;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseRepository;
import zti.gymappspringbackend.repositories.ExerciseSetRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseSet saveExerciseSet(CreateOrUpdateExerciseSetDto dto) {
        Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
                .orElseThrow(() -> new BadRequestGymAppException("Exercise not found"));

//        if (!exercise.getWorkout().getUser().getId().equals(currentUserService.getUserId())) {
//            throw new ExerciseNotFoundException(command.getExerciseId());
//        }

        List<ExerciseSet> sets = exercise.getExerciseSets()
                .stream()
                .sorted(Comparator.comparingInt(ExerciseSet::getSetNumber))
                .toList();

        int nextSetNumber = sets.isEmpty() ? 1 : sets.get(sets.size() - 1).getSetNumber() + 1;

        ExerciseSet exerciseSet = new ExerciseSet(
                nextSetNumber,
                dto.getQuantity(),
                dto.getReps(),
                exercise
        );

        exerciseSetRepository.save(exerciseSet);
        return exerciseSet;
    }
}

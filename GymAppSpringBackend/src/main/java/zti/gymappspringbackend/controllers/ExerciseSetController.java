package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.exerciseSet.CreateOrUpdateExerciseSetDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.entities.ExerciseSet;
import zti.gymappspringbackend.entities.User;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.ExerciseSetRepository;
import zti.gymappspringbackend.repositories.UserRepository;
import zti.gymappspringbackend.services.ExerciseSetService;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise-sets")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseSetService exerciseSetService;
    private final UserRepository userRepository;

//    @GetMapping
//    public ResponseEntity<List<ExerciseSet>> getAll() {
//        List<ExerciseSet> exerciseSets = exerciseSetRepository.findAll();
//        return ResponseEntity.ok(exerciseSets);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ExerciseSet> getById(@PathVariable UUID id) {
//        Optional<ExerciseSet> exerciseSet = exerciseSetRepository.findById(id);
//        return exerciseSet.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping
    public ResponseEntity<ExerciseSet> create(@RequestBody CreateOrUpdateExerciseSetDto dto) {
        ExerciseSet savedExerciseSet = exerciseSetService.saveExerciseSet(dto);
        return ResponseEntity.created(URI.create("/api/exercise-sets/" + savedExerciseSet.getId())).body(savedExerciseSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSet> update(@PathVariable UUID id, @RequestBody CreateOrUpdateExerciseSetDto dto) {
        return exerciseSetRepository.findById(id)
                .map(existing -> {
                    UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new BadRequestGymAppException());

                    if (!existing.getExercise().getWorkout().getUser().equals(user)) {
                        throw new BadRequestGymAppException();
                    }

                    existing.update(dto.getQuantity(), dto.getReps());
                    ExerciseSet exerciseSet = exerciseSetRepository.save(existing);
                    return ResponseEntity.ok(exerciseSet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ExerciseSet exerciseSet = exerciseSetRepository.findById(id)
                .orElseThrow(() -> new BadRequestGymAppException("Exercise not found"));

        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestGymAppException());

        if (!exerciseSet.getExercise().getWorkout().getUser().equals(user)) {
            throw new BadRequestGymAppException();
        }

        exerciseSetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

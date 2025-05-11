package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.exercise.CreateExerciseDto;
import zti.gymappspringbackend.dtos.exercise.PatchExerciseDto;
import zti.gymappspringbackend.entities.Exercise;
import zti.gymappspringbackend.repositories.ExerciseRepository;
import zti.gymappspringbackend.services.ExerciseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable UUID id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        return exercise.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody CreateExerciseDto exercise) {
        Exercise savedExercise = exerciseService.saveExercise(exercise);
        return ResponseEntity.created(URI.create("/api/exercises/" + savedExercise.getId())).body(savedExercise);
    }

    @PatchMapping("/{id}/exercise-number")
    public ResponseEntity<Exercise> patch(@PathVariable UUID id, @RequestBody PatchExerciseDto dto) {
        Exercise savedExercise = exerciseService.updateExerciseNumber(id, dto);
        return ResponseEntity.ok(savedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}

package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.entities.ExerciseSet;
import zti.gymappspringbackend.repositories.ExerciseSetRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise-sets")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetRepository exerciseSetRepository;

    @GetMapping
    public ResponseEntity<List<ExerciseSet>> getAll() {
        List<ExerciseSet> exerciseSets = exerciseSetRepository.findAll();
        return ResponseEntity.ok(exerciseSets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseSet> getById(@PathVariable UUID id) {
        Optional<ExerciseSet> exerciseSet = exerciseSetRepository.findById(id);
        return exerciseSet.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExerciseSet> create(@RequestBody ExerciseSet exerciseSet) {
        ExerciseSet savedExerciseSet = exerciseSetRepository.save(exerciseSet);
        return ResponseEntity.created(URI.create("/api/exercise-sets/" + savedExerciseSet.getId())).body(savedExerciseSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (exerciseSetRepository.existsById(id)) {
            exerciseSetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

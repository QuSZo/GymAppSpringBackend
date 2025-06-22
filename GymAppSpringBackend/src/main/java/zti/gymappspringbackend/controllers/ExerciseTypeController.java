package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.exerciseType.ExerciseTypeMapper;
import zti.gymappspringbackend.dtos.exerciseType.GetExerciseTypeDto;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise-types")
@RequiredArgsConstructor
public class ExerciseTypeController {

    private final ExerciseTypeRepository exerciseTypeRepository;

    @GetMapping
    public ResponseEntity<List<GetExerciseTypeDto>> getAll() {
        List<GetExerciseTypeDto> exerciseTypes = exerciseTypeRepository.findAll().stream().map(ExerciseTypeMapper::toGetExerciseTypeDto).toList();
        return ResponseEntity.ok(exerciseTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseType> getById(@PathVariable UUID id) {
        Optional<ExerciseType> exerciseType = exerciseTypeRepository.findById(id);
        return exerciseType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<ExerciseType> create(@RequestBody ExerciseType exerciseType) {
//        ExerciseType savedExerciseType = exerciseTypeRepository.save(exerciseType);
//        return ResponseEntity.created(URI.create("/api/exercise-types/" + savedExerciseType.getId())).body(savedExerciseType);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        if (exerciseTypeRepository.existsById(id)) {
//            exerciseTypeRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}

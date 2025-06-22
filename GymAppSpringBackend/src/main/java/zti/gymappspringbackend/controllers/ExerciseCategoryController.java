package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.exerciseCategory.ExerciseCategoryMapper;
import zti.gymappspringbackend.dtos.exerciseCategory.GetExerciseCategoryDto;
import zti.gymappspringbackend.entities.ExerciseCategory;
import zti.gymappspringbackend.repositories.ExerciseCategoryRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise-categories")
@RequiredArgsConstructor
public class ExerciseCategoryController {

    private final ExerciseCategoryRepository exerciseCategoryRepository;

    @GetMapping
    public ResponseEntity<List<GetExerciseCategoryDto>> getAll() {
        List<GetExerciseCategoryDto> exerciseCategories = exerciseCategoryRepository.findAll().stream().map(ExerciseCategoryMapper::toGetExerciseCategoryDto).toList();
        return ResponseEntity.ok(exerciseCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseCategory> getById(@PathVariable UUID id) {
        Optional<ExerciseCategory> exerciseCategory = exerciseCategoryRepository.findById(id);
        return exerciseCategory.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<ExerciseCategory> create(@RequestBody ExerciseCategory exerciseCategory) {
//        ExerciseCategory savedExerciseCategory = exerciseCategoryRepository.save(exerciseCategory);
//        return ResponseEntity.created(URI.create("/api/exercise-categories/" + savedExerciseCategory.getId())).body(savedExerciseCategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        if (exerciseCategoryRepository.existsById(id)) {
//            exerciseCategoryRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}

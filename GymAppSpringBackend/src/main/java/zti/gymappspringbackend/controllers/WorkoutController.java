package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.workout.CreateWorkoutDto;
import zti.gymappspringbackend.dtos.workout.GetWorkoutDto;
import zti.gymappspringbackend.dtos.workout.WorkoutMapper;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.repositories.WorkoutRepository;
import zti.gymappspringbackend.services.WorkoutService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepository workoutRepository;
    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<GetWorkoutDto>> getAll() {
        List<GetWorkoutDto> workouts = workoutRepository.findAll().stream().map(WorkoutMapper::toGetDto).toList();
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getById(@PathVariable UUID id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        return workout.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody CreateWorkoutDto request) {
        Workout savedWorkout = workoutService.createWorkout(request);
        return ResponseEntity.created(URI.create("/api/workouts/" + savedWorkout.getId())).body(savedWorkout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package zti.gymappspringbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zti.gymappspringbackend.dtos.workout.*;
import zti.gymappspringbackend.entities.User;
import zti.gymappspringbackend.entities.Workout;
import zti.gymappspringbackend.exceptions.BadRequestGymAppException;
import zti.gymappspringbackend.repositories.UserRepository;
import zti.gymappspringbackend.repositories.WorkoutRepository;
import zti.gymappspringbackend.services.WorkoutService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepository workoutRepository;
    private final WorkoutService workoutService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<GetWorkoutDto>> getAll() {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestGymAppException());
        List<GetWorkoutDto> workouts = workoutRepository.findAllByUser(user).stream().map(WorkoutMapper::toGetDto).toList();
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetWorkoutDetailsDto> getById(@PathVariable UUID id) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestGymAppException());
        Optional<GetWorkoutDetailsDto> workout = workoutRepository.findByIdAndUser(id, user).map(WorkoutMapper::toGetDetailsDto);
        return workout.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<GetWorkoutDetailsDto> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestGymAppException());
        Optional<GetWorkoutDetailsDto> workout = workoutRepository.findByDateAndUser(date, user).map(WorkoutMapper::toGetDetailsDto);
        return workout.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody CreateWorkoutDto request) {
        Workout savedWorkout = workoutService.createWorkout(request);
        return ResponseEntity.created(URI.create("/api/workouts/" + savedWorkout.getId())).body(savedWorkout);
    }

    @PostMapping("/copy")
    public ResponseEntity<Workout> copy(@RequestBody CopyWorkoutDto request){
        Workout savedWorkout = workoutService.copyWorkout(request);
        return ResponseEntity.created(URI.create("/api/workouts/" + savedWorkout.getId())).body(savedWorkout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestGymAppException());

        if (workoutRepository.existsByIdAndUser(id, user)) {
            workoutRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

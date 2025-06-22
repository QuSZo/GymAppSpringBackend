package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

/**
 * Encja reprezentująca trening w systemie.
 */
@Getter
@Entity
@NoArgsConstructor
public class Workout {

    @Id @GeneratedValue
    private UUID id;

    private LocalDate date;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();

    public Workout(LocalDate date, User user) {
        this.date = date;
        this.user = user;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setWorkout(this);
    }
}
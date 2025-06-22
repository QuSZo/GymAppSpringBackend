package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Encja reprezentująca ćwiczenie w systemie.
 */
@Getter
@Entity
@NoArgsConstructor
public class Exercise {

    @Id @GeneratedValue
    private UUID id;

    private int exerciseNumber;

    @ManyToOne(optional = false)
    private ExerciseType exerciseType;

    @ManyToOne(optional = false) @JoinColumn(name = "workout_id")
    private Workout workout;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> exerciseSets = new ArrayList<>();

    public Exercise(int exerciseNumber, ExerciseType exerciseType, Workout workout) {
        this.exerciseNumber = exerciseNumber;
        this.exerciseType = exerciseType;
        this.workout = workout;
    }

    public void addExerciseSet(ExerciseSet exerciseSet) {
        exerciseSets.add(exerciseSet);
        exerciseSet.setExercise(this);
    }

    public void updateExerciseNumber(int exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}

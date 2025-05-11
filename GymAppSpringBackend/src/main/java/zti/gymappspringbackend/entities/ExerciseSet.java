package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class ExerciseSet {

    @Id @GeneratedValue
    private UUID id;

    private int setNumber;
    private int quantity;
    private int reps;

    @ManyToOne(optional = false) @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public ExerciseSet(int setNumber, int quantity, int reps, Exercise exercise) {
        this.setNumber = setNumber;
        this.quantity = quantity;
        this.reps = reps;
        this.exercise = exercise;
    }

    public void update(int quantity, int reps) {
        this.quantity = quantity;
        this.reps = reps;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}

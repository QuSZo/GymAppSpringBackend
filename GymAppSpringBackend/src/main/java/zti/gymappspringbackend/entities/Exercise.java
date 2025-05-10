package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@NoArgsConstructor
public class Exercise {

    @Id @GeneratedValue @Getter
    private UUID id;

    @Getter
    private int exerciseNumber;

    @ManyToOne(optional = false) @Getter
    private ExerciseType exerciseType;

    @ManyToOne(optional = false) @JoinColumn(name = "workout_id") @Getter
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

    public List<ExerciseSet> getExerciseSets() {
        return Collections.unmodifiableList(exerciseSets);
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}

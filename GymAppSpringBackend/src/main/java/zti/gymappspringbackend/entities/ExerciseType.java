package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Encja reprezentująca typ ćwiczenia w systemie.
 */
@Entity
@NoArgsConstructor
@Getter
public class ExerciseType {

    @Id @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne(optional = false) @JoinColumn(name = "exerciseCategoryId")
    private ExerciseCategory exerciseCategory;

    public ExerciseType(String name, ExerciseCategory exerciseCategory) {
        this.name = name;
        this.exerciseCategory = exerciseCategory;
    }
}
package zti.gymappspringbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@NoArgsConstructor
public class ExerciseCategory {

    @Id @GeneratedValue @Getter
    private UUID id;

    @Getter
    private String name;

    @OneToMany(mappedBy = "exerciseCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExerciseType> exerciseTypes = new ArrayList<>();

    public ExerciseCategory(String name) {
        this.name = name;
    }

    public List<ExerciseType> getExerciseTypes() {
        return Collections.unmodifiableList(exerciseTypes);
    }
}

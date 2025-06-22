package zti.gymappspringbackend.dtos.exercise;

import lombok.Data;

/**
 * DTO służące do aktualizacji wybranego pola ćwiczenia,
 */
@Data
public class PatchExerciseDto {
    private ChangeDirectionEnum changeDirection;
}

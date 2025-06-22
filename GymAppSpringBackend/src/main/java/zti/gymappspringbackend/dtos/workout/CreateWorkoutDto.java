package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO służące do tworzenia nowego treningu.
 */
@Data
public class CreateWorkoutDto {
    private LocalDate date;
    private UUID exerciseTypeId;
}
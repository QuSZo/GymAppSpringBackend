package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateWorkoutDto {
    private LocalDate date;
    private UUID exerciseTypeId;
}
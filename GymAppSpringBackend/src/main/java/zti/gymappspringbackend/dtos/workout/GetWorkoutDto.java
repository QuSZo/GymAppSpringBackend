package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GetWorkoutDto {
    private UUID id;
    private LocalDate date;
}
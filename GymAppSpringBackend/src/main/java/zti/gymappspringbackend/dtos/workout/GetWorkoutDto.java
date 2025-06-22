package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO służące do pobierania treningów.
 */
@Data
public class GetWorkoutDto {
    private UUID id;
    private LocalDate date;
}
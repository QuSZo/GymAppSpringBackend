package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO służące do kopiowania treningów.
 */
@Data
public class CopyWorkoutDto {
    private LocalDate DestinationDate;
    private LocalDate SourceDate;
}

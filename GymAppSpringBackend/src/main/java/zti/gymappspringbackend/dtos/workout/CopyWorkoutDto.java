package zti.gymappspringbackend.dtos.workout;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CopyWorkoutDto {
    private LocalDate DestinationDate;
    private LocalDate SourceDate;
}

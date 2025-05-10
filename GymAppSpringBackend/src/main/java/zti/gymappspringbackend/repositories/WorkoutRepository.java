package zti.gymappspringbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.gymappspringbackend.entities.Workout;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    Workout findByDate(LocalDate date);
}

package zti.gymappspringbackend.seeder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import zti.gymappspringbackend.entities.ExerciseCategory;
import zti.gymappspringbackend.entities.ExerciseType;
import zti.gymappspringbackend.repositories.ExerciseCategoryRepository;
import zti.gymappspringbackend.repositories.ExerciseTypeRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements ApplicationRunner {

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedExerciseCategories();
        seedExerciseTypes();
    }

    private void seedExerciseCategories(){
        if (exerciseCategoryRepository.count() == 0) {
            List<ExerciseCategory> exerciseCategories = List.of(
                new ExerciseCategory("Barki"),
                new ExerciseCategory("Klatka piersiowa"),
                new ExerciseCategory("Plecy"),
                new ExerciseCategory("Biceps"),
                new ExerciseCategory("Triceps"),
                new ExerciseCategory("Brzuch"),
                new ExerciseCategory("Nogi")
            );

            exerciseCategoryRepository.saveAll(exerciseCategories);
        }
    }

    private void seedExerciseTypes(){
        if (exerciseTypeRepository.count() == 0) {
            List<ExerciseType> exerciseTypes = List.of(
                new ExerciseType("Wyciskanie sztangi na ławce poziomej", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Wyciskanie sztangi na ławce skośnej", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Wyciskanie hantli na ławce skośnej", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Wyciskanie hantli na ławce poziomej", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Rozpiętki na ławce poziomej", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Pompki na poręczach (dipy)", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Wyciskanie na maszynie hammer", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Rozpiętki na maszynie", exerciseCategoryRepository.findByName("Klatka piersiowa")),
                new ExerciseType("Prostowanie ramion z linkami wyciągu górnego", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Wyciskanie francuskie", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Wyciskanie sztangi wąskim chwytem", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Wyciskanie hantelki zza głowy", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Prostowanie ramienia w opadzie tułowia", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Pompki na poręczach (dipy)", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Prostowanie ramienia leżąc", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Prostowanie ramion z linkami wyciągu górnego", exerciseCategoryRepository.findByName("Triceps")),
                new ExerciseType("Unoszenie hantli bokiem", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Unoszenie hantli w przód", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Unoszenie hantli w opadzie tułowia", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Wyciskanie żołnierskie", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Wyciskanie hantli (Arnoldki)", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Wyciskanie na maszynie hammer", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Ściąganie linek od dołu na bramie", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Ściąganie linek w tył wyciągu stojąc", exerciseCategoryRepository.findByName("Barki")),
                new ExerciseType("Podciąganie na drążku", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Wiosłowanie na maszynie siedząc", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Ściąganie na wyciągu (narciarz)", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Półsztanga", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Podciąganie sztangi w opadzie tułowia", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Wiosłowanie hantelkami", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Ściąganie linki wyciągu górnego", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Ściąganie poziome szeroko na wyciągu", exerciseCategoryRepository.findByName("Plecy")),
                new ExerciseType("Uginanie ramion z hantlami", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion ze sztangą łamaną", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion siedząc na ławce skośnej", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion na bramie", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion z hantlami w oparciu o kolano", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion na wyciągu", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Uginanie ramion na modlitewniku", exerciseCategoryRepository.findByName("Biceps")),
                new ExerciseType("Unoszenie tułowia na ławce", exerciseCategoryRepository.findByName("Brzuch")),
                new ExerciseType("Unoszenie nóg", exerciseCategoryRepository.findByName("Brzuch")),
                new ExerciseType("Allahy na wyciągu", exerciseCategoryRepository.findByName("Brzuch")),
                new ExerciseType("Spinanie brzucha na maszynie", exerciseCategoryRepository.findByName("Brzuch")),
                new ExerciseType("Plank", exerciseCategoryRepository.findByName("Brzuch")),
                new ExerciseType("Przysiady ze sztangą", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Martwy ciąg", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Wykroki", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Wyciskanie na suwnicy", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Prostowanie na maszynie", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Uginanie na maszynie", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Spięcia łydek ma maszynie", exerciseCategoryRepository.findByName("Nogi")),
                new ExerciseType("Spięcia łydek z handelkami", exerciseCategoryRepository.findByName("Nogi"))
            );

            exerciseTypeRepository.saveAll(exerciseTypes);
        }
    }
}

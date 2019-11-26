package at.javatraining.trainings.services;

import at.javatraining.trainings.SpringBaseTest;
import at.javatraining.trainings.entities.Student;
import at.javatraining.trainings.entities.Trainer;
import at.javatraining.trainings.entities.Training;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingServiceTest extends SpringBaseTest {
    @Autowired
    TrainingService trainingService;

    @Value("#{trainerBernhard}")
    Trainer trainerBernhard;

    @Value("#{philipp}")
    Student studentPhilipp;

    @Value("#{david}")
    Student studentDavid;

    @BeforeEach
    void setUp() {
        if(trainingService instanceof TrainingServiceMemImpl) {
            ((TrainingServiceMemImpl) trainingService).reset();
        }
    }

    @Test
    void schedule() {
        LocalDate begin = LocalDate.of(2019, 11, 25);
        LocalDate end = LocalDate.of(2019, 11, 29);
        String title = "Gradle Training";
        Training training = trainingService.schedule(begin, end, title, trainerBernhard);

        assertEquals(title, training.getTitle());
        assertEquals(begin, training.getBegin());
        assertEquals(end, training.getEnd());
        assertEquals(trainerBernhard, training.getTrainer());

        Training training2 = trainingService.findTrainingById(training.getId()).get();
        assertEquals(training, training2);
    }
}
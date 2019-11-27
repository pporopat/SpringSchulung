package at.javatraining.trainings.repos.training;

import at.javatraining.trainings.SpringBaseTest;
import at.javatraining.trainings.entities.Training;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

class TrainingRepoTest extends SpringBaseTest {

    @Autowired
    TrainingRepo trainingRepo;

    @Value("#{springTraining}")
    Training training;

    @Test
    @Commit
    void saveTraining() {
        trainingRepo.saveUsingMerge(training);
    }

}
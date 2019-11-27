package at.javatraining.trainings.repos.training;

import at.javatraining.trainings.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepo extends JpaRepository<Training, Long>,TrainingRepoCustom {
}

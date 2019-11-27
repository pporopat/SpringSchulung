package at.javatraining.trainings.repos.training;

import at.javatraining.trainings.entities.Training;

public interface TrainingRepoCustom {
    Training saveUsingMerge(Training training);
}

package at.javatraining.trainings.services;

import at.javatraining.trainings.entities.Student;
import at.javatraining.trainings.entities.Trainer;
import at.javatraining.trainings.entities.Training;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingService {
    Training schedule(LocalDate begin, LocalDate end, String title, Trainer trainer);
    Training bookStudent(Long trainingsId, Student student);
    Training cancel(Long trainingID, Student student);
    void cancel(Long trainingID);
    List<Training> getSchedule();
    Optional<Training> findTrainingById(Long id);
}

package at.javatraining.trainings.services;

import at.javatraining.trainings.entities.Student;
import at.javatraining.trainings.entities.Trainer;
import at.javatraining.trainings.entities.Training;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TrainingServiceMemImpl implements TrainingService {
    private Map<Long, Training> trainingMap = Collections.synchronizedMap(new HashMap<>());
    private AtomicLong nextId = new AtomicLong(1L);

    @Override
    public Training schedule(LocalDate begin, LocalDate end, String title, Trainer trainer) {
        long currentID = nextId.getAndIncrement();
        Training training = Training.builder()
                .id(currentID)
                .begin(begin)
                .end(end)
                .title(title)
                .trainer(trainer)
                .build();

        trainingMap.put(currentID, training);
        return training;
    }

    @Override
    public Training bookStudent(Long trainingsId, Student student) {
        Optional<Training> trainingOpt = findTrainingById(trainingsId);
        Training training = trainingOpt.orElseThrow();
        training.getStudents().add(student);
        return training;
    }

    @Override
    public Training cancel(Long trainingID, Student student) {
        Training training = findTrainingById(trainingID).orElseThrow();
        training.getStudents().remove(student);
        return training;
    }

    @Override
    public void cancel(Long trainingID) {
        trainingMap.remove(trainingID);
    }

    @Override
    public List<Training> getSchedule() {
        return trainingMap
                .values()
                .stream()
                .sorted(Comparator.comparing(Training::getBegin))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Training> findTrainingById(Long id) {
        return Optional.ofNullable(trainingMap.get(id));
    }

    void reset() {
        trainingMap.clear();
    }
}

package at.javatraining.trainings.rest;

import at.javatraining.trainings.entities.Training;
import at.javatraining.trainings.repos.training.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainings")
public class TrainingRestService {
    @Autowired
    private TrainingRepo trainingRepo;

    @GetMapping
    public List<Training> findAll() {
        return trainingRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Training> findById(@PathVariable("id") Long id) {
        return trainingRepo.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public Training insert(@RequestBody Training training) {
        if(training.getId() != null) throw new IllegalArgumentException("id must be null!");
        return trainingRepo.saveUsingMerge(training);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Training update(@RequestBody Training training, @PathVariable("id")Long id) {
        if(training.getId() == null) throw new IllegalArgumentException("id must not be null!");
        if(training.getId() != id) throw new IllegalArgumentException("id invalid!");

        return trainingRepo.saveUsingMerge(training);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        trainingRepo.deleteById(id);
    }
}

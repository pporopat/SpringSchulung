package at.javatraining.trainings;

import at.javatraining.trainings.entities.Student;
import at.javatraining.trainings.entities.Trainer;
import at.javatraining.trainings.entities.Training;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingsApplicationTests extends SpringBaseTest{
    // @Autowired
    private Trainer trainer;

    /*
    // Referenzierung über Namen
    @Autowired
    private Trainer trainerMichael;
     */

    /*
    @Autowired
    public TrainingsApplicationTests(Trainer trainer) {
        this.trainer = trainer;
    }
    */

    @Autowired
    @Qualifier("trainerBernhard")
    Trainer trainerBernhard;

    @Value("#{trainerMichael}")
    Trainer trainerMichael;

    @Value("#{trainerMichael.name}")
    String nameTrainerMichael;

    @Autowired
    List<Trainer> alleTrainer;

    @Value("#{springTraining}")
    Training springTraining;

    @Value("#{microservicesTraining}")
    Training microservicesTraining;

    @Value("#{tim}")
    Student tim;

    @Value("#{patrick}")
    Student patrick;

    @Test
    void testDI() {
        assertNull(trainer.getId());
        trainer.setId(1L);
        System.out.println(trainer);
        assertNotNull(trainer.getId());
    }

    @Test
    void testDI2() {
        assertNull(trainer.getId());
        trainer.setId(2L);
        assertNotNull(trainer.getId());
    }

    @Test
    void testAdditionalInjections() {
        assertEquals("Bernhard", trainerBernhard.getName());
        assertEquals("Michael", trainerMichael.getName());
        assertEquals("Michael", nameTrainerMichael);
    }

    @Test
    void testAlleTrainer() {
        assertEquals(2, alleTrainer.size());
    }

    @Test
    void testSpringTraining() {
        System.out.println(springTraining);
        assertNotNull(springTraining.getTrainer());
        assertNotNull(springTraining.getTitle());
        assertNotNull(springTraining.getBegin());
        assertNotNull(springTraining.getEnd());
        assertEquals(2, springTraining.getStudents().size());
    }

    @Test
    void testMicroservicesTraining() {
        System.out.println(microservicesTraining);
        assertNotNull(microservicesTraining.getTrainer());
        assertNotNull(microservicesTraining.getTitle());
        assertNotNull(microservicesTraining.getBegin());
        assertNotNull(microservicesTraining.getEnd());
        assertEquals(3, microservicesTraining.getStudents().size());
    }

    @Test
    void testTimAndPatrick() {
        assertNotNull(tim);
        System.out.println(tim);

        assertNotNull(patrick);
        System.out.println(patrick);
    }

    @Autowired
    void setTrainer(Trainer trainerMichael) {
        this.trainer = trainerMichael;
    }
}

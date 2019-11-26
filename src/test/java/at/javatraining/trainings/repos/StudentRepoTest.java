package at.javatraining.trainings.repos;

import at.javatraining.trainings.SpringBaseTest;
import at.javatraining.trainings.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepoTest extends SpringBaseTest {
    @Autowired
    StudentRepo studentRepo;

    @Value("#{philipp}")
    Student studentPhilipp;

    @Test
    void save() {
        studentPhilipp = studentRepo.save(studentPhilipp);
        Student studentFromDB = studentRepo.findById(studentPhilipp.getId()).get();
        assertEquals(studentPhilipp.getId(), studentFromDB.getId());
        assertEquals(studentPhilipp.getEmail(), studentFromDB.getEmail());
        assertEquals(studentPhilipp.getName(), studentFromDB.getName());
    }
}
package at.javatraining.trainings.repos;

import at.javatraining.trainings.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

}

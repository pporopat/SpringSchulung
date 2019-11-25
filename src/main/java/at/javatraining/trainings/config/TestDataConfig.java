package at.javatraining.trainings.config;

import at.javatraining.trainings.entities.Student;
import at.javatraining.trainings.entities.Trainer;
import at.javatraining.trainings.entities.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@ImportResource("spring.xml")
public class TestDataConfig {
    @Bean(name="trainerMichael")
    @Scope("prototype")
    public Trainer createTrainerMicheal() {
        log.info("createTrainerMichael created!");

        Trainer trainerMichael = Trainer.builder()
                .name("Michael")
                .skillLevel(Trainer.SkillLevel.EXPERT)
                .build();
        return trainerMichael;
    }

    @Bean
    @Scope("prototype")
    public  Trainer trainerBernhard() {
        return Trainer.builder()
                .skillLevel(Trainer.SkillLevel.EXPERT)
                .name("Bernhard")
                .build();
    }

    @Bean
    @Scope("prototype")
    @Profile("profile1")
    Student philipp() {
        return Student.builder()
                .name("Philipp")
                .email("philipp@mail.at")
                .build();
    }

    @Bean
    @Scope("prototype")
    @Profile("profile2")
    Student david() {
        return Student.builder()
                .name("David")
                .email("david@mail.at")
                .build();
    }

    @Bean
    @Scope("prototype")
    Student mladen() {
        return Student.builder()
                .name("Mladen")
                .email("mladen@mail.at")
                .build();
    }

    @Bean
    @Scope("prototype")
    Student marcel() {
        return Student.builder()
                .name("Marcel")
                .email("marcel@mail.at")
                .build();
    }

    @Bean
    Training springTraining(@Value("#{trainerMichael}") Trainer trainer,
                            @Value("#{philipp}") Student philipp,
                            @Value("#{marcel}") Student marcel) {
        return Training.builder()
                .title("Spring Training")
                .students(new ArrayList<>(List.of(philipp, marcel)))
                .trainer(trainer)
                .begin(LocalDate.parse("2019-11-25"))
                .end(LocalDate.parse("2019-11-29"))
                .build();

    }

    @Bean
    Training microservicesTraining() {
        return Training.builder()
                .trainer(trainerBernhard())
                .students(new ArrayList<>(List.of(marcel(), philipp(), mladen())))
                .title("Microservices Training")
                .trainer(trainerBernhard())
                .begin(LocalDate.parse("2019-12-02"))
                .end(LocalDate.parse("2019-11-06"))
                .build();
    }
}

package at.javatraining.trainings.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(name = "trainingBegin")
    private LocalDate begin;

    @Column(name = "trainingEnd")
    private LocalDate end;

    @Transient
    private Trainer trainer;

    @Transient
    private List<Student> students;
}

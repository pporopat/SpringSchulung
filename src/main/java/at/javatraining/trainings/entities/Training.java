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

    @Column(name = "training_begin")
    private LocalDate begin;

    @Column(name = "training_end")
    private LocalDate end;

    @JoinColumn(name="id_trainer")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Trainer trainer;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="training_students",
            joinColumns = @JoinColumn(name="id_training"),
            inverseJoinColumns = @JoinColumn(name="id_student"))
    private List<Student> students;
}

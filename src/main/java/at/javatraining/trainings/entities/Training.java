package at.javatraining.trainings.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {
    private Long id;
    private String title;
    private LocalDate begin;
    private LocalDate end;
    private Trainer trainer;
    private List<Student> students;
}

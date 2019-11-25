package at.javatraining.trainings.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    public enum SkillLevel{BASIC, ADVANCED, EXPERT}

    private Long id;
    private String name;
    private SkillLevel skillLevel;
}

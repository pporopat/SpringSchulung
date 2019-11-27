package at.javatraining.trainings.repos.training;

import at.javatraining.trainings.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class TrainingRepoCustomImpl implements TrainingRepoCustom{
    @PersistenceContext
    private EntityManager em;

    @Lazy
    @Autowired
    TrainingRepo myself;

    @Override
    public Training saveUsingMerge(Training training) {
        return em.merge(training);
    }
}

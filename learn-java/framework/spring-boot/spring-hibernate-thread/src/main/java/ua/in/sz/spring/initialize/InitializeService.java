package ua.in.sz.spring.initialize;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.in.sz.spring.initialize.entity.InitializeScheduleEntity;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitializeService {

    private final EntityManager entityManager;

    @Transactional
    public List<InitializeScheduleEntity> load() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InitializeScheduleEntity> cq = cb.createQuery(InitializeScheduleEntity.class);
        CriteriaQuery<InitializeScheduleEntity> query = cq.select(cq.from(InitializeScheduleEntity.class));

        List<InitializeScheduleEntity> entities = entityManager.createQuery(query).getResultList();

        for (InitializeScheduleEntity entity : entities) {
            log.info("Initialization:");
            Hibernate.initialize(entity.getActions());
        }

        return entities;
    }
}

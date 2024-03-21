package ua.in.sz.spring.transaction.readonly;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.in.sz.spring.transaction.readonly.entity.TxReadOnlyScheduleEntity;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TxReadOnlyService {

    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public void load() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TxReadOnlyScheduleEntity> cq = cb.createQuery(TxReadOnlyScheduleEntity.class);
        CriteriaQuery<TxReadOnlyScheduleEntity> query = cq.select(cq.from(TxReadOnlyScheduleEntity.class));

        List<TxReadOnlyScheduleEntity> entities = entityManager.createQuery(query).getResultList();

        for (TxReadOnlyScheduleEntity entity : entities) {
            log.info("Entity with name: [{}]", entity.getName());

            entity.setName(entity.getName() + " +2");
        }
    }
}

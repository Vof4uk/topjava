package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User userRef = em.getReference(User.class, userId);
        meal.setUser(userRef);
        if(meal.getId() == null) {
            em.persist(meal);
            return meal;
        }
        Meal mealRef = em.getReference(Meal.class, meal.getId());
        if(Objects.equals(mealRef.getUser().getId(), meal.getUser().getId()))
            return em.merge(meal);
        else return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        String queryLine = "DELETE FROM Meal m WHERE m.id =:id AND m.user.id =:userId";
        Query query = em.createQuery(queryLine);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        return query.executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        String queryLine = "SELECT m FROM Meal m WHERE m.id =:id AND m.user.id =:userId";
        Query query = em.createQuery(queryLine, Meal.class);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        List meals = query.getResultList();
        if (meals.isEmpty()) {
            return null;
        }
        return (Meal) meals.get(0);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Meal> cq  = cb.createQuery(Meal.class);
        Root<Meal> meal = cq.from(Meal.class);
        cq.select(meal);
        cq.where(
                cb.greaterThanOrEqualTo(meal.get("dateTime"), cb.literal(startDate)),
                cb.lessThan(meal.get("dateTime"), cb.literal(endDate))
        );
        cq.orderBy(cb.desc(meal.get("dateTime")));
        return em.createQuery(cq).getResultList();
    }
}
package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Override
    Meal save(Meal meal);

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id = :id AND m.user.id = :userId")
    int delete(@Param("id")int id,
               @Param("userId") int userId);

    List<Meal> getAllByUserIdOrderByDateTimeDesc(Integer userId);

    Meal getByIdAndUserId(Integer id, Integer userId);

    List<Meal> getByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime start, LocalDateTime stop);


}

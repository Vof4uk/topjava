package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repositoryById = new ConcurrentHashMap<>();
    private Map<String, User> repositoryByEmail = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        User res = repositoryById.remove(id);
        if(res != null)
        {
            repositoryByEmail.remove(res.getEmail());
            LOG.info("delete " + id);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        if (user.getEmail() == null || repositoryByEmail.containsKey(user.getEmail())) return null;
        LOG.info("save " + user);
        if(user.getId() == null)
            user.setId(counter.getAndIncrement());
        repositoryByEmail.put(user.getEmail(), user);
        repositoryById.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repositoryById.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repositoryById.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repositoryByEmail.get(email);
    }
}

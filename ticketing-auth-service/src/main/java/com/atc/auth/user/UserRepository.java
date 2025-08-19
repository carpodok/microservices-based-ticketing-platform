package com.atc.auth.user;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public User save(User user) {
        user.setId(idSequence.incrementAndGet());
        users.put(user.getUsername(), user);
        return user;
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}

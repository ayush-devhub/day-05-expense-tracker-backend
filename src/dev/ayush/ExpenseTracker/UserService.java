package dev.ayush.ExpenseTracker;

import java.util.*;

/**
 * Service layer for managing users.
 */
public class UserService {
    private List<User> users; // collection of users

    /**
     * Constructor: initialize empty list.
     */
    public UserService() {
        users = new ArrayList<>();
    }

    /**
     * Add user with validation.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Find user by ID. @return user or null
     */
    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }

        }
        return null;
    }

    /**
     * List all users.
     */
    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
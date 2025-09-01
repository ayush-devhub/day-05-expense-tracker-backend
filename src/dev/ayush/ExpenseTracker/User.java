package dev.ayush.ExpenseTracker;

import java.util.*;

/**
 * Represents a user of the expense tracker.
 */
public class User {
    private int id; // unique user ID
    private String name; // user name
    private String email; // user email
    private List<Expense> expenses; // list of expenses for the user

    /**
     * Constructor: initialize fields.
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        expenses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @return user's expenses
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Add an expense to the user.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

}
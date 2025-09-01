package dev.ayush.ExpenseTracker;

import java.time.LocalDate;

/**
 * Represents an expense entry.
 */
public class Expense {
    private int id; // unique expense ID
    private String category; // category like Food/Travel
    private double amount; // amount spent
    private LocalDate date; // date of expense
    private String description; // short description

    /**
     * Constructor: initialize fields.
     */
    public Expense(int id, String category, double amount, LocalDate date, String description) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Getters for all fields
     */


    @Override
    public String toString() {
        return "ID=" + id + " | Category=" + category + " | Amount=" + amount + " | Date=" + date + " | " + getDescription();
    }
}
package dev.ayush.ExpenseTracker;

import java.util.*;

/**
 * Service layer for managing expenses.
 */
public class ExpenseService {

    /**
     * Add expense to user.
     */
    public void addExpenseToUser(User user, Expense expense) {
        user.addExpense(expense);
    }

    /**
     * List all expenses of a user.
     */
    public void listExpensesByUser(User user) {
        if (user.getExpenses() == null) {
            System.out.println("No user expense.");
        } else {
            List<Expense> expenses = user.getExpenses();
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    /**
     * Delete expense by ID. @return success flag
     */
    public boolean deleteExpense(User user, int expenseId) {
        List<Expense> expenses = user.getExpenses();

        for (Expense expense : expenses) {
            if (expense.getId() == expenseId) expenses.remove(expense);
            return true;
        }

        return false;
    }

    /**
     * @return total spent by user
     */
    public double totalSpentByUser(User user) {
        int total = 0;

        List<Expense> expenses = user.getExpenses();

        for (Expense expens : expenses) {
            total += expens.getAmount();
        }

        return total;
    }

    /**
     * @return total spent by category for a user
     */
    public double totalByCategory(User user, String category) {
        int totalInCategory = 0;

        List<Expense> expenses = user.getExpenses();

        for (Expense expens : expenses) {
            if (expens.getCategory() == category) {
                totalInCategory += expens.getAmount();
            }
        }

        return totalInCategory;
    }
}
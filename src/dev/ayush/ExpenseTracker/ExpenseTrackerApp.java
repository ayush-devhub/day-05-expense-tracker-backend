package dev.ayush.ExpenseTracker;

import java.time.LocalDate;
import java.util.*;

/**
 * Console entry point for Expense Tracker.
 */
public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();

        while (true) {
            showMenu();
            int choice = validInt("Enter choice: ", 1, 9, scanner);
            System.out.println();
            handleUserChoice(choice, userService, expenseService, scanner);
            System.out.println();
        }
    }

    /**
     * Show main menu options.
     */
    private static void showMenu() {
        System.out.println("""
                ===== Expense Tracker =====
                1. Create User
                2. Add Expense
                3. List Expenses
                4. Delete Expense
                5. Total Spent by User
                6. Total by Category
                7. Save Data
                8. Load Data
                9. Exit""");
    }

    /**
     * Handle user choice from menu.
     */
    private static void handleUserChoice(int choice, UserService userService, ExpenseService expenseService, Scanner scanner) {
        switch (choice) {
            case 1: {
                int id = validInt("Enter User ID: ", 1, 1000, scanner);
                String name = validString("Enter Name: ", scanner);
                String email = validString("Enter Email: ", scanner);
                userService.addUser(new User(id, name, email));
                break;
            }
            case 2: {
                int id = validInt("Enter User ID: ", 1, 1000, scanner);
                int expenseId = validInt("Enter Expense ID: ", 1, 1000, scanner);
                String category = validString("Enter Category: ", scanner);
                double amount = validDouble("Enter Expense ID: ", 1, 100000, scanner);
                String description = validString("Enter Description: ", scanner);
                expenseService.addExpenseToUser(userService.findUserById(id), new Expense(expenseId, category, amount, LocalDate.now(), description));
                System.out.println("Expense added successfully!");
                break;
            }

            case 3: {
                List<User> userList = userService.getUsers();
                for (User user : userList) {
                    expenseService.listExpensesByUser(user);
                }
                break;
            }

            case 4: {
                int id = validInt("Enter User ID: ", 1, 1000, scanner);
                User user = userService.findUserById(id);
                int expenseId = validInt("Enter Expense ID: ", 1, 1000, scanner);
                expenseService.deleteExpense(user, expenseId);
                System.out.println("Expense id deleted.");
                break;
            }

            case 5: {
                int id = validInt("Enter User ID: ", 1, 1000, scanner);
                User user = userService.findUserById(id);
                double totalSpent = expenseService.totalSpentByUser(user);
                System.out.println("Total Spent: " + totalSpent);
                break;
            }

            case 6: {
                int id = validInt("Enter User ID: ", 1, 1000, scanner);
                User user = userService.findUserById(id);
                String category = validString("Enter Category: ", scanner);
                double totalSpent = expenseService.totalByCategory(user, category);
                System.out.println("Total spent: " + totalSpent + "in category: " + category);
                break;
            }

            case 7: {
                FileService fileService = new FileService();
                fileService.saveToFile("data/expenses.txt", userService.getUsers());
                break;
            }

            case 8: {
                FileService fileService = new FileService();
                fileService.loadFromFile("data/expenses.txt");
                break;
            }

            case 9: {
                System.exit(0);
            }
        }
    }

    private static double validDouble(String prompt, int min, int max, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
                System.out.println("Enter value in range (" + min + "-" + max + ").");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid Integer value.");
            }
        }
    }

    private static String validString(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("This section can't be empty.");
        }
    }

    private static int validInt(String prompt, int min, int max, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
                System.out.println("Enter value in range (" + min + "-" + max + ").");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid Integer value.");
            }
        }
    }


}
package dev.ayush.ExpenseTracker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Handles file persistence.
 */
public class FileService {

    /**
     * Save all users + expenses to file.
     */
    public void saveToFile(String filePath, List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write("USER," + user.getId() + "," + user.getName() + "," + user.getEmail());
                writer.newLine();
                for (Expense expense : user.getExpenses()) {
                    writer.write("EXPENSE" + "," + expense.getId() + "," + expense.getCategory() + "," + expense.getAmount() + "," + expense.getDescription() + "," + expense.getDate());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all users + expenses from file.
     */
    public List<User> loadFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        Map<Integer, User> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(",");
                if (splits[0] == "USER") {
                    int id = Integer.parseInt(splits[1]); // unique user ID
                    String name = splits[2]; // user name
                    String email = splits[3];
                    User user = new User(id, name, email);
                    users.add(user);
                    map.put(id, user);
                } else {
                    int id = Integer.parseInt(splits[1]); // unique expense ID
                    String category = splits[2]; // category like Food/Travel
                    double amount = Double.parseDouble(splits[3]); // amount spent
                    LocalDate date = LocalDate.parse(splits[4]); // date of expense
                    String description = splits[5];
                    Expense expense = new Expense(id, category, amount, date, description);
                    map.get(id).addExpense(expense);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return users;
    }
}
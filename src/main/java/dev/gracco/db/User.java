package dev.gracco.db;

import lombok.Getter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    @Getter private static String firstName;
    @Getter private static String lastName;
    @Getter private static boolean changedPassword;
    @Getter private static Enums.Role role;

    public static String login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return "Please enter credentials!";
        }
        String sql = """
                SELECT first_name, last_name, changed_pass, is_active, role_id, password_hash
                FROM users
                WHERE username = ?
                LIMIT 1
                """;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return "User does not exist";
                }

                String passwordHash = resultSet.getString("password_hash");

                if (!Encryption.decrypt(password, passwordHash)) {
                    return "Incorrect password";
                }

                if (!resultSet.getBoolean("is_active")) {
                    return "User is set as inactive. Contact your administrator";
                }

                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                changedPassword = resultSet.getBoolean("changed_pass");
                role = Enums.Role.fromString(resultSet.getString("role_id"));

                return null;
            }

        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }
}
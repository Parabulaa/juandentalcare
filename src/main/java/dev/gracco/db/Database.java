package dev.gracco.db;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/INSERT_NAME";
    private static final String DB_USER = "INSERT_USER";
    private static final String DB_PASSWORD = "INSERT_PASSWORD";

    public static boolean initialize() {
        return true;
    }
}

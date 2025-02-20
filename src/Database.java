import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/Kailua_CarRental";
    private static final String USER = "root";
    private static final String PASSWORD = "0502015575";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
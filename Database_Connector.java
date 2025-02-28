package invoice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connector {
    private static final String URL = "jdbc:mysql://localhost:3306/virtual_assistant";
    private static final String USER = "root";
    private static final String PASSWORD = "therese";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

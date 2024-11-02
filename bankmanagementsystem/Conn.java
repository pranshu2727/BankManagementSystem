package bankmanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;
    public Conn (){
        // Load the driver
        // Establish connection
        // Create the statement
        // Execute query
        // Close connection
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String db = "BankManagementSystem";
            String user = "root";
            String password = "@prashant123";
            c = DriverManager.getConnection(url + db, user, password);
            s = c.createStatement();
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}

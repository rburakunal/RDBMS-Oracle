import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class BookInserter {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:sys/ORACLE@localhost:1521/XE as sysdba";  // Updated connection string
        String username = "SYS";
        String password = "ORACLE";
        
        String insertSQL = "INSERT INTO BOOK (ID, NAME, ISBN) VALUES (?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            
            Random random = new Random();
            for (int i = 1; i <= 100; i++) {
                preparedStatement.setInt(1, i); // ID
                preparedStatement.setString(2, "Book " + i); // NAME
                preparedStatement.setString(3, "ISBN" + random.nextInt(10000)); // ISBN
                
                preparedStatement.executeUpdate();
            }
            
            System.out.println("100 records inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

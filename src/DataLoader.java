import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataLoader {
	private Connection connection;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/hotel_world?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadUser(){
		
	}
	
	
}

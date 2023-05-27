import java.io.IOException;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Database connection
public class ConnectDB {
	
	//Connection setup, database name, url and database username/password
	static Connection connect;
	static String databaseName = "FootballManagementSystem";
	static String url = "jdbc:mysql://localhost:3306/" +databaseName;
	static String username = System.getenv("DB_USERNAME");
	static String password = System.getenv("DB_PASSWORD");
	
	
	
	
	//establishing connection to database
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
			
			
			System.out.println("connection is successful");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
		
		
		
		public static Connection getConnect() {
			return connect;
		}

}

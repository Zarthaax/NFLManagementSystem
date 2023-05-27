package database;
import java.io.IOException;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Database connection
public class ConnectDB {
	
	//Connection setup, database name, url and database username/password
	static Connection connect = null;
	static String databaseName = "FootballManagementSystem";
	static String url = "jdbc:mysql://localhost:3306/" +databaseName;
	static String username = System.getenv("DB_USERNAME");
	static String password = System.getenv("DB_PASSWORD");
	
	
	
	
	//establishing connection to database
	public static void main(String[] args) throws SQLException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
			PlayerDao playerdao = new PlayerDao(connect);
			playerdao.createTable();
			playerdao.createPlayer();
			
			System.out.println("connection is successful");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}

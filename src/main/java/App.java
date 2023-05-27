import java.sql.Connection;

public class App {

	public static void main(String[] args) {
		
		ConnectDB connectDB = new ConnectDB();		
		PlayerDao playerDao = new PlayerDao(connectDB.getConnect());
		
		

	}

}

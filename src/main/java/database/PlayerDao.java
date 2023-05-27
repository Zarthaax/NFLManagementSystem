package database;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao {

	private Connection connection;
	
	
	public PlayerDao(Connection connection) {
		this.connection = connection;		
	}
	
	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS COLTS (\n"
				+ " player_number integer PRIMARY KEY,\n"
				+ " name text NOT NULL,\n"
				+ " position text NOT NULL\n"
				+ ");";	
	
	try (Statement stmt = connection.createStatement()) {
        // Executing the SQL statement
        stmt.execute(sql);
        ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'COLTS'");
        if(rs.next()) {
        	System.out.println("Table was created");
        } else {
        	System.out.println("Table was not created");
        }
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
	}
	
	//create one player
	//name: Anthony Richardson
	//number: 5
	//Position: QB
	public void createPlayer() {
		String deletesql = "DELETE FROM COLTS WHERE id=5";
		String sql = "INSERT INTO COLTS VALUES ("
				+ "5,"
				+"'Anthony Richardson',"
				+"'Quarterback'"
				+");";
	
		try(Statement stmt = connection.createStatement()){
		stmt.executeUpdate(deletesql);
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("Select * from COLTS");
		
			while(rs.next()) {
					System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("position"));
		}
	}catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
	}
	
	public void updateTable() {}
	public void updatePlayer() {}	
}
	




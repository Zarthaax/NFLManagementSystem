import java.sql.Statement;
import java.sql.*;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import api.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PlayerDao {

	private Connection connection;
	private Player player;	
	
	public PlayerDao(Connection connection) {
		this.connection = connection;
		listPlayers();
		enterPlayer();
		createTable();
	}
	
	private void listPlayers() {
	API api = new API();
	//need to bring over thee api key
	Call<List<Player>> call = api.listPlayers();
	
	call.enqueue(new Callback <List<Player>>() {
		
		@Override
		public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
			if(response.isSuccessful()) {
				List<Player>players = response.body();
//				System.out.println(players);
			} else {
				System.out.print("Response failed with code:" + response.code());
			}
		}
		
		@Override
		public void onFailure(Call<List<Player>> call, Throwable t) {
			System.out.println(t.getMessage());
		}
		
		
	});
	}	
	
	
	
	private void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS COLTS (ID INT AUTO_INCREMENT, PlayerNumber INT NOT NULL, PlayerName VARCHAR(255) NOT NULL, PlayerPosition VARCHAR(255) NOT NULL, PRIMARY KEY (ID))";
	
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
	
	private void enterPlayer() {
		
		API api  = new API();
		Call<List<Player>> call = api.listPlayers();
		
		call.enqueue(new Callback<List<Player>>() {

			@Override
			public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
				if(response.isSuccessful()) {
					List<Player> players = response.body();
					
					
					
					int length = players.size();
					for(int i = 0; i < length; i++) {
						
						String name = players.get(i).getName();
						int num = players.get(i).getNumber();
						String position = players.get(i).getPosition();
						
						if(name != players.get(i).getName()) { 
							String sql = "INSERT INTO COLTS VALUES (ID, ?,?,?)";
							try(PreparedStatement statement = connection.prepareStatement(sql);) {
								statement.setInt(1, num);
								statement.setString(2, name);
								statement.setString(3, position);
								statement.executeUpdate();
								
								ResultSet rs = statement.executeQuery("Select * from COLTS");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
//							try(Statement stmt = connection.createStatement()){
//								stmt.executeUpdate(sql);
//								ResultSet rs = stmt.executeQuery("Select * from COLTS");
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
						}
						
					}
					
				}
				
			}

			@Override
			public void onFailure(Call<List<Player>> call, Throwable t) {
				System.out.println(t.getMessage());
			}
			
		});
		
		
		String deletesql = "DELETE FROM COLTS WHERE id=5";
		
	
		try(Statement stmt = connection.createStatement()){
		stmt.executeUpdate(deletesql);
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
	




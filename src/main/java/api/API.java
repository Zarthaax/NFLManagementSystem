package api;

import java.net.http.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import database.PlayerDao;
import api.*;

public class API {

	// will eventually need to change IND to {team} once I create a larger db
	public interface SportsDataService {
		@GET("PlayersBasic/IND")
		Call<List<Player>> listPlayers(@Query("key") String apiKey);
		@GET("PlayersBasic/IND")
		Call<Player>enterPlayer(@Field("Number") int number, @Field("Name") String name, @Field("Position") String position, @Query("key") String apiKey);
	}
	
	private final SportsDataService service;
	private final String apiKey;

	public API() {
		this.apiKey = System.getenv("SPORTS_DATA_API");
		

		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
				.readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.sportsdata.io/v3/nfl/scores/json/")
				.client(client).addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(SportsDataService.class);
	}

	public Call<List<Player>> listPlayers() {
		return service.listPlayers(this.apiKey);
	}
	
//	public Call<Player> enterPlayer(){
//		return service.enterPlayer(, apiKey, apiKey)
//	}
	
	
	

}

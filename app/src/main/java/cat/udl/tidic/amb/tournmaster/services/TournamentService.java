package cat.udl.tidic.amb.tournmaster.services;

import java.util.List;

import cat.udl.tidic.amb.tournmaster.Tournament;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TournamentService {
	
    @GET("/tournaments/show/{id}")
    Call<Tournament> getTournament(@Path("id") String id);

    @GET("/tournamets/list")
    Call<List<Tournament>> getTournaments();


}

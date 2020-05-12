package cat.udl.tidic.amb.tournmaster.services;

import java.util.List;

import cat.udl.tidic.amb.tournmaster.Tournament;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TournamentService {

    @GET("/tournamets/list")
    Call<List<Tournament>> getTournaments();



}

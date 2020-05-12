package cat.udl.tidic.amb.tournmaster.services;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amb.tournmaster.Tournament;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TournamentService {
	
    @GET("/tournaments/show/{id}")
    Call<Tournament> getTournament(@Path("id") String id);
}

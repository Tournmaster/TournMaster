package cat.udl.tidic.amb.tournmaster.services;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amb.tournmaster.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {


        @GET("/account/profile")
        Call<User> getUserProfile();

        @GET("/users")
        Call<List<User>> getUsers(@Query("genere") String genere,@Query("position") String position,@Query("prefsmash") String prefsmash);

        @GET("/users/show/{username}")
        Call<User> getPerfilPublico(@Path("username") String username);

        @GET("/tournaments/show/{id}")
        Call<User> getTournament(@Path("id") String id);

        @POST("/users/register")
        @Headers("No-Authentication: true") //Con esta línia el interceptor no pondra el token :)
        Call<Void> createUser(@Body JsonObject userJson);

        @POST("/account/create_token")
        Call<ResponseBody>createToken();

        @Multipart
        @POST("/account/profile/update_profile_image")
        Call<ResponseBody>uploadImage(@Part  MultipartBody.Part image);

        @POST("account/delete_token")
        Call<ResponseBody>deleteToken(@Body String token);

        @PUT("/account/update_profile")
        Call<Void> updateAccount(@Body User u);
}

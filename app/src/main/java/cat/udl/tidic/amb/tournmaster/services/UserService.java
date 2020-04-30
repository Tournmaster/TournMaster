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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    //metodes per fer les instancies a la base de dades tan como get i post!:

        // Accounts
        /*
         * @JordiMateoUdl: Heu de diferenciar el registre de la operació d'actualitzar el perfil!!!
         */
        //PETICIONES GETS
        @GET("/account/profile")
        Call<JsonObject> getUserProfile(@Header("Authorization") String auth_token);

        // Users
        @GET("/users")
        Call<List<User>> getUsers(@Header("Authorization") String auth_token,@Query("genere") String genere,@Query("position") String position,@Query("prefsmash") String prefsmash);

        @GET("/users/show/{username}")
        Call<User> getPerfilPublico(@Header("Authorization") String auth_token, @Path("username") String username);
//----------------------------------------------------------------------------------------------------------

        //PETICIONES POST
        @POST("/users/register")
        Call<Void> createUser(@Body JsonObject userJson);


        @POST("/account/create_token")
        Call<ResponseBody>createToken(@Header("Authorization") String auth_token);
        @Multipart
        @POST("/account/profile/update_profile_image")
        Call<ResponseBody>uploadImage(@Header("Authorization") String auth_token, @Part  MultipartBody.Part image);

        @POST("account/delete_token")
        Call<ResponseBody>deleteToken(@Header("Authorization") String auth_token);
        //----------------------------------------------------------------------------------------------------------

        //Peticiones PUT
        @PUT("/account/update_profile")
        Call<Void> updateAccount(@Header("Authorization") String auth_token, @Body JsonObject userJson);
}

package cat.udl.tidic.amb.tournmaster.retrofit;

import android.content.SharedPreferences;

import java.io.IOException;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String token = PreferencesProvider.providePreferences().getString("token","");

        if(request.header("No-Authentication") == null){
            request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build();
        }
        return chain.proceed(request);
    }
}

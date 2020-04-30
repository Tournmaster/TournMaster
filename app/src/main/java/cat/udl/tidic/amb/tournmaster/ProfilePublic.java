package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePublic extends AppCompatActivity {
    public static final String EXTRA_USERNAME =
            "cat.udl.tidic.amd.tournmaster.EXTRA_USERNAME";
    private TextView nom;
    private UserService userService;
    private SharedPreferences mPreferences;
    private TextView sex;
    private String TAG ="PUBLIC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_public);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_USERNAME)) {
            setTitle("Perfil Publico");

        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Buscar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.Inicio:
                        startActivity(new Intent(getApplicationContext(),
                                Inicio.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Partidos:
                        startActivity(new Intent(getApplicationContext(),
                                Partidos.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Buscar:
                        startActivity(new Intent(getApplicationContext(),
                                Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Perfil:
                        startActivity(new Intent(getApplicationContext(),
                                Perfil.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        String token;
        String username;
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);


        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");
        nom = findViewById(R.id.text_users);
        sex = findViewById(R.id.text_sexo);


        nom.setText(intent.getStringExtra(EXTRA_USERNAME));

         username = nom.getText().toString();
         Log.d(TAG,""+username);
         Call<User> call_get = userService.getPerfilPublico(token,username.trim());
         call_get.enqueue(new Callback<User>() {
             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 Log.d(TAG,""+response.code());
                 if(response.code()==200) {
                     User user = response.body();

                     if(user.getGenere().equals("M")){
                         Log.d("TAG","ENTRA");
                         sex.setText("Hombre");
                     }
                     else{
                         sex.setText("Mujer");
                     }


                 }

             }

             @Override
             public void onFailure(Call<User> call, Throwable t) {
                    Log.d(TAG,t.getMessage());
             }
         });


    }

    public String atributs(String n){

        n = n.substring(1,n.length()-1);

        return n;

    }
}

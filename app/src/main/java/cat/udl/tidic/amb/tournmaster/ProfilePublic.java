package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
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
    private TextView name;
    private TextView golpeprf;
    private TextView position;
    private TextView mail;
    private TextView matchname;
    private TextView TiempoJugado;
    private TextView club;
    private ImageView girl;
    private ImageView boy;
    private TextView rol;
    private ImageView img;


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
        boy = findViewById(R.id.img_boys);
        girl = findViewById(R.id.img_girls);
        boy = findViewById(R.id.img_boys);
        girl = findViewById(R.id.img_girls);
        name = findViewById(R.id.edit_nombre);
        mail = findViewById(R.id.edit_email);
        position = findViewById(R.id.edit_position);
        matchname = findViewById(R.id.edit_marchname);
        golpeprf = findViewById(R.id.edit_prefsmash);
        club = findViewById(R.id.edit_club);
        rol = findViewById(R.id.text_rol);
        img = findViewById(R.id.image_pb);



        nom.setText(intent.getStringExtra(EXTRA_USERNAME));

         username = nom.getText().toString();
         Log.d(TAG,""+username);
         Call<User> call_get = userService.getPerfilPublico(username.trim());
         call_get.enqueue(new Callback<User>() {
             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 Log.d(TAG,""+response.code());
                 if(response.code()==200) {
                     User user = response.body();

                     if(user.getGenere().equals("M")){
                         Log.d("TAG","ENTRA");
                         boy.setVisibility(View.VISIBLE);
                     }
                     else{
                         girl.setVisibility(View.VISIBLE);
                     }

                     if(user.getPosicion().equals("L")){
                         position.setText("Izquierda");
                     }
                     else{
                         position.setText("Derecha");
                     }
                     Log.d(TAG, "Photo URl:" + user.getPhoto());
                     Picasso.get().load(user.getPhoto()).into(img);
                     name.setText(user.getName());
                     mail.setText(user.getEmail());
                     matchname.setText(user.getMatchname());
                     club.setText(user.getClub());
                     golpeprf.setText(user.getPrefSmash());
                     if(user.getPrefSmash().equals("S")){
                         golpeprf.setText("Saque");
                     }
                     if(user.getPrefSmash().equals("R")){
                         golpeprf.setText("Right");
                     }
                     if(user.getPrefSmash().equals("L")){
                         golpeprf.setText("Reves");
                     }
                     if(user.getPrefSmash().equals("G")){
                         golpeprf.setText("Globo");
                     }
                     if(user.getPrefSmash().equals("C")){
                         golpeprf.setText("Cortada");
                     }
                     if(user.getPrefSmash().equals("M")){
                         golpeprf.setText("Smash");
                     }
                     if(user.getPrefSmash().equals("V")){
                         golpeprf.setText("Volea");
                     }

                     rol.setText(user.getRol());





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

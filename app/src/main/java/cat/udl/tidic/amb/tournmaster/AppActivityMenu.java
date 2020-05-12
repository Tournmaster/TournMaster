package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import cat.udl.tidic.amb.tournmaster.services.UserService;

public class AppActivityMenu extends AppCompatActivity {

    private Intent intent;
    protected UserService userService;
    protected SharedPreferences mPreferences;
    protected String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // @JordiMateoUdL: Inicialitzem les preferencies i el userService, aixi ja no cal tornar a fer-ho
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);
        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");

    }

    protected void initView(int layout_resource){
        setContentView(layout_resource);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.getBotNavigationListener());
    }


    public void Inico (View view){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }
    public void partidos (View view){

        Intent intent = new Intent(this, Partidos.class);
        startActivity(intent);
    }
    public void search (View view){

        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void perfil (View view){

        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    protected BottomNavigationView.OnNavigationItemSelectedListener getBotNavigationListener() {

        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Inicio:
                        startActivity(new Intent(getApplicationContext(),
                                Inicio.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Partidos:
                        startActivity(new Intent(getApplicationContext(),
                                Partidos.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Buscar:
                        startActivity(new Intent(getApplicationContext(),
                                Search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Perfil:
                        startActivity(new Intent(getApplicationContext(),
                                Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        };
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),
                Inicio.class));
    }
}

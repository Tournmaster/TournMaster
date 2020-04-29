package cat.udl.tidic.amb.tournmaster;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    private static final String TAG = "PlayersListActivity";
    private UserService userService;
    public static final int EDIT_EVENT = 2;
    private RecyclerView playersListView;
    private UserAdapter userAdapter;
    ArrayList<User> players_data = new ArrayList<>();

    private SharedPreferences mPreferences;
    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        playersListView = findViewById(R.id.playersList);
        playersListView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(new UserDiffCallback());
        playersListView.setAdapter(userAdapter);
        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Log.d(TAG, user.getUsername());
                Intent intent = new Intent(Search.this, PublicProfile.class);
                intent.putExtra(PublicProfile.EXTRA_USERNAME, user.getUsername());
                startActivityForResult(intent, EDIT_EVENT);
            }
        });
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");

        Log.d(TAG, "Token: " + token);

        // Aquesta funció serveix per omplir la llista, ull tota la list està en
        // Mèmoria del dispositiu
        populateList();

    }

    private void populateList(){

        Call<List<User>> call_get_players = userService.getUsers(token);
        call_get_players.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200){
                    // Obtenim les dades de la consulta
                    players_data = (ArrayList<User>) response.body();
                    userAdapter.submitList(players_data);
                } else{
                    // notificar problemes amb la consulta
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                    // Notificar problemes amb la red
            }
        });



    }
    private void EntrarPerfil(){



    }




    public void Inico (View view){

        Intent intent = new Intent(Search.this,Inicio.class);
        startActivity(intent);
    }
    public void partidos (View view){

        Intent intent = new Intent(Search.this,Partidos.class);
        startActivity(intent);
    }
    public void search (View view){

        Intent intent = new Intent(Search.this,Search.class);
        startActivity(intent);
    }
    public void perfil (View view){

        Intent intent = new Intent(Search.this,Perfil.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.equals("") ){
                    userAdapter.submitList(players_data);
                    Log.d(TAG, "|Players| = " + players_data.size());
                }
                else {
                    ArrayList<User> filteredModelList = (ArrayList<User>) players_data.stream()
                            .filter(player -> player.getName().contains(newText))
                            .collect(Collectors.toList());

                    Log.d(TAG, "|Players| = " + filteredModelList.size());

                    userAdapter.submitList(filteredModelList);
                }
                playersListView.scrollToPosition(0);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }




}

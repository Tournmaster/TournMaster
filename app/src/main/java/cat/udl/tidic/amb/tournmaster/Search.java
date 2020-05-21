package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
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

    public void openDialog() {
        DialogFilter dialogfilter = new DialogFilter().newInstance(this);
        dialogfilter.show(getSupportFragmentManager(),"dialog_filter");
    }

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
                Intent intent = new Intent(Search.this, ProfilePublic.class);
                intent.putExtra(ProfilePublic.EXTRA_USERNAME, user.getUsername());
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

        populateList(null,null,null);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DIALOGFilter", "" + resultCode);

    }

    public void populateList(String genere,String position,String prefsmash){
        Log.d(TAG,""+genere);
        Call<List<User>> call_get_players = userService.getUsers(genere,position,prefsmash);
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

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            openDialog();
            Log.d(TAG, "Here we need to launch settings...");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}

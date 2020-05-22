package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cat.udl.tidic.amb.tournmaster.Torneo.EXTRA_TOURNAMENT;

public class MatchRounds extends AppCompatActivity {
    private RecyclerView tournamentDrawsListView;
    private TournamentService tournamentService;
    private MatchesAdapter tournamentDrawAdapter;
    private String TAG="TOURNDRAFT";
    private Intent intent;
    private String tourname;
    private Tournament tournament;
    private Spinner spinner_category;
    ArrayList<Tournament> tournaments_data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_draw);
        intent = getIntent();
        spinner_category = findViewById(R.id.spinner_category);
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
        tournamentService =  RetrofitClientInstance.
                getRetrofitInstance().create(TournamentService.class);
        Intent intent = getIntent();
        Log.d(TAG, intent.getStringExtra(EXTRA_TOURNAMENT) + "");
        if (intent.hasExtra(EXTRA_TOURNAMENT)) {
            setTitle("Torneo");

        }
        tourname = intent.getStringExtra(EXTRA_TOURNAMENT);


        Call<Tournament> call_get = tournamentService.getTournament(tourname);//cambiar
        call_get.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                Log.d(TAG,""+response.code());
                if(response.code()==200) {
                    tournament = response.body();
                    Log.d(TAG, "El torneo recibido contiene: \n" + tournament);
                    initRecicleview();
                    spinnerCategory();



                }
            }
            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });





        }



    public void initRecicleview() {


        tournamentDrawsListView = findViewById(R.id.tournamentDrawList);
        tournamentDrawsListView.setLayoutManager(new LinearLayoutManager(this));
        tournamentDrawAdapter = new MatchesAdapter(new MatchesDiffCallBack());
        tournamentDrawsListView.setAdapter(tournamentDrawAdapter);
        tournamentDrawAdapter.submitList(tournament.getRounds().get(0).getMatches());







    }

    public void spinnerCategory(){

        ArrayAdapter<String> category_adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        for(int i=0; i<tournament.getRounds().size(); i++) {
            category_adapter.add(tournament.getRounds().get(i).getId());
        }
        spinner_category.setAdapter(category_adapter);

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<Rounds> cat = tournament.getRounds();
                for(int i = 0; i<cat.size(); i++){
                    if(spinner_category.getSelectedItem().equals(cat.get(i).getId())){
                        Log.d("TAG", String.valueOf(i));
                        tournamentDrawAdapter.submitList(tournament.getRounds().get(i).getMatches());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}

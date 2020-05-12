package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchTournament extends AppCompatActivity {
    private RecyclerView tournamentsListView;
    private TournamentAdapter tournamentAdapter;
    private String TAG="SEARCHTOURN";
    private Intent intent;
    ArrayList<Tournament> tournaments_data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tournament);
        intent = getIntent();

        tournamentsListView = findViewById(R.id.tournamentList);
        tournamentsListView.setLayoutManager(new LinearLayoutManager(this));
        tournamentAdapter = new TournamentAdapter(new TournamentDiffCallBack());
//            TournamentAdapter.setOnItemClickListener(new TournamentAdapter().OnItemClickListener() {
//            @Override
//            public void onItemClick(Tournament tour) {
//                Log.d(TAG, tour.getUsername());
//                Intent intent = new Intent(Search.this, Torneo.class);
//                intent.putExtra(ProfilePublic.EXTRA_USERNAME, tour.getUsername());
//                startActivityForResult(intent, EDIT_EVENT);
//            }
//        });

        SearchList();




    }
    private TournamentService tournamentService;
    public void SearchList(){
        tournamentService = RetrofitClientInstance.
                getRetrofitInstance().create(TournamentService.class);

        Call<List<Tournament>> call_get_tournaments = tournamentService.getTournaments();
        call_get_tournaments.enqueue(new Callback<List<Tournament>>() {
            @Override
            public void onResponse(Call<List<Tournament>> call, Response<List<Tournament>> response) {
                if (response.code() == 200){
                    Log.d(TAG,"200");
                    // Obtenim les dades de la consulta
                    tournaments_data = (ArrayList<Tournament>) response.body();
                    tournamentAdapter.submitList(tournaments_data);
                } else{
                    // notificar problemes amb la consulta
                }
            }

            @Override
            public void onFailure(Call<List<Tournament>> call, Throwable t) {
                // Notificar problemes amb la red
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_tournament, menu);
        MenuItem item= menu.findItem(R.id.action_SearchTour);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.equals("") ){
                    tournamentAdapter.submitList(tournaments_data);
                    Log.d(TAG, "|Tournaments| = " + tournaments_data.size());
                }
                else {
                    ArrayList<Tournament> filteredModelList = (ArrayList<Tournament>) tournaments_data.stream()
                            .filter(Tournaments -> Tournaments.getName().contains(newText))
                            .collect(Collectors.toList());

                    Log.d(TAG, "|Tournaments| = " + filteredModelList.size());

                    tournamentAdapter.submitList(filteredModelList);
                }
                tournamentsListView.scrollToPosition(0);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



}

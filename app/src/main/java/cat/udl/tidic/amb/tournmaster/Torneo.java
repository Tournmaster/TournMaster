package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Torneo extends AppActivityMenu {
    public static final String EXTRA_TOURNAMENT =
            "cat.udl.tidic.amd.tournmaster.EXTRA_TOURNAMENT";
    private String TAG ="TORNEO";
    private Intent intent;
    private TournamentService tournamentService;
    private ImageView img_info;
    private TextView txt_info;
    private TextView txt_inscription;
    private Spinner category;
    private TextView txt_price;
    private ImageView masculino;
    private ImageView femenino;
    private ImageView mixto;
    private TextView txt_club;
    private TextView txt_start_date;
    private TextView txt_end_date;
    private TextView txt_name_tournament;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo);
        tournamentService =  RetrofitClientInstance.
                getRetrofitInstance().create(TournamentService.class);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TOURNAMENT)) {
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

        img_info = findViewById(R.id.image_info);
        txt_info = findViewById(R.id.text_info);
        category = findViewById(R.id.spinner);
        txt_inscription = findViewById(R.id.text_inscription_date2);
        txt_price = findViewById(R.id.text_price2);
        txt_club = findViewById(R.id.text_club);
        txt_start_date = findViewById(R.id.text_start_date);
        txt_end_date = findViewById(R.id.text_end_date);
        txt_name_tournament = findViewById(R.id.text_torneo_title);

        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog();
            }
        });
        txt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog();
            }
        });
        txt_inscription.setText("30 de febrero");

        category.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item));
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
        txt_name_tournament.setText(intent.getStringExtra(EXTRA_TOURNAMENT));
        String tourname = txt_name_tournament.getText().toString();
        Log.d(TAG,""+tourname);
        Call<Tournament> call_get = tournamentService.getTournament(tourname.trim());
        call_get.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                Log.d(TAG,""+response.code());
                if(response.code()==200) {
                    Tournament tournament = response.body();
                    txt_club.setText("CANSTRIXÉ");
                    txt_start_date.setText("24M");
                    txt_end_date.setText("27M");
                    txt_price.setText("24 euros");
                }
            }
            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }
    public void mostrarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Torneo.this);
        String tourname = txt_name_tournament.getText().toString();
        Log.d(TAG,""+tourname);
        Call<Tournament> call_get = tournamentService.getTournament(tourname.trim());
        call_get.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                Log.d(TAG,""+response.code());
                if(response.code()==200) {
                    Tournament tournament = response.body();
                    builder.setTitle(txt_name_tournament.getText());
                    builder.setMessage(tournament.getDescription());
                }
            }
            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
    public void AdapterCategory() {

    }

}

package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
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
    private View rootView;
    private String tourname;
    private Tournament tournament;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo);
        tournamentService =  RetrofitClientInstance.
                getRetrofitInstance().create(TournamentService.class);
        Intent intent = getIntent();
        Log.d(TAG, intent.getStringExtra(EXTRA_TOURNAMENT) + "");
        if (intent.hasExtra(EXTRA_TOURNAMENT)) {
            setTitle("Torneo");

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
        masculino = findViewById(R.id.img_hombre);
        femenino = findViewById(R.id.img_femenino);
        mixto = findViewById(R.id.img_mixto);



        tourname = intent.getStringExtra(EXTRA_TOURNAMENT);
        Log.d(TAG,""+tourname);

        Call<Tournament> call_get = tournamentService.getTournament(tourname);//cambiar
        call_get.enqueue(new Callback<Tournament>() {
            @Override
            public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                Log.d(TAG,""+response.code());
                if(response.code()==200) {
                    tournament = response.body();
                    Log.d(TAG, "El torneo recibido contiene: \n" + tournament);
                    assert tournament != null;
                    txt_club.setText(tournament.getOwner());
                    txt_start_date.setText(tournament.getStart());
                    txt_end_date.setText(tournament.getFinish());
                    txt_price.setText(tournament.getPrice_1());
                    txt_inscription.setText(tournament.getFinish_register_date());
                    txt_name_tournament.setText(tournament.getName());
                    initSpinner(tournament);
                    mostrarImagen(tournament);
                }
            }
            @Override
            public void onFailure(Call<Tournament> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        txt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tourname = intent.getStringExtra(EXTRA_TOURNAMENT);
                Call<Tournament> call_get = tournamentService.getTournament(tourname);//cambiar
                call_get.enqueue(new Callback<Tournament>() {
                    @Override
                    public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                        Log.d(TAG, "" + response.code());
                        if (response.code() == 200) {
                            Tournament tournament = response.body();
                            mostrarImagen(tournament);
                        }
                    }

                    @Override
                    public void onFailure(Call<Tournament> call, Throwable t) {

                    }

                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

            public void initSpinner(Tournament t){
        ArrayAdapter<String> category_adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        TournamentAge.names()) ;

        category.setAdapter(category_adapter);
    }

    public void mostrarImagen(Tournament t){
        List<Category> cat = t.getCategories();
        ArrayList generos = new ArrayList();
        int y=0;
        for (int i = 0; i<cat.size();i++){
            if(category.getSelectedItem().toString().equals(cat.get(i).toString())) {
                generos.add(cat.get(i).getGenere().name);
                y++;
            }
        }
        Log.d("categorias", String.valueOf(generos));
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i<generos.size();i++){
            if (generos.get(i).equals(TournamentGenere.X.name)) {
                mixto.setImageAlpha(255);
                a++;
            }else{if(a==0){mixto.setImageAlpha(50);}}

            if(generos.get(i).equals(TournamentGenere.F.name)){
                femenino.setImageAlpha(255);
                b++;
            }else{if(b==0){femenino.setImageAlpha(50);}}

            if(generos.get(i).equals(TournamentGenere.H.name)){
                masculino.setImageAlpha(255);
                c++;
            }else{if(c==0){masculino.setImageAlpha(50);}}
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DIALOGDESC", "" + resultCode);


    }
    public void mostrarDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Torneo.this);
                Intent intent = getIntent();
                String tourname = intent.getStringExtra(EXTRA_TOURNAMENT);
                Log.d(TAG,""+tourname);//tourname.trim()
                Call<Tournament> call_get = tournamentService.getTournament(tourname);//cambiar
                call_get.enqueue(new Callback<Tournament>() {
                    @Override
                    public void onResponse(Call<Tournament> call, Response<Tournament> response) {
                        Log.d(TAG,""+response.code());
                        if(response.code()==200) {
                            Tournament tournament = response.body();
                            builder.setView(rootView);
                            builder.setTitle("tournament.getName()");
                            builder.setMessage(tournament.getDescription());
                            builder.create();
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


    public void openDialog() {
        DialogDescription dialogDesc =  DialogDescription.newInstance(this, tournament);
        dialogDesc.show(getSupportFragmentManager(),"Dialog description");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_partidos, menu);
        return true;

    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_match) {
            Intent intent = new Intent(Torneo.this,MatchRounds.class);
            intent.putExtra(Torneo.EXTRA_TOURNAMENT, tournament.getId());
            startActivity(intent);
            Log.d(TAG, "Here we need to launch settings...");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

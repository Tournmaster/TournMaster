package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Inicio extends AppActivityMenu {
    private Intent intent;
    private Button but_unir;
    private Button but_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView(R.layout.activity_inicio);
        but_unir = findViewById(R.id.button2);
        but_crear = findViewById(R.id.button);
        but_unir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Inicio.this, Torneo.class);
                startActivity(intent);
            }
        });
    }


    // Si un usuari tira endarra, no es torna a login -> es tanca l'app :)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}

package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Inicio extends AppActivityMenu {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView(R.layout.activity_inicio);
    }


    // Si un usuari tira endarra, no es torna a login -> es tanca l'app :)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}

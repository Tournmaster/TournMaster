package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppActivityMenu {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView(R.layout.activity_inicio);
    }
    public void SearchTour(View view){
        Intent intent = new Intent(Inicio.this,SearchTournament.class);
        startActivity(intent);

    }

    // Si un usuari tira endarra, no es torna a login -> es tanca l'app :)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}

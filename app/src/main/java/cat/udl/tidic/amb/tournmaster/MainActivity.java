package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.inciarButton);
        Button register = findViewById(R.id.registerButton);
        getSupportActionBar().hide();

        SharedPreferences mPreferences = PreferencesProvider.providePreferences();
        String token = mPreferences.getString("token", "");

        if(!token.equals("")){
            Intent intent = new Intent(MainActivity.this,Inicio.class);
            startActivity(intent);
        }

    }

    public void login(View view){
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);

    }

    public void register(View view){
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }


}

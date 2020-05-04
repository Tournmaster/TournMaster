package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.inciarButton);
        Button register = findViewById(R.id.registerButton);
        getSupportActionBar().hide();

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

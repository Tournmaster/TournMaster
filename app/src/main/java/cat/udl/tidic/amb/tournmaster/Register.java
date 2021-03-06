package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import cat.udl.tidic.amb.tournmaster.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private UserService userService;
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText passwrod1;
    private RadioButton generoM;
    private RadioButton generoH;
    private RadioButton rolP;
    private RadioButton rolO;
    String genero;
    String rol;
    private ImageView img1;
    private TextView miss;
    private TextView error_con;
    private TextView error_null;
    int cont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        name = findViewById(R.id.nombre2);
        username = findViewById(R.id.nombre);
        password = findViewById(R.id.contraseña);
        passwrod1 = findViewById(R.id.contraseña2);
        Button iniciar = findViewById(R.id.inciarButton);
        generoM = findViewById(R.id.btn_mujer);
        generoH = findViewById(R.id.btn_hombre);
        rolP = findViewById(R.id.check_player);
        rolO= findViewById(R.id.check_owner);
        error_null = findViewById(R.id.miss_error_null);



        miss = findViewById(R.id.contra_error);
        img1 = findViewById(R.id.img_tick);
        error_con = findViewById(R.id.error_regist);
        img1.setVisibility(View.INVISIBLE);
        miss.setVisibility(View.INVISIBLE);
        error_con.setVisibility(View.INVISIBLE);
        error_null.setVisibility(View.INVISIBLE);

        passwrod1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = password.getText().toString();
                String pass2 =  passwrod1.getText().toString();


                    if (pass2.equals(pass)) {

                        img1.setVisibility(View.VISIBLE);
                        miss.setVisibility(View.INVISIBLE);
                        error_null.setVisibility(View.INVISIBLE);

                    }
                    else {
                        miss.setVisibility(View.VISIBLE);
                        img1.setVisibility(View.INVISIBLE);
                    }



            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });







        generoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generoH.setChecked(false);
                generoM.setChecked(true);
                genero = "F";
                error_null.setVisibility(View.INVISIBLE);
            }
        });
        generoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generoH.setChecked(true);
                generoM.setChecked(false);
                genero = "M";
                error_null.setVisibility(View.INVISIBLE);
            }
        });
        rolO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolO.setChecked(true);
                rolP.setChecked(false);
                rol = "O";
                error_null.setVisibility(View.INVISIBLE);
            }
        });
        rolP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolP.setChecked(true);
                rolO.setChecked(false);
                rol = "P";
                error_null.setVisibility(View.INVISIBLE);
            }
        });

            System.out.println(rol);
            userService = RetrofitClientInstance.
                    getRetrofitInstance().create(UserService.class);
            iniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        JsonObject user_json = new JsonObject();
                        try {

                            if (validar(name.getText().toString())) {
                                user_json.addProperty("username", name.getText().toString());
                                cont++;
                            } else {
                                System.out.println("El valor username no puede ser nullo");
                            }
                            if (validar(username.getText().toString())) {
                                user_json.addProperty("email", username.getText().toString());
                                cont++;
                            } else {
                                System.out.println("El valor email no puede ser nullo");
                            }
                            System.out.println(rol);
                            if (validar(rol)) {

                                user_json.addProperty("rol", rol);
                                cont++;
                            } else {

                                System.out.println("Debe selecionar un rol");
                            }
                            System.out.println(genero);
                            if (validar(genero)) {
                                user_json.addProperty("genere", genero);
                                cont++;
                            } else {

                                System.out.println("Debe selecionar un genero");
                            }
                            if (validar(password.getText().toString())) {

                                // Course API requires passwords in sha-256 in passlib format so:
                                String p = password.getText().toString();
                                String salt = "16";
                                String encode_hash = Utils.encode(p, salt, 29000);
                                System.out.println("PASSWORD_ENCRYPTED " + encode_hash);
                                user_json.addProperty("password", encode_hash);
                                cont++;

                            } else {
                                System.out.println("El valor password no puede ser nullo");
                            }
                            System.out.println(cont);
                            if(cont == 5) {
                                System.out.println("entra");
                                Call<Void> call = userService.createUser(user_json);
                                call.enqueue(new Callback<Void>() {

                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                        if (response.code() == 200) {
                                            Toast.makeText(Register.this, "User registered", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            startActivity(intent);

                                        } else {
                                            try {
                                                Toast.makeText(Register.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                        error_con.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            else{
                                error_con.setVisibility(View.VISIBLE);
                            }
                        }
                        catch (Exception e){
                            cont=0;
                            error_null.setVisibility(View.VISIBLE);
                        }
                    }



            });

    }
    public boolean validar(String n){

        System.out.println(n);

        if(n.isEmpty() || n.equals(" ")){

            return false;
        }
        else{
            return true;
        }
    }


}


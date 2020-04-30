package cat.udl.tidic.amb.tournmaster;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {
    private TextView user;
    private EditText mail;
    private EditText rol;
    private EditText sex;
    private EditText name;
    private EditText surname;
    private EditText birthday;
    private EditText prefsmash;
    private EditText timeplay;
    private EditText matchaname;
    private EditText club;
    private EditText license;
    private Button iniciar;
    private Button cancelar;
    private Button actualizar;
    private ImageView img_photo;
    private EditText position;
    private EditText phone;
    private RadioButton rigth;
    private RadioButton left;
    private String valuePos;



    private String TAG = "Perfil";

    private UserService userService;
    private SharedPreferences mPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Perfil);
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

        readPermission();

        img_photo = findViewById(R.id.img_perfil);
        user = findViewById(R.id.text_users);
        mail = findViewById(R.id.text_mail);
        rol = findViewById(R.id.text_rol);
        sex = findViewById(R.id.text_sexo);
        name = findViewById(R.id.ed_name);
        surname = findViewById(R.id.ed_surname);
        birthday = findViewById(R.id.text_birthday);
        prefsmash = findViewById(R.id.ed_smash);
        timeplay = findViewById(R.id.ed_timeplay);
        matchaname = findViewById(R.id.ed_matchname);
        club = findViewById(R.id.ed_club);
        license = findViewById(R.id.ed_license);
        iniciar = findViewById(R.id.inciarButton);
        actualizar = findViewById(R.id.btn_actualizarperfil);
        cancelar = findViewById(R.id.btn_cancelar);
        iniciar = findViewById(R.id.inciarButton);
        position = findViewById(R.id.ed_position);
        phone = findViewById(R.id.ed_phone);
        rigth = findViewById(R.id.rdbtn_right);
        left = findViewById(R.id.rdbtn_left);

        img_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog();
            }
        });

        rigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setChecked(false);
                rigth.setChecked(true);
                valuePos = "R";

                Log.d("POSIC",valuePos);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setChecked(true);
                rigth.setChecked(false);
                valuePos = "L";
            Log.d("POSIC",valuePos);
            }
        });





        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");


        Call<JsonObject> call_get = userService.getUserProfile(token);
        call_get.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("ERROR",response.code()+"");
                if(response.code()==200){
                    //response.body();
                    JsonObject userJson = response.body();
                    String nom_user = userJson.get("username").toString();
                    user.setText(atributs(nom_user));
                    String user_surname = userJson.get("surname").toString();
                    surname.setText(atributs(user_surname));
                    String user_name = userJson.get("name").toString();
                    name.setText(atributs(user_name));
                    String user_prefplayer = userJson.get("matchname").toString();
                    matchaname.setText(atributs(user_prefplayer));
                    String user_smash = userJson.get("prefsmash").toString();
                    prefsmash.setText(atributs(user_smash));
                    String user_club = userJson.get("club").toString();
                    club.setText(atributs(user_club));
                    String user_phone= (userJson.get("phone").toString());
                    phone.setText(atributs(user_phone));
                    isValidPhoneNumber(phone.getText().toString());
                    String user_mail = userJson.get("email").toString();
                    mail.setText(atributs(user_mail));
                    String user_sex= userJson.get("genere").toString();
                    isValidEmailAddress(mail.getText().toString());
                    user_sex = user_sex.substring(1,user_sex.length()-1);
                    Log.d("TAG",user_sex);

                    if(user_sex.equals("M")){
                        Log.d("TAG","ENTRA");
                        sex.setText("Hombre");
                    }
                    else{
                        sex.setText("Mujer");
                    }
                    String user_license= userJson.get("license").toString();
                    user_license = user_license.substring(1,user_license.length()-1);
                    Log.d("TAG",user_license);

                    if(user_license.equals("Y")){
                        Log.d("TAG","ENTRA");
                        license.setText("Tienes licencia");
                    }
                    else{
                        license.setText("No tienes liencia");
                    }
                    String user_position= userJson.get("position").toString();
                    user_position = user_position.substring(1,user_position.length()-1);
                    Log.d("TAG",user_position);

                    if(user_position.equals("R")){
                        Log.d("TAG","ENTRA");
                        position.setText("Derecha");
                        rigth.setSelected(true);
                    }
                    else{
                        position.setText("Izquierda");
                        left.setSelected(true);
                    }
                    Log.d("TAG",user_sex);
                    String user_rol = userJson.get("rol").toString();
                    Log.d("rol",user_rol);
                    user_rol=user_rol.substring(1,user_rol.length()-1);
                    if(user_rol.equals("O")){

                        rol.setText("Organizador");
                    }

                    else{
                        rol.setText("Jugador");
                    }
                    actualizar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JsonObject user_json = new JsonObject();
                            try {

                                    // @JordiMateoUdL: Heu de vigilar la property té que tenir el mateix nom que a models del python, sinó error :)
                                    // Si s'envieen sempre tots els camps -> afegiu tots els camps a User i despres enlloc d'enviar un JSON envieu el Usuari
                                    // Molt més net i us evitareu errors tontos...

                                    user_json.addProperty("username", name.getText().toString());
                                    user_json.addProperty("surname", surname.getText().toString());
                                    user_json.addProperty("email", mail.getText().toString());
                                    user_json.addProperty("phone", phone.getText().toString());
                                    user_json.addProperty("matchname", matchaname.getText().toString());
                                    user_json.addProperty("prefsmash", prefsmash.getText().toString());
                                    user_json.addProperty("club", club.getText().toString());


                                    user_json.addProperty("position", valuePos);


                                    System.out.println("Debe selecionar una posicion");

                                Call<Void>call = userService.updateAccount(token, user_json);
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.code() == 200) {
                                            System.out.println("entras?");
                                            Toast.makeText(Perfil.this, "Properties Changed", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Perfil.this, Perfil.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            try {
                                                Toast.makeText(Perfil.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });


                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });



                }
                else{
                    //missatge de error;
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG",t.getMessage().toString());
            }
        });

    }
    public String atributs(String n){

        n = n.substring(1,n.length()-1);

        return n;

    }

    public void cerrarSession(View view){

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");


        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        Call<ResponseBody> call_delete= userService.deleteToken(token);
        call_delete.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                    Log.d("FUNC","entras?");

                        mPreferences.edit().putString("token", "").apply();
                        Intent intent = new Intent(Perfil.this, Login.class);
                        startActivity(intent);
                }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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
    public void Inico (View view){

        Intent intent = new Intent(Perfil.this,MainActivity.class);
        startActivity(intent);
    }

    public void editar(View view){
        mail.setEnabled(true);
        sex.setEnabled(true);
        rol.setEnabled(true);
        name.setEnabled(true);
        surname.setEnabled(true);
        birthday.setEnabled(true);
        position.setVisibility(View.INVISIBLE);
        prefsmash.setEnabled(true);
        timeplay.setEnabled(true);
        matchaname.setEnabled(true);
        club.setEnabled(true);
        license.setEnabled(true);
        phone.setEnabled(true);
        cancelar.setVisibility(View.VISIBLE);
        iniciar.setVisibility(View.INVISIBLE);
        actualizar.setVisibility(View.VISIBLE);
        rigth.setVisibility(View.VISIBLE);
        left.setVisibility(View.VISIBLE);




    }
    public void cancelarCambios(View view){

        mail.setEnabled(false);
        sex.setEnabled(false);
        rol.setEnabled(false);
        name.setEnabled(false);
        surname.setEnabled(false);
        birthday.setEnabled(false);
        position.setEnabled(false);
        position.setVisibility(View.VISIBLE);
        prefsmash.setEnabled(false);
        timeplay.setEnabled(false);
        matchaname.setEnabled(false);
        club.setEnabled(false);
        license.setEnabled(false);
        cancelar.setVisibility(View.INVISIBLE);
        iniciar.setVisibility(View.VISIBLE);
        actualizar.setVisibility(View.INVISIBLE);
        rigth.setVisibility(View.INVISIBLE);
        left.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.perfil_button,menu);
        return true;
    }
    public void openDialog() {
        DialogImage dialogImage = new DialogImage().newInstance(this);
        dialogImage.show(getSupportFragmentManager(),"example_dialog");
    }



    private boolean readPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(),"I need access to images", Toast.LENGTH_LONG);
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    21);
            return false;
        }else{
            return true;
        }
    }

    public void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent, 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DIALOG", "" + resultCode);
        if (resultCode == RESULT_OK) {
            String token;
            this.mPreferences = PreferencesProvider.providePreferences();
            token = this.mPreferences.getString("token", "");


            userService = RetrofitClientInstance.
                    getRetrofitInstance().create(UserService.class);

            Uri path = data.getData();
            img_photo.setImageURI(path);
            File file1 = new File(getRealPathFromURI(path, this));
            RequestBody requestFile2 = RequestBody.create(MediaType.parse(getContentResolver().getType(data.getData())), file1);
            MultipartBody.Part body2 = MultipartBody.Part.createFormData("image_file", file1.getName(), requestFile2);

            Call<ResponseBody> call_update = userService.uploadImage(token, body2);
            call_update.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d(TAG, "Success " + response.code());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });

        }


    }

    public String getRealPathFromURI(Uri uri, Activity activity) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
    public boolean isValidEmailAddress(String email){
        final String MAIL_PATTERN =
                "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(MAIL_PATTERN);
    }



    public boolean isValidPassword(final String password) {
        Pattern pattern; Matcher matcher;
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidPhoneNumber(final String phonenumber){

        final String numeros =
                "(6|7)[ -]*([0-9][ -]*){9}$";
        return phonenumber.matches(numeros);

    }



}
package cat.udl.tidic.amb.tournmaster;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppActivityMenu {

    // Visual components (UI)
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

    // Tag for debug
    private String TAG = "Perfil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView(R.layout.activity_profile);
        readPermission();
        getUserProfile();
    }

    @Override
    protected void initView(int layout){
        // @JordiMateoUdl: En aquest punt inicialitzem els components del pare:
        // - Menu
        // - UserService
        // - SharedPreferences
        super.initView(layout);

        // @JordiMateoUdl: Ara els propis de la vista
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

                Log.d(TAG, "El valor del elemento valuePos después del click (left)  és::" + valuePos);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setChecked(true);
                rigth.setChecked(false);
                valuePos = "L";
                Log.d(TAG, "El valor del elemento valuePos después del click (left)  és:" + valuePos);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = getUserChanges();
                updateUserProfile(u);
            }
        });
    }

    public void getUserProfile() {
        // @JordiMateoUdl -> Podem posar el enque per obtenir usuari
        System.out.print(this.userService);
        Call<User> call_get = this.userService.getUserProfile();
        call_get.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 200) {
                    Log.d(TAG, "getUserProfile -> API ha devuelto el codigo: " + response.code() + "");
                    User u = response.body();
                    Log.d(TAG, "Position: " + u.getPosicion() +" = "+u.getPositionText());
                    bindUserToUI(u);
                } else {
                    Log.d(TAG, "getUserProfile -> API ha devuelto el codigo: " + response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "getUserProfile -> Problemas con la comunicación con API: " + t.getMessage() + "");

            }
        });
    }

    public void updateUserProfile(User u){
        Call<Void>call = this.userService.updateAccount(u);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "updateUserProfile -> API ha devuelto el codigo: " + response.code() + "");
                    Toast.makeText(Perfil.this, "Properties Changed", Toast.LENGTH_SHORT).show();
                    getUserProfile();
                    cancelarCambios(getCurrentFocus());
                }
                else {
                    Log.d(TAG, "updateUserProfile -> API ha devuelto el codigo: " + response.code() + "");
                    try {
                        Toast.makeText(Perfil.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "updateUserProfile error en la comunicación con l'API: " + t.getMessage() + "");
            }
        });
    }

    public void bindUserToUI(User u) {

        if (u != null) {
            Picasso.get().load(u.getPhoto()).into(img_photo);
            user.setText(u.getUsername());
            surname.setText(u.getSurname());
            mail.setText(u.getEmail());
            name.setText(u.getName());
            matchaname.setText(u.getMatchname());
            prefsmash.setText(u.getPrefSmashText());
            club.setText(u.getClub());
            phone.setText(u.getPhone());
            sex.setText(u.getGenereText());
            license.setText(u.hasLicense());
            position.setText(u.getPositionText());
            if (u.getPosicion().equals("R")){
                left.setChecked(true);
                rigth.setChecked(false);
                valuePos = "R";
            }else{
                rigth.setChecked(true);
                left.setChecked(false);
                valuePos = "L";
            }
            rol.setText(u.getRolText());
        }
    }

    // Con esta función creamos un usuario con les valores entrados en el formulario
    public User getUserChanges() {
        User u = new User();
        u.setUsername(name.getText().toString());
        u.setSurname(surname.getText().toString());
        u.setEmail(mail.getText().toString());
        u.setClub(phone.getText().toString());
        u.setMatchname(matchaname.getText().toString());
        u.setPrefSmash(prefsmash.getText().toString());
        u.setClub(club.getText().toString());
        u.setPosicion(valuePos);
        return u;
    }




    public void cerrarSession(View view){
        Call<ResponseBody> call_delete= userService.deleteToken(token);
        call_delete.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200){
                    Log.d(TAG, "deleteToken -> API ha devuelto el codigo: " + response.code() + "");
                    mPreferences.edit().putString("token", "").apply();
                    Intent intent = new Intent(Perfil.this, Login.class);
                    startActivity(intent);
                }else{
                    Log.d(TAG, "deleteToken -> API ha devuelto el codigo: " + response.code() + "");
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "deleteToken -> API ha devuelto el codigo: " + t.getMessage() + "");
            }
        });
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


    public void openDialog() {
        DialogImage dialogImage = new DialogImage().newInstance(this);
        dialogImage.show(getSupportFragmentManager(),"Profile Photo Dialog");
    }


    private boolean readPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

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
        Log.d(TAG, "Dialog result: " + resultCode);

        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            img_photo.setImageURI(path);

            File file1 = new File(getRealPathFromURI(path, this));
            RequestBody requestFile2 = RequestBody.create(MediaType.parse(getContentResolver().getType(data.getData())), file1);
            MultipartBody.Part body2 = MultipartBody.Part.createFormData("image_file", file1.getName(), requestFile2);

            Call<ResponseBody> call_update = this.userService.uploadImage(body2);
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


    // @JordiMateoUdl -> Moure a un classe utils se comparteix abm el registre :)
    public boolean isValidEmailAddress(String email){
        final String MAIL_PATTERN =
                "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(MAIL_PATTERN);
    }

    // @JordiMateoUdl -> Moure a un classe utils se comparteix abm el registre :)
    public boolean isValidPassword(final String password) {
        Pattern pattern; Matcher matcher;
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // @JordiMateoUdl -> Moure a un classe utils se comparteix abm el registre :)
    public boolean isValidPhoneNumber(final String phonenumber){

        final String numeros =
                "(6|7)[ -]*([0-9][ -]*){9}$";
        return phonenumber.matches(numeros);

    }






}
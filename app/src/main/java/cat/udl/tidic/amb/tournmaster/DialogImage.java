package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.squareup.picasso.Picasso;

import cat.udl.tidic.amb.tournmaster.services.UserService;

public class DialogImage extends AppCompatDialogFragment {
    private ImageView image_photo;
    private UserService userService;
    private SharedPreferences mPreferences;
    private String TAG = "DIALOGFILTER";
    private Perfil activity;
    private View rootView;
    private User user;

    public static DialogImage newInstance(Perfil activity){
        DialogImage dialog = new DialogImage();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("Selecciona la imagen")
                .setCancelable(false)
                .setPositiveButton("Cargar", null)
                .create();
        alertDialog.setOnShowListener( dialog -> onDialogShow(alertDialog));
        return alertDialog;
    }

    private void init(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog, null, false);
        user = new User();
        image_photo = rootView.findViewById(R.id.img_imageview);

        Log.d(TAG, "Photo URl:" + user.getPhoto());
        Picasso.get().load(user.getPhoto()).into(image_photo);


    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener( v -> {
            activity.cargarImagen();
            dismiss();
        });
    }



}

package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cat.udl.tidic.amb.tournmaster.services.UserService;

public class DialogFilter extends AppCompatDialogFragment {
    private ImageView image_female;
    private ImageView image_male;
    private ImageView image_rigth;
    private ImageView image_left;
    private String genero;
    private String position;
    private String golpe;
    private Spinner combobox;
    private RadioButton female;
    private RadioButton male;
    private RadioButton rigth;
    private RadioButton left;
    private UserService userService;
    private SharedPreferences mPreferences;
    private String TAG = "DIALOGFILTER";
    private Search activity;
    private View rootView;
    private CheckBox g_derecha;
    private CheckBox g_izquierda;
    private CheckBox g_glovo;
    private CheckBox g_saque;
    private CheckBox g_cortada;
    private CheckBox g_volea;
    private CheckBox g_mate;


    public static DialogFilter newInstance(Search activity){
        DialogFilter dialog = new DialogFilter();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("Selecciona uno de los filtros")
                .setCancelable(false)
                .setPositiveButton("Buscar", null)
                .create();
        alertDialog.setOnShowListener( dialog -> onDialogShow(alertDialog));
        return alertDialog;

    }

    private void init(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialgfilter, null, false);
        image_female = rootView.findViewById(R.id.img_female);
        image_male = rootView.findViewById(R.id.img_male);
        image_left = rootView.findViewById(R.id.img_left);
        image_rigth= rootView.findViewById(R.id.img_rigth);
        female = rootView.findViewById(R.id.rdbtn_female);
        male= rootView.findViewById(R.id.rdbtn_male);
        left = rootView.findViewById(R.id.Boto_esq);
        rigth = rootView.findViewById(R.id.Boto_dere);
        g_derecha = rootView.findViewById(R.id.g_Derecha);
        g_izquierda = rootView.findViewById(R.id.g_Izquier);
        g_cortada = rootView.findViewById(R.id.g_Cortada);
        g_glovo = rootView.findViewById(R.id.g_Globo);
        g_mate = rootView.findViewById(R.id.g_Mate);
        g_saque = rootView.findViewById(R.id.g_Saque);
        g_volea = rootView.findViewById(R.id.g_Volea);






    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener( v -> {
            activity.populateList(null,null,null);
            dismiss();
        });
    }



}

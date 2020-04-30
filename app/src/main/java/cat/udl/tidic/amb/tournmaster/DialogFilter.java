package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

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
    private RadioButton female;
    private RadioButton male;
    private RadioButton posrigth;
    private RadioButton posleft;
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
    private String prefsmash;
    private String drcha;
    private String rves;
    private String globs;
    private String saq;
    private String crtada;
    private String vlea;
    private String mte;
    private  String optionTwo;

    public static DialogFilter newInstance(Search activity){
        DialogFilter dialog = new DialogFilter();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        female = rootView.findViewById(R.id.rdbtn_female);
        male= rootView.findViewById(R.id.rdbtn_male);
        posleft = rootView.findViewById(R.id.Boto_esq);
        posrigth = rootView.findViewById(R.id.Boto_dere);
        g_derecha = rootView.findViewById(R.id.g_Derecha);
        g_izquierda = rootView.findViewById(R.id.g_Izquier);
        g_cortada = rootView.findViewById(R.id.g_Cortada);
        g_glovo = rootView.findViewById(R.id.g_Globo);
        g_mate = rootView.findViewById(R.id.g_Mate);
        g_saque = rootView.findViewById(R.id.g_Saque);
        g_volea = rootView.findViewById(R.id.g_Volea);

        g_derecha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   drcha="R";
                   prefsmash="R";
                   optionTwo="S";

               }
           }
        );
        g_glovo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                     prefsmash="G";


                 }
             }
        );
        g_izquierda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                     prefsmash="L";

                 }
             }
        );
        g_saque.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                   prefsmash="S";

               }
           }
        );
        g_cortada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                   prefsmash="C";

               }
           }
        );
        g_volea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                     prefsmash="V";

                 }
             }
        );
        g_mate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                   prefsmash="M";

               }
           }
        );

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                female.setChecked(true);
                genero = "F";

            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(false);
                male.setChecked(true);
                genero = "M";

            }
        });
        posleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posrigth.setChecked(false);
                posleft.setChecked(true);
                position = "L";

            }
        });
        posrigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posleft.setChecked(false);
                posrigth.setChecked(true);
                position = "R";

            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("")
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

    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        positiveButton.setOnClickListener( v -> {
            activity.populateList(genero,position,prefsmash);
            dismiss();
        });
    }



}

package cat.udl.tidic.amb.tournmaster;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import cat.udl.tidic.amb.tournmaster.services.UserService;

public class DialogDescription extends AppCompatDialogFragment {
    private ImageView image_photo;
    private UserService userService;
    private SharedPreferences mPreferences;
    private String TAG = "DIALOGFILTER";
        private Torneo torneo;
    private View rootView;
    private TextView desc;
    private Tournament tournament;
    private TournamentService tournamentService;
    public static DialogDescription newInstance(Torneo torneo, Tournament tournament){
        DialogDescription dialog = new DialogDescription();
        dialog.torneo = torneo;
        dialog.tournament = tournament;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("Descripcion del torneo")
                .setCancelable(true)
                .setPositiveButton("Cerrar", null)
                .create();
        return alertDialog;
    }
    private void init(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_description, null, false);
        Log.d(TAG, tournament.toString());
        ((TextView) rootView.findViewById(R.id.text_descriptionTour)).setText(tournament.getDescription());
        //Picasso.get().load(user.getPhoto()).into(image_photo);


    }
    private void onDialogShow(AlertDialog dialog) {


    }
}

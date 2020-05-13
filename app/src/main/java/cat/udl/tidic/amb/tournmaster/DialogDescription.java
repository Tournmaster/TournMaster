package cat.udl.tidic.amb.tournmaster;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.squareup.picasso.Picasso;

import cat.udl.tidic.amb.tournmaster.retrofit.RetrofitClientInstance;
import cat.udl.tidic.amb.tournmaster.services.TournamentService;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import static cat.udl.tidic.amb.tournmaster.Torneo.EXTRA_TOURNAMENT;

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
    public static DialogDescription newInstance(Torneo torneo){
        DialogDescription dialog = new DialogDescription();
        dialog.torneo = torneo;
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
                .setPositiveButton("Cargar", null)
                .create();
        alertDialog.setOnShowListener( dialog -> onDialogShow(alertDialog));
        return alertDialog;
    }
    private void init(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog, null, false);
        Intent intent = getIntent();
        Log.d(TAG, intent.getStringExtra(EXTRA_TOURNAMENT) + "");
        if (intent.hasExtra(EXTRA_TOURNAMENT)) {
            setTitle("Torneo");

        }
        tournamentService =  RetrofitClientInstance.
                getRetrofitInstance().create(TournamentService.class);
        Call<Tournament> call_get = tournamentService.getTournament(tourname);
        Log.d(TAG, "Photo URl:");
        //Picasso.get().load(user.getPhoto()).into(image_photo);


    }
    private void onDialogShow(AlertDialog dialog) {


    }
}

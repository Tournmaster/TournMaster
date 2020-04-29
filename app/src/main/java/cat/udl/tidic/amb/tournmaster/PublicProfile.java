package cat.udl.tidic.amb.tournmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PublicProfile extends AppCompatActivity {
    public static final String EXTRA_USERNAME =
            "cat.udl.tidic.amd.tournmaster.EXTRA_USERNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicprofile);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_USERNAME)) {
            setTitle("Edit Event");

        } else {
            setTitle("Add Event");
        }
    }

}

package demirciy.ygslyspuanhesaplama.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demirciy.ygslyspuanhesaplama.R;

public class ActivityWhatIsYgsLys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_ygs_lys);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

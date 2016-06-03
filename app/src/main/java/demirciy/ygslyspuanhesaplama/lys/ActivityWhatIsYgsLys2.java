package demirciy.ygslyspuanhesaplama.lys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.lys.ActivityLys;

public class ActivityWhatIsYgsLys2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_ygs_lys2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, ActivityLys.class);

        switch (item.getItemId())
        {
            case R.id.home:
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}

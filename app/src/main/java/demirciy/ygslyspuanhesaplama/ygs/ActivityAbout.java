package demirciy.ygslyspuanhesaplama.ygs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import demirciy.ygslyspuanhesaplama.R;

public class ActivityAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, ActivityYgs.class);

        switch (item.getItemId())
        {
            case R.id.home:
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}

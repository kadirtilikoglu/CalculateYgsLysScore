package demirciy.ygslyspuanhesaplama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class About2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, Lys.class);

        switch (item.getItemId())
        {
            case R.id.home:
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}

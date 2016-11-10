package demirciy.ygslyspuanhesaplama.common;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.base.ActivityBase;

public class ActivityFindUni extends ActivityBase {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_uni);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_find_uni, menu);

        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}

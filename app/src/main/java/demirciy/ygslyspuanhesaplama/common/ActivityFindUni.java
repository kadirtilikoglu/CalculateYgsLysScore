package demirciy.ygslyspuanhesaplama.common;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.adapter.AdapterFindUni;
import demirciy.ygslyspuanhesaplama.base.ActivityBase;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;

public class ActivityFindUni extends ActivityBase {

    Toolbar toolbar;

    ExpandableListView expLw;

    ArrayList<String> alParent, alChild;

    TextView tYgs1, tYgs2, tYgs3, tYgs4, tYgs5, tYgs6;

    Double ygs1, ygs2, ygs3, ygs4, ygs5, ygs6;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_uni);

        myDb = new DatabaseHelper(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tYgs1 = (TextView) findViewById(R.id.tYgs1);
        tYgs2 = (TextView) findViewById(R.id.tYgs2);
        tYgs3 = (TextView) findViewById(R.id.tYgs3);
        tYgs4 = (TextView) findViewById(R.id.tYgs4);
        tYgs5 = (TextView) findViewById(R.id.tYgs5);
        tYgs6 = (TextView) findViewById(R.id.tYgs6);

        expLw = (ExpandableListView) findViewById(R.id.expLw);

        showScores();

        HashMap<Integer, List<String>> parent = new HashMap<>();
        HashMap<List<String>, ArrayList<String>> child = new HashMap<>();

        for (int i = 0; i < 3; i++) {

            alParent = new ArrayList<>();

            alParent.add("üni" + i);
            alParent.add("bölüm" + i);
            alParent.add("puan türü" + i);
            alParent.add("taban puanı" + i);

            parent.put(i, alParent);
        }

        for (int i = 0; i < 3; i++) {

            alChild = new ArrayList<>();

            alChild.add("kontenjan" + i);
            alChild.add("gecen sene taban puan" + i);

            child.put(parent.get(i), alChild);
        }

        AdapterFindUni adapterFindUni = new AdapterFindUni(this, parent, child);
        expLw.setAdapter(adapterFindUni);
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

    private void showScores() {

        try {

            Cursor res = myDb.getYgsDatasForLys();

            while (res.moveToNext()) {

                ygs1 = res.getDouble(4);
                ygs2 = res.getDouble(5);
                ygs3 = res.getDouble(6);
                ygs4 = res.getDouble(7);
                ygs5 = res.getDouble(8);
                ygs6 = res.getDouble(9);
            }

            tYgs1.setText(String.format(Locale.getDefault(), "Ygs-1 : %.2f", ygs1));
            tYgs2.setText(String.format(Locale.getDefault(), "Ygs-2 : %.2f", ygs2));
            tYgs3.setText(String.format(Locale.getDefault(), "Ygs-3 : %.2f", ygs3));
            tYgs4.setText(String.format(Locale.getDefault(), "Ygs-4 : %.2f", ygs4));
            tYgs5.setText(String.format(Locale.getDefault(), "Ygs-5 : %.2f", ygs5));
            tYgs6.setText(String.format(Locale.getDefault(), "Ygs-6 : %.2f", ygs6));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

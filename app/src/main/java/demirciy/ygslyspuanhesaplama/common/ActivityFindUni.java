package demirciy.ygslyspuanhesaplama.common;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
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
    LinearLayout layoutYgs, layoutLys;
    ExpandableListView expLw;
    TextView tYgs1, tYgs2, tYgs3, tYgs4, tYgs5, tYgs6, tMf1, tMf2, tMf3, tMf4, tTm1, tTm2, tTm3,
    tTs1, tTs2, tLang1, tLang2, tLang3;

    ArrayList<String> alParent, alChild;

    Double ygs1, ygs2, ygs3, ygs4, ygs5, ygs6, mf1, mf2, mf3, mf4, tm1, tm2, tm3, ts1, ts2, lang1,
            lang2, lang3;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_uni);

        myDb = new DatabaseHelper(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutYgs = (LinearLayout) findViewById(R.id.layoutYgs);
        layoutLys = (LinearLayout) findViewById(R.id.layoutLys);
        tYgs1 = (TextView) findViewById(R.id.tYgs1);
        tYgs2 = (TextView) findViewById(R.id.tYgs2);
        tYgs3 = (TextView) findViewById(R.id.tYgs3);
        tYgs4 = (TextView) findViewById(R.id.tYgs4);
        tYgs5 = (TextView) findViewById(R.id.tYgs5);
        tYgs6 = (TextView) findViewById(R.id.tYgs6);
        tMf1 = (TextView) findViewById(R.id.tMf1);
        tMf2 = (TextView) findViewById(R.id.tMf2);
        tMf3 = (TextView) findViewById(R.id.tMf3);
        tMf4 = (TextView) findViewById(R.id.tMf4);
        tTm1 = (TextView) findViewById(R.id.tTm1);
        tTm2 = (TextView) findViewById(R.id.tTm2);
        tTm3 = (TextView) findViewById(R.id.tTm3);
        tTs1 = (TextView) findViewById(R.id.tTs1);
        tTs2 = (TextView) findViewById(R.id.tTs2);
        tLang1 = (TextView) findViewById(R.id.tLang1);
        tLang2 = (TextView) findViewById(R.id.tLang2);
        tLang3 = (TextView) findViewById(R.id.tLang3);
        expLw = (ExpandableListView) findViewById(R.id.expLw);

        checkExam();

        HashMap<Integer, List<String>> parent = new HashMap<>();
        HashMap<List<String>, ArrayList<String>> child = new HashMap<>();

        for (int i = 0; i < 3; i++) {

            alParent = new ArrayList<>();

            alParent.add("üni" + i);
            alParent.add("bölüm" + i);
            alParent.add("taban puanı" + i);

            parent.put(i, alParent);
        }

        for (int i = 0; i < 3; i++) {

            alChild = new ArrayList<>();

            alChild.add("puan türü" + i);
            alChild.add("yerlestirilen" + i);
            alChild.add("kontenjan" + i);

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

    private void checkExam() {

        Intent i = getIntent();
        String from = i.getExtras().getString("from");

        if (from.equalsIgnoreCase("ActivityYgs")) {

            layoutLys.setVisibility(View.GONE);
            showYgsScore();

        } else if (from.equalsIgnoreCase("ActivityLys")) {

            layoutYgs.setVisibility(View.GONE);
            showLysScore();
        }
    }

    private void showYgsScore() {

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

    private void showLysScore() {

        try {

            Cursor res = myDb.getYgsDatasForLys();

            while (res.moveToNext()) {

                mf1 = res.getDouble(10);
                mf2 = res.getDouble(11);
                mf3 = res.getDouble(12);
                mf4 = res.getDouble(13);
                tm1 = res.getDouble(14);
                tm2 = res.getDouble(15);
                tm3 = res.getDouble(16);
                ts1 = res.getDouble(17);
                ts2 = res.getDouble(18);
                lang1 = res.getDouble(19);
                lang2 = res.getDouble(20);
                lang3 = res.getDouble(21);
            }

            tMf1.setText(String.format(Locale.getDefault(), "MF-1 : %.2f", mf1));
            tMf2.setText(String.format(Locale.getDefault(), "MF-2 : %.2f", mf2));
            tMf3.setText(String.format(Locale.getDefault(), "MF-3 : %.2f", mf3));
            tMf4.setText(String.format(Locale.getDefault(), "MF-4 : %.2f", mf4));
            tTm1.setText(String.format(Locale.getDefault(), "TM-1 : %.2f", tm1));
            tTm2.setText(String.format(Locale.getDefault(), "TM-2 : %.2f", tm2));
            tTm3.setText(String.format(Locale.getDefault(), "TM-3 : %.2f", tm3));
            tTs1.setText(String.format(Locale.getDefault(), "TS-1 : %.2f", ts1));
            tTs2.setText(String.format(Locale.getDefault(), "TS-2 : %.2f", ts2));
            tLang1.setText(String.format(Locale.getDefault(), "Dil-1 : %.2f", lang1));
            tLang2.setText(String.format(Locale.getDefault(), "Dil-2 : %.2f", lang2));
            tLang3.setText(String.format(Locale.getDefault(), "Dil-3 : %.2f", lang3));

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}

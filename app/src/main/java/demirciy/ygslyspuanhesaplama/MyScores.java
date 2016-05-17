package demirciy.ygslyspuanhesaplama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyScores extends AppCompatActivity {

    ExpandableListView expMyScores;

    String[] Headers, Datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        expMyScores = (ExpandableListView) findViewById(R.id.expMyScores);

    }

    public void process(String[] Headers, String[] Datas) {

        this.Headers = Headers;
        this.Datas = Datas;

        List<String> expHeaders = new ArrayList<String>();
        List<String> expDatas = new ArrayList<String>();


        for (String header : Headers) {
            expHeaders.add(header);
        }

        for (String data : Datas) {
            expDatas.add(data);
        }

    }

}

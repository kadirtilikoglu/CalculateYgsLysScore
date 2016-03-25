package demirciy.ygslyspuanhesaplama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

public class YgsMyScores extends AppCompatActivity {

    HashMap<String, List<String>> ygsScores;
    List<String> ygsScoreDatas;
    ExpandableListView expList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ygs_my_scores);
    }
}

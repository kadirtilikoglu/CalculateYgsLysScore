package demirciy.ygslyspuanhesaplama.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import demirciy.ygslyspuanhesaplama.model.AllScores;
import demirciy.ygslyspuanhesaplama.util.CustomDialog;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 13.10.2016
 * Time     : 14:34
 **/
public class ActivityBase extends AppCompatActivity {

    public void createDialog(Context context, CustomDialog.From from, AllScores allScores) {

        CustomDialog dialog = new CustomDialog(context, from, allScores);

        dialog.build();
    }
}

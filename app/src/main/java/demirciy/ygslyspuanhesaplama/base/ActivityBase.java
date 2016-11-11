package demirciy.ygslyspuanhesaplama.base;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import demirciy.ygslyspuanhesaplama.model.AllScores;
import demirciy.ygslyspuanhesaplama.util.CustomDialog;
import demirciy.ygslyspuanhesaplama.util.ToastMessage;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 13.10.2016
 * Time     : 14:34
 **/
public class ActivityBase extends AppCompatActivity {

    public void createDialog(Context context, AllScores allScores) {

        CustomDialog dialog = new CustomDialog(context, allScores);

        dialog.build();
    }
}

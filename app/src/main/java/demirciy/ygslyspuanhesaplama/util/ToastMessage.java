package demirciy.ygslyspuanhesaplama.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 13.10.2016
 * Time     : 14:33
 **/
public class ToastMessage extends Toast {

    private Context context;

    public ToastMessage(Context context) {
        super(context);

        this.context = context;
    }

    public void show(String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

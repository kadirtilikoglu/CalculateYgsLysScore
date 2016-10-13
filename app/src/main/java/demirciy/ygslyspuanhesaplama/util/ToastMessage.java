package demirciy.ygslyspuanhesaplama.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 13.10.2016
 * Time     : 14:33
 **/
public class ToastMessage extends Toast {

    Context context;

    String message;

    public ToastMessage(Context context, String message) {

        super(context);

        this.context = context;
        this.message = message;
    }

    public void show() {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

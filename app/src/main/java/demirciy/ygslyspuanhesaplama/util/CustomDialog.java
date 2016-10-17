package demirciy.ygslyspuanhesaplama.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;
import demirciy.ygslyspuanhesaplama.model.AllScores;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 13.10.2016
 * Time     : 10:45
 **/
public class CustomDialog extends Dialog implements TextWatcher {

    Context context;

    EditText etExamName;

    String date, examName;

    CustomDialog.From from;

    DatabaseHelper myDb;
    AllScores allScores;

    ToastMessage toast;

    private android.app.AlertDialog b = null;

    public CustomDialog(Context context, From from, AllScores allScores) {

        super(context);

        myDb = new DatabaseHelper(context);

        this.context = context;
        this.from = from;
        this.allScores = allScores;

        toast = new ToastMessage(context);
    }

    public void build() {

        Builder dialogBuilder = new Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        etExamName = (EditText) dialogView.findViewById(R.id.etExamName);

        dialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                examName = etExamName.getText().toString();

                if (!examName.equals("")) {

                    date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());

                    allScores.setExamName(examName);
                    allScores.setExamDate(date);

                    myDb.addAllScore(allScores);

                    toast.show("Ygs puanı kaydedildi");
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.cancel();
            }
        });

        b = dialogBuilder.create();
        b.show();

        etExamName.addTextChangedListener(this);

        checkPositiveButton();
    }

    private boolean isUniqueExamName() {

        boolean isUnique = true;

        ArrayList<String> Headers;
        Headers = myDb.getAllHeadersFromDb2();

        for (int i = 0; i < Headers.size(); i++) {

            if (Headers.get(i).equals(etExamName.getText().toString())) {

                toast.show("Bu ad başka bir sınava ait");

                isUnique = false;
            }
        }

        return isUnique;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        checkPositiveButton();
    }

    private void checkPositiveButton() {

        if (TextUtils.isEmpty(etExamName.getText()) || !isUniqueExamName())
            b.getButton(BUTTON_POSITIVE).setEnabled(false);
        else
            b.getButton(BUTTON_POSITIVE).setEnabled(true);
    }

    public enum From {
        Ygs, Lys
    }
}

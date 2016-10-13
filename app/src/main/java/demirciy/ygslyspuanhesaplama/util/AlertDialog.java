package demirciy.ygslyspuanhesaplama.util;

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
public class AlertDialog extends android.app.AlertDialog implements TextWatcher {

    Context context;

    EditText etExamName;

    String title, message, date, examName;
    boolean isSuccess = false;

    AlertDialog.From from;

    DatabaseHelper myDb;
    AllScores allScores;

    private android.app.AlertDialog b = null;

    public AlertDialog(Context context, String title, String message) {

        super(context);

        myDb = new DatabaseHelper(context);

        this.context = context;
        this.title = title;
        this.message = message;
    }

    public boolean build() {

        allScores = new AllScores();

        date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());

        Builder dialogBuilder = new Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_add_score_name, null);
        dialogBuilder.setView(dialogView);

        etExamName = (EditText) dialogView.findViewById(R.id.etExamName);

        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(message);

        dialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

//                examName = etExamName.getText().toString();
//
//                allScores.setExamName(examName);
//                allScores.setExamDate(date);
//
//                setAllScores(allScores);
//
//                isSuccess = true;
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

        return isSuccess;
    }

    private boolean isUniqueExamName() {

        boolean isUnique = true;

        ArrayList<String> Headers;
        Headers = myDb.getAllHeadersFromDb2();

        for (int i = 0; i < Headers.size(); i++) {

            if (Headers.get(i).equals(examName)) {

                ToastMessage toast = new ToastMessage(context, "Bu ad başka bir sınava ait");
                toast.show();

                isUnique = false;
            }
        }

        return isUnique;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public AllScores getAllScores() {
        return allScores;
    }

    public void setAllScores(AllScores allScores) {
        this.allScores = allScores;
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

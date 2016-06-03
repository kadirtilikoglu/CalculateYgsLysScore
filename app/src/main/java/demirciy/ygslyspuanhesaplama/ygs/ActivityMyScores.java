package demirciy.ygslyspuanhesaplama.ygs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demirciy.ygslyspuanhesaplama.adapter.AdapterExpListView;
import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;

public class ActivityMyScores extends AppCompatActivity {

    ExpandableListView expMyScores;

    ArrayList<String> Headers;
    HashMap<String, List<String>> Datas;

    DatabaseHelper myDb;

    AlertDialog b, a;

    String examName, newExamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        myDb = new DatabaseHelper(this);

        expMyScores = (ExpandableListView) findViewById(R.id.expMyScores);

        Headers = myDb.getAllHeadersFromDb();
        Datas = myDb.getAllDatasFromDb();

        AdapterExpListView adapterExpListView = new AdapterExpListView(this, Headers, Datas);
        expMyScores.setAdapter(adapterExpListView);

        expMyScores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {

                    dialog(position);

                    return true;
                }

                return false;
            }
        });

    }

    public void dialog(final int position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_long_click, null);
        builder.setView(dialogView);

        final TextView delete = (TextView) dialogView.findViewById(R.id.delete);
        delete.setText("Sil");
        final TextView rename = (TextView) dialogView.findViewById(R.id.rename);
        rename.setText("Yeniden Adlandır");

        b = builder.create();
        b.show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteDatas(position);
                finish();
                startActivity(getIntent());
            }
        });

        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog(position);
            }
        });
    }

    public void alertDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_add_score_name, null);
        dialogBuilder.setView(dialogView);

        final EditText etExamName = (EditText) dialogView.findViewById(R.id.etExamName);
        dialogBuilder.setTitle("Yeniden adlandır");
        dialogBuilder.setMessage("Sınav adı giriniz. Örn: Zambak Ygs denemesi");
        dialogBuilder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean isNull = etExamName.getText().toString().equals("");
                if (isNull) {
                    examName = "Adsız";
                    myDb.renameDatas(position, examName);
                } else {
                    examName = etExamName.getText().toString();
                    myDb.renameDatas(position, examName);
                }
                finish();
                startActivity(getIntent());
            }
        });
        dialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        a = dialogBuilder.create();
        a.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, ActivityYgs.class);

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}
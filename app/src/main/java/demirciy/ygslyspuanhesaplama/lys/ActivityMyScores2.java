package demirciy.ygslyspuanhesaplama.lys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.adapter.AdapterExpListView;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;

//activity lys tarafından gelince bu activity açılır
public class ActivityMyScores2 extends AppCompatActivity {

    ExpandableListView expMyScores;

    ArrayList<String> Headers;
    HashMap<String, List<String>> Datas;

    DatabaseHelper myDb;

    AlertDialog b, a;

    String examName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        expMyScores = (ExpandableListView) findViewById(R.id.expMyScores);

        //parent ve child itemlar veri tabanından çekilip arraylist lere atılır
        Headers = myDb.getAllHeadersFromDb();
        Datas = myDb.getAllDatasFromDb();

        //veritabanından çekilen parent ve child item ları adapter e gönderir
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
        final View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        final EditText etExamName = (EditText) dialogView.findViewById(R.id.etExamName);
        dialogBuilder.setTitle("Yeniden adlandır");
        dialogBuilder.setMessage("Sınav adı giriniz. Örn: Zambak Lys denemesi");
        dialogBuilder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean isNull = etExamName.getText().toString().equals("");
                if (isNull) {
                    examName = "Adsız";
                    if (findSameExamName() == 0) {
                        myDb.renameDatas(position, examName);

                        //değişiklikleri göstermek için activity i sonlandırıyor
                        finish();
                        //activity yeniden başlatılıyor
                        startActivity(getIntent());
                    } else {
                        alertDialog(position);
                    }

                } else {
                    examName = etExamName.getText().toString();
                    if (findSameExamName() == 0) {
                        myDb.renameDatas(position, examName);
                        finish();
                        startActivity(getIntent());
                    } else {
                        alertDialog(position);
                    }
                }
            }
        });
        dialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                b.cancel();
            }
        });

        a = dialogBuilder.create();
        a.show();
    }

    private int findSameExamName() {
        int value = 0;

        ArrayList<String> Headers;
        Headers = myDb.getAllHeadersFromDb2();
        for (int i = 0; i < Headers.size(); i++) {
            if (Headers.get(i).equals(examName)) {
                examNameErrorMessage();
                value = 1;
            }
        }
        return value;
    }

    private void examNameErrorMessage() {
        String errorMessage = "Bu isme ait başka bir sınav adı mevcuttur.";
        Toast.makeText(ActivityMyScores2.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
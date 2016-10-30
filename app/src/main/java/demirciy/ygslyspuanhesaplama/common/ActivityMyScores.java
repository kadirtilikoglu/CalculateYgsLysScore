package demirciy.ygslyspuanhesaplama.common;

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

//action bar da denemelerim butonuna basınca açılır
//kaydedilmiş ygs ve lys denemelerini gösterir
public class ActivityMyScores extends AppCompatActivity {

    ExpandableListView expMyScores;

    //expandable listviewdeki başlıklar için
    ArrayList<String> Headers;
    //expandable listviewde başlığın içindeki veriler için
    HashMap<String, List<String>> Datas;

    DatabaseHelper myDb;

    AlertDialog b, a;

    String examName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        expMyScores = (ExpandableListView) findViewById(R.id.expMyScores);

        //parent ve child itemlar veri tabanından çekilip arraylist lere atılır
        Headers = myDb.getAllHeadersFromDb();
        Datas = myDb.getAllDatasFromDb();

        //veritabanından çekilen parent ve child item ları adapter e gönderir
        AdapterExpListView adapterExpListView = new AdapterExpListView(this, Headers, Datas);
        expMyScores.setAdapter(adapterExpListView);

        //bir expandable listview başlığına(deneme adı ve tarihi) uzun tıklanınca çalışır
        expMyScores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {

                    //position: tıklanılan başlığın sırasıdır
                    //kayıtlı denemeyi silme veya yeniden adlandırma işlemi bu position sayısına göre yapılacak
                    dialog(position);

                    return true;
                }

                return false;
            }
        });
    }

    //deneme adına uzun tıklanınca bir alertdialog açar
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
                //silinmek istenen denemenin position numasarı veritabanı class ına gönderilir
                //tablodaki silme işlemi de ona göre yapılır
                myDb.deleteDatas(position);
                finish();
                startActivity(getIntent());
            }
        });

        //yeniden adlandırma yapılacaksa yeni bir alertdialog açılır
        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog(position);
            }
        });
    }

    //yeni deneme adı için bu alertdialog açılır
    public void alertDialog(final int position) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        final EditText etExamName = (EditText) dialogView.findViewById(R.id.etExamName);
        dialogBuilder.setTitle("Yeniden adlandır");
        dialogBuilder.setMessage("Sınav adı giriniz. Örn: Zambak Ygs denemesi");
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
        Toast.makeText(ActivityMyScores.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
package demirciy.ygslyspuanhesaplama;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etYgsTrD, etYgsTrY, etYgsTrN, etYgsSosD, etYgsSosY,
            etYgsSosN, etYgsMatD, etYgsMatY, etYgsMatN, etYgsFenD,
            etYgsFenY, etYgsFenN;
    private TextView tToplamD, tToplamY, tToplamN, tYgs1, tYgs2,
            tYgs3, tYgs4, tYgs5, tYgs6;
    private Button btnHesapla, btnLys, btnTemizle;

    private double ygsTrD, ygsTrY, ygsSosD, ygsSosY, ygsMatD,
            ygsMatY, ygsFenD, ygsFenY;
    public double ygsTrN, ygsSosN, ygsMatN, ygsFenN;

    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "Hata bildirisi...";

    DatabaseHelper myDb;

    //edittextte 3 haneli sayı olunca edittext büyüyor
    //başlagıçta ekrana bir bildiri gönder ve sayısal yeşil, eşit ağırlık mor, sözel açık sarı yazdır
    //multi screen support
    //ctrl+alt+l
    //ctrl+alt+o

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "Uygulama çalışmaya başladı.");

        etYgsTrD = (EditText) findViewById(R.id.etYgsTrD);
        etYgsTrY = (EditText) findViewById(R.id.etYgsTrY);
        etYgsTrN = (EditText) findViewById(R.id.etYgsTrN);
        etYgsSosD = (EditText) findViewById(R.id.etYgsSosD);
        etYgsSosY = (EditText) findViewById(R.id.etYgsSosY);
        etYgsSosN = (EditText) findViewById(R.id.etYgsSosN);
        etYgsMatD = (EditText) findViewById(R.id.etYgsMatD);
        etYgsMatY = (EditText) findViewById(R.id.etYgsMatY);
        etYgsMatN = (EditText) findViewById(R.id.etYgsMatN);
        etYgsFenD = (EditText) findViewById(R.id.etYgsFenD);
        etYgsFenY = (EditText) findViewById(R.id.etYgsFenY);
        etYgsFenN = (EditText) findViewById(R.id.etYgsFenN);
        tToplamD = (TextView) findViewById(R.id.tToplamD);
        tToplamY = (TextView) findViewById(R.id.tToplamY);
        tToplamN = (TextView) findViewById(R.id.tToplamN);
        tYgs1 = (TextView) findViewById(R.id.tYgs1);
        tYgs2 = (TextView) findViewById(R.id.tYgs2);
        tYgs3 = (TextView) findViewById(R.id.tYgs3);
        tYgs4 = (TextView) findViewById(R.id.tYgs4);
        tYgs5 = (TextView) findViewById(R.id.tYgs5);
        tYgs6 = (TextView) findViewById(R.id.tYgs6);
        btnHesapla = (Button) findViewById(R.id.btnHesapla);
        btnLys = (Button) findViewById(R.id.btnLys);
        btnTemizle = (Button) findViewById(R.id.btnTemizle);

        btnHesapla.setOnClickListener(this);
        btnLys.setOnClickListener(this);
        btnTemizle.setOnClickListener(this);

        myDb = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHesapla:
                ygsHesapla();
                break;
            case R.id.btnLys:
                gitLys();
                break;
            case R.id.btnTemizle:
                ygsTemizle();
                break;
            default:
                break;
        }
    }

    public void ygsHesapla() {
        Log.d(LOG_TAG, "Ygs notları alınıyor.");

        try {
            boolean trD = etYgsTrD.getText().toString().equals("");
            boolean trY = etYgsTrY.getText().toString().equals("");
            boolean trN = etYgsTrN.getText().toString().equals("");
            if (trD == false && trY == true && trN == true)
            {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                ygsTrN = ygsTrD;
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else if (trD == true && trY == false && trN == true)
            {
                NetHesapla ygsTr = new NetHesapla(0, Double.parseDouble(etYgsTrY.getText().toString()));
                ygsTrN = ygsTr.getNet();
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else if(trD == true && trY == true && trN == false)
            {
                ygsTrN = Double.parseDouble(etYgsTrN.getText().toString());
            }
            else if(trD == false && trY == false && trN == true)
            {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                ygsTrN = ygsTr.getNet();
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else if(trD == false && trY == true && trN == false)
            {
                ygsTrN = Double.parseDouble(etYgsTrD.getText().toString());
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else if (trD == true && trY == false && trN == false)
            {
                NetHesapla ygsTr = new NetHesapla(0, Double.parseDouble(etYgsTrY.getText().toString()));
                ygsTrN = ygsTr.getNet();
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else if(trD == false && trY == false && trN == false)
            {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                ygsTrN = ygsTr.getNet();
                etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
            }
            else
                return;


            boolean sosD = etYgsSosD.getText().toString().equals("");
            boolean sosY = etYgsSosY.getText().toString().equals("");
            boolean sosN = etYgsSosN.getText().toString().equals("");
            if (sosD == false && sosY == true && sosN == true)
            {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                ygsSosN = ygsSosD;
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else if (sosD == true && sosY == false && sosN == true)
            {
                NetHesapla ygsSos = new NetHesapla(0, Double.parseDouble(etYgsSosY.getText().toString()));
                ygsSosN = ygsSos.getNet();
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else if(sosD == true && sosY == true && sosN == false)
            {
                ygsSosN = Double.parseDouble(etYgsSosN.getText().toString());
            }
            else if(sosD == false && sosY == false && sosN == true)
            {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                ygsSosN = ygsSos.getNet();
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else if(sosD == false && sosY == true && sosN == false)
            {
                ygsSosN = Double.parseDouble(etYgsSosD.getText().toString());
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else if (sosD == true && sosY == false && sosN == false)
            {
                NetHesapla ygsSos = new NetHesapla(0, Double.parseDouble(etYgsSosY.getText().toString()));
                ygsSosN = ygsSos.getNet();
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else if(sosD == false && sosY == false && sosN == false)
            {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                ygsSosN = ygsSos.getNet();
                etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
            }
            else
                return;

            boolean matD = etYgsMatD.getText().toString().equals("");
            boolean matY = etYgsMatY.getText().toString().equals("");
            boolean matN = etYgsMatN.getText().toString().equals("");
            if (matD == false && matY == true && matN == true)
            {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                ygsMatN = ygsMatD;
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else if (matD == true && matY == false && matN == true)
            {
                NetHesapla ygsMat = new NetHesapla(0, Double.parseDouble(etYgsMatY.getText().toString()));
                ygsMatN = ygsMat.getNet();
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else if(matD == true && matY == true && matN == false)
            {
                ygsMatN = Double.parseDouble(etYgsMatN.getText().toString());
            }
            else if(matD == false && matY == false && matN == true)
            {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                ygsMatN = ygsMat.getNet();
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else if(matD == false && matY == true && matN == false)
            {
                ygsMatN = Double.parseDouble(etYgsMatD.getText().toString());
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else if (matD == true && matY == false && matN == false)
            {
                NetHesapla ygsMat = new NetHesapla(0, Double.parseDouble(etYgsMatY.getText().toString()));
                ygsMatN = ygsMat.getNet();
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else if(matD == false && matY == false && matN == false)
            {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                ygsMatN = ygsMat.getNet();
                etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
            }
            else
                return;

            boolean fenD = etYgsFenD.getText().toString().equals("");
            boolean fenY = etYgsFenY.getText().toString().equals("");
            boolean fenN = etYgsFenN.getText().toString().equals("");
            if (fenD == false && fenY == true && fenN == true)
            {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                ygsFenN = ygsFenD;
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else if (fenD == true && fenY == false && fenN == true)
            {
                NetHesapla ygsFen = new NetHesapla(0, Double.parseDouble(etYgsFenY.getText().toString()));
                ygsFenN = ygsFen.getNet();
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else if(fenD == true && fenY == true && fenN == false)
            {
                ygsFenN = Double.parseDouble(etYgsFenN.getText().toString());
            }
            else if(fenD == false && fenY == false && fenN == true)
            {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                ygsFenN = ygsFen.getNet();
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else if(fenD == false && fenY == true && fenN == false)
            {
                ygsFenN = Double.parseDouble(etYgsFenD.getText().toString());
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else if (fenD == true && fenY == false && fenN == false)
            {
                NetHesapla ygsFen = new NetHesapla(0, Double.parseDouble(etYgsFenY.getText().toString()));
                ygsFenN = ygsFen.getNet();
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else if(fenD == false && fenY == false && fenN == false)
            {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                ygsFenN = ygsFen.getNet();
                etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
            }
            else
                return;

            tToplamD.setText(String.valueOf(format.format(ygsTrD + ygsSosD + ygsMatD + ygsFenD)));
            tToplamY.setText(String.valueOf(format.format(ygsTrY + ygsSosY + ygsMatY + ygsFenY)));
            tToplamN.setText(String.valueOf(format.format(ygsTrN + ygsSosN + ygsMatN + ygsFenN)));

            ygsPuanGoster();

            ygsVeritabaniNetEkle();

            Log.d(LOG_TAG, "Ygs notları alınıp puanlar hesaplandı.");
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());

            String hata_mesaji = "Alanları boş bırakmayın.";
            Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
        }
    }

    public void ygsTemizle() {
        Log.d(LOG_TAG, "Temizleme butonuna basıldı.");
        try {
            etYgsTrD.setText("");
            etYgsTrY.setText("");
            etYgsTrN.setText("");
            etYgsSosD.setText("");
            etYgsSosY.setText("");
            etYgsSosN.setText("");
            etYgsMatD.setText("");
            etYgsMatY.setText("");
            etYgsMatN.setText("");
            etYgsFenD.setText("");
            etYgsFenY.setText("");
            etYgsFenN.setText("");
            tToplamD.setText("");
            tToplamY.setText("");
            tToplamN.setText("");
            tYgs1.setText("Ygs-1 : 100.16");
            tYgs2.setText("Ygs-2 : 100.16");
            tYgs3.setText("Ygs-3 : 100.16");
            tYgs4.setText("Ygs-4 : 100.16");
            tYgs5.setText("Ygs-5 : 100.12");
            tYgs6.setText("Ygs-6 : 100.12");
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        Log.d(LOG_TAG, "Temizleme işlemi bitti.");
    }

    public void gitLys() {
        Intent i = new Intent(MainActivity.this, Lys.class);
        startActivity(i);
    }

    public void ygsPuanGoster() {
        YgsPuanTuruHesaplama ygs1 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs1.setText(String.format("Ygs-1 : %.2f", ygs1.getYgs1()));
        YgsPuanTuruHesaplama ygs2 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs2.setText(String.format("Ygs-2 : %.2f", ygs2.getYgs2()));
        YgsPuanTuruHesaplama ygs3 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs3.setText(String.format("Ygs-3 : %.2f", ygs3.getYgs3()));
        YgsPuanTuruHesaplama ygs4 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs4.setText(String.format("Ygs-4 : %.2f", ygs4.getYgs4()));
        YgsPuanTuruHesaplama ygs5 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs5.setText(String.format("Ygs-5 : %.2f", ygs5.getYgs5()));
        YgsPuanTuruHesaplama ygs6 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        tYgs6.setText(String.format("Ygs-6 : %.2f", ygs6.getYgs6()));
    }

    public void ygsVeritabaniNetEkle() {
        Cursor res = myDb.ygsNetleriAl();
        if (res.getCount() == 0) {
            boolean tr = myDb.ygsNetEkle("Türkçe", String.valueOf(ygsTrN));
            boolean sosyal = myDb.ygsNetEkle("Sosyal", String.valueOf(ygsSosN));
            boolean mat = myDb.ygsNetEkle("Matematik", String.valueOf(ygsMatN));
            boolean fen = myDb.ygsNetEkle("Fen", String.valueOf(ygsFenN));
            if (tr == true && sosyal == true && mat == true && fen == true)
                Toast.makeText(MainActivity.this, "Ygs netleri veri tabanına eklendi", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Ygs netleri veri tabanına eklenemedi.", Toast.LENGTH_LONG).show();
        } else {
            boolean tr = myDb.ygsNetGuncelle("Türkçe", String.valueOf(ygsTrN));
            boolean sosyal = myDb.ygsNetGuncelle("Sosyal", String.valueOf(ygsSosN));
            boolean mat = myDb.ygsNetGuncelle("Matematik", String.valueOf(ygsMatN));
            boolean fen = myDb.ygsNetGuncelle("Fen", String.valueOf(ygsFenN));

            if (tr == true && sosyal == true && mat == true && fen == true)
                Toast.makeText(MainActivity.this, "veri tabanı güncellendi", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "veri tabanı güncellenemedi", Toast.LENGTH_LONG).show();
        }
    }

}

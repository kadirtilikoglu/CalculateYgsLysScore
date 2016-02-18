package demirciy.ygslyspuanhesaplama;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText et_ygs_tr_dogru, et_ygs_tr_yanlis, et_ygs_tr_net, et_ygs_sosyal_dogru, et_ygs_sosyal_yanlis,
    et_ygs_sosyal_net, et_ygs_mat_dogru, et_ygs_mat_yanlis, et_ygs_mat_net, et_ygs_fen_dogru,
    et_ygs_fen_yanlis, et_ygs_fen_net;
    private TextView text_ygs_toplam_dogru, text_ygs_toplam_yanlis, text_ygs_toplam_net, text_ygs1, text_ygs2,
    text_ygs3, text_ygs4, text_ygs5, text_ygs6;

    private double ygs_tr_dogru, ygs_tr_yanlis, ygs_sosyal_dogru, ygs_sosyal_yanlis, ygs_mat_dogru,
    ygs_mat_yanlis, ygs_fen_dogru, ygs_fen_yanlis;
    public double ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net;

    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "Hata bildirisi...";

    DatabaseHelper myDb;

    //başlagıçta ekrana bir bildiri gönder ve sayısal yeşil, eşit ağırlık mor, sözel açık sarı yazdır
    //multi screen support

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "Uygulama çalışmaya başladı.");

        et_ygs_tr_dogru        = (EditText) findViewById(R.id.et_ygs_tr_dogru);
        et_ygs_tr_yanlis       = (EditText) findViewById(R.id.et_ygs_tr_yanlis);
        et_ygs_tr_net          = (EditText) findViewById(R.id.et_ygs_tr_net);
        et_ygs_sosyal_dogru    = (EditText) findViewById(R.id.et_ygs_sosyal_dogru);
        et_ygs_sosyal_yanlis   = (EditText) findViewById(R.id.et_ygs_sosyal_yanlis);
        et_ygs_sosyal_net      = (EditText) findViewById(R.id.et_ygs_sosyal_net);
        et_ygs_mat_dogru       = (EditText) findViewById(R.id.et_ygs_mat_dogru);
        et_ygs_mat_yanlis      = (EditText) findViewById(R.id.et_ygs_mat_yanlis);
        et_ygs_mat_net         = (EditText) findViewById(R.id.et_ygs_mat_net);
        et_ygs_fen_dogru       = (EditText) findViewById(R.id.et_ygs_fen_dogru);
        et_ygs_fen_yanlis      = (EditText) findViewById(R.id.et_ygs_fen_yanlis);
        et_ygs_fen_net         = (EditText) findViewById(R.id.et_ygs_fen_net);
        text_ygs_toplam_dogru  = (TextView) findViewById(R.id.text_ygs_toplam_dogru);
        text_ygs_toplam_yanlis = (TextView) findViewById(R.id.text_ygs_toplam_yanlis);
        text_ygs_toplam_net    = (TextView) findViewById(R.id.text_ygs_toplam_net);
        text_ygs1              = (TextView) findViewById(R.id.text_ygs1);
        text_ygs2              = (TextView) findViewById(R.id.text_ygs2);
        text_ygs3              = (TextView) findViewById(R.id.text_ygs3);
        text_ygs4              = (TextView) findViewById(R.id.text_ygs4);
        text_ygs5              = (TextView) findViewById(R.id.text_ygs5);
        text_ygs6              = (TextView) findViewById(R.id.text_ygs6);

        myDb = new DatabaseHelper(this);
    }

    public void YgsHesapla(View view)
    {
        Log.d(LOG_TAG, "Ygs notları alınıyor.");
        try {
            ygs_tr_dogru = Double.parseDouble(et_ygs_tr_dogru.getText().toString());
            ygs_tr_yanlis = Double.parseDouble(et_ygs_tr_yanlis.getText().toString());
            ygs_tr_net = Double.parseDouble(et_ygs_tr_net.getText().toString());
            if (ygs_tr_net == 0) {
                NetHesapla ygs_tr = new NetHesapla(ygs_tr_dogru, ygs_tr_yanlis);
                ygs_tr_net = ygs_tr.getNet();
                et_ygs_tr_net.setText(String.valueOf(format.format(ygs_tr_net)));
            } else {
                if (ygs_tr_dogru == 0 && ygs_tr_yanlis == 0) {
                    et_ygs_tr_net.setText(String.valueOf(format.format(ygs_tr_net)));
                } else {
                    NetHesapla ygs_tr = new NetHesapla(ygs_tr_dogru, ygs_tr_yanlis);
                    ygs_tr_net = ygs_tr.getNet();
                    et_ygs_tr_net.setText(String.valueOf(format.format(ygs_tr_net)));
                }
            }

            ygs_sosyal_dogru = Double.parseDouble(et_ygs_sosyal_dogru.getText().toString());
            ygs_sosyal_yanlis = Double.parseDouble(et_ygs_sosyal_yanlis.getText().toString());
            ygs_sosyal_net = Double.parseDouble(et_ygs_sosyal_net.getText().toString());
            if (ygs_sosyal_net == 0) {
                NetHesapla ygs_sosyal = new NetHesapla(ygs_sosyal_dogru, ygs_sosyal_yanlis);
                ygs_sosyal_net = ygs_sosyal.getNet();
                et_ygs_sosyal_net.setText(String.valueOf(format.format(ygs_sosyal_net)));
            } else {
                if (ygs_sosyal_dogru == 0 && ygs_sosyal_yanlis == 0) {
                    et_ygs_sosyal_net.setText(String.valueOf(format.format(ygs_sosyal_net)));
                } else {
                    NetHesapla ygs_sosyal = new NetHesapla(ygs_sosyal_dogru, ygs_sosyal_yanlis);
                    ygs_sosyal_net = ygs_sosyal.getNet();
                    et_ygs_sosyal_net.setText(String.valueOf(format.format(ygs_sosyal_net)));
                }
            }

            ygs_mat_dogru = Double.parseDouble(et_ygs_mat_dogru.getText().toString());
            ygs_mat_yanlis = Double.parseDouble(et_ygs_mat_yanlis.getText().toString());
            ygs_mat_net = Double.parseDouble(et_ygs_mat_net.getText().toString());
            if (ygs_mat_net == 0) {
                NetHesapla ygs_mat = new NetHesapla(ygs_mat_dogru, ygs_mat_yanlis);
                ygs_mat_net = ygs_mat.getNet();
                et_ygs_mat_net.setText(String.valueOf(format.format(ygs_mat_net)));
            } else {
                if (ygs_mat_dogru == 0 && ygs_mat_yanlis == 0) {
                    et_ygs_mat_net.setText(String.valueOf(format.format(ygs_mat_net)));
                } else {
                    NetHesapla ygs_mat = new NetHesapla(ygs_mat_dogru, ygs_mat_yanlis);
                    ygs_mat_net = ygs_mat.getNet();
                    et_ygs_mat_net.setText(String.valueOf(format.format(ygs_mat_net)));
                }
            }

            ygs_fen_dogru = Double.parseDouble(et_ygs_fen_dogru.getText().toString());
            ygs_fen_yanlis = Double.parseDouble(et_ygs_fen_yanlis.getText().toString());
            ygs_fen_net = Double.parseDouble(et_ygs_fen_net.getText().toString());
            if (ygs_fen_net == 0) {
                NetHesapla ygs_fen = new NetHesapla(ygs_fen_dogru, ygs_fen_yanlis);
                ygs_fen_net = ygs_fen.getNet();
                et_ygs_fen_net.setText(String.valueOf(format.format(ygs_fen_net)));
            } else {
                if (ygs_fen_dogru == 0 && ygs_fen_yanlis == 0) {
                    et_ygs_fen_net.setText(String.valueOf(format.format(ygs_fen_net)));
                } else {
                    NetHesapla ygs_fen = new NetHesapla(ygs_fen_dogru, ygs_fen_yanlis);
                    ygs_fen_net = ygs_fen.getNet();
                    et_ygs_fen_net.setText(String.valueOf(format.format(ygs_fen_net)));
                }
            }

            text_ygs_toplam_dogru.setText(String.valueOf(format.format(ygs_tr_dogru + ygs_sosyal_dogru + ygs_mat_dogru + ygs_fen_dogru)));
            text_ygs_toplam_yanlis.setText(String.valueOf(format.format(ygs_tr_yanlis + ygs_sosyal_yanlis + ygs_mat_yanlis + ygs_fen_yanlis)));
            text_ygs_toplam_net.setText(String.valueOf(format.format(ygs_tr_net + ygs_sosyal_net + ygs_mat_net + ygs_fen_net)));

            ygsPuanGoster();

            ygsVeritabaniNetEkle();

            Log.d(LOG_TAG, "Ygs notları alınıp puanlar hesaplandı.");
        }
        catch (Exception e)
        {
            Log.e(LOG_TAG, e.getMessage());

            String hata_mesaji = "Alanları boş bırakmayın.";
            Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
        }
    }
    public void YgsTemizle(View view)
    {
        Log.d(LOG_TAG, "Temizleme butonuna basıldı.");
        try
        {
            et_ygs_tr_dogru.setText("0");
            et_ygs_tr_yanlis.setText("0");
            et_ygs_tr_net.setText("0");
            et_ygs_sosyal_dogru.setText("0");
            et_ygs_sosyal_yanlis.setText("0");
            et_ygs_sosyal_net.setText("0");
            et_ygs_mat_dogru.setText("0");
            et_ygs_mat_yanlis.setText("0");
            et_ygs_mat_net.setText("0");
            et_ygs_fen_dogru.setText("0");
            et_ygs_fen_yanlis.setText("0");
            et_ygs_fen_net.setText("0");
            text_ygs_toplam_dogru.setText("0");
            text_ygs_toplam_yanlis.setText("0");
            text_ygs_toplam_net.setText("0");
            text_ygs1.setText("Ygs-1 : 100.16");
            text_ygs2.setText("Ygs-2 : 100.16");
            text_ygs3.setText("Ygs-3 : 100.16");
            text_ygs4.setText("Ygs-4 : 100.16");
            text_ygs5.setText("Ygs-5 : 100.12");
            text_ygs6.setText("Ygs-6 : 100.12");
        }
        catch(Exception e)
        {
            Log.e(LOG_TAG, e.getMessage());
        }

        Log.d(LOG_TAG, "Temizleme işlemi bitti.");
    }

    public void GitLys(View view)
    {
        Intent i = new Intent(MainActivity.this, Lys.class);
        startActivity(i);
    }

    public void ygsPuanGoster()
    {
        YgsPuanTuruHesaplama ygs1 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs1.setText(String.format("Ygs-1 : %.2f", ygs1.getYgs1()));
        YgsPuanTuruHesaplama ygs2 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs2.setText(String.format("Ygs-2 : %.2f", ygs2.getYgs2()));
        YgsPuanTuruHesaplama ygs3 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs3.setText(String.format("Ygs-3 : %.2f", ygs3.getYgs3()));
        YgsPuanTuruHesaplama ygs4 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs4.setText(String.format("Ygs-4 : %.2f", ygs4.getYgs4()));
        YgsPuanTuruHesaplama ygs5 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs5.setText(String.format("Ygs-5 : %.2f", ygs5.getYgs5()));
        YgsPuanTuruHesaplama ygs6 = new YgsPuanTuruHesaplama(ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net);
        text_ygs6.setText(String.format("Ygs-6 : %.2f", ygs6.getYgs6()));
    }

    public void ygsVeritabaniNetEkle()
    {
        Cursor res = myDb.ygsNetleriAl();
        if(res.getCount() == 0)
        {
            boolean tr = myDb.ygsNetEkle("Türkçe", String.valueOf(ygs_tr_net));
            boolean sosyal = myDb.ygsNetEkle("Sosyal", String.valueOf(ygs_sosyal_net));
            boolean mat = myDb.ygsNetEkle("Matematik", String.valueOf(ygs_mat_net));
            boolean fen = myDb.ygsNetEkle("Fen", String.valueOf(ygs_fen_net));
            if(tr == true && sosyal == true && mat == true && fen == true)
                Toast.makeText(MainActivity.this, "Ygs netleri veri tabanına eklendi", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Ygs netleri veri tabanına eklenemedi.", Toast.LENGTH_LONG).show();
        }
        else
        {
            boolean tr = myDb.ygsNetGuncelle("Türkçe", String.valueOf(ygs_tr_net));
            boolean sosyal = myDb.ygsNetGuncelle("Sosyal", String.valueOf(ygs_sosyal_net));
            boolean mat = myDb.ygsNetGuncelle("Matematik", String.valueOf(ygs_mat_net));
            boolean fen = myDb.ygsNetGuncelle("Fen", String.valueOf(ygs_fen_net));

            if(tr == true && sosyal == true && mat == true && fen == true)
                Toast.makeText(MainActivity.this, "veri tabanı güncellendi", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "veri tabanı güncellenemedi", Toast.LENGTH_LONG).show();
        }
    }
}

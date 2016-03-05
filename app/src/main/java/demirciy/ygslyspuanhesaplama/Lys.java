package demirciy.ygslyspuanhesaplama;

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

public class Lys extends AppCompatActivity implements View.OnClickListener {

    private EditText etLysMatD, etLysMatY, etLysMatN, etLysGeoD, etLysGeoY,
            etLysGeoN, etLysFizikD, etLysFizikY, etLysFizikN, etLysKimyaD,
            etLysKimyaY, etLysKimyaN, etLysBiyoD, etLysBiyoY, etLysBiyoN,
            etLysEdeD, etLysEdeY, etLysEdeN, etLysCog1D, etLysCog1Y, etLysCog1N,
            etLysTarihD, etLysTarihY, etLysTarihN, etLysCog2D, etLysCog2Y, etLysCog2N,
            etLysFelD, etLysFelY, etLysFelN, etLysYdilD, etLysYdilY, etLysYdilN, etDiploma;
    private TextView tMf1, tMf2, tMf3, tMf4, tTm1, tTm2,
            tTm3, tTs1, tTs2, tYdil1, tYdil2, tYdil3;

    private double lysMatN, lysGeoN, lysFizikN, lysKimyaN, lysBiyoN, lysEdeN, lysCog1N, lysTarihN,
            lysCog2N, lysFelN, lysYdilN, OBP;

    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "Hata bildirisi...";

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lys);

        etLysMatD = (EditText) findViewById(R.id.etLysMatD);
        etLysMatY = (EditText) findViewById(R.id.etLysMatY);
        etLysMatN = (EditText) findViewById(R.id.etLysMatN);
        etLysGeoD = (EditText) findViewById(R.id.etLysGeoD);
        etLysGeoY = (EditText) findViewById(R.id.etLysGeoY);
        etLysGeoN = (EditText) findViewById(R.id.etLysGeoN);
        etLysFizikD = (EditText) findViewById(R.id.etLysFizikD);
        etLysFizikY = (EditText) findViewById(R.id.etLysFizikY);
        etLysFizikN = (EditText) findViewById(R.id.etLysFizikN);
        etLysKimyaD = (EditText) findViewById(R.id.etLysKimyaD);
        etLysKimyaY = (EditText) findViewById(R.id.etLysKimyaY);
        etLysKimyaN = (EditText) findViewById(R.id.etLysKimyaN);
        etLysBiyoD = (EditText) findViewById(R.id.etLysBiyoD);
        etLysBiyoY = (EditText) findViewById(R.id.etLysBiyoY);
        etLysBiyoN = (EditText) findViewById(R.id.etLysBiyoN);
        etLysEdeD = (EditText) findViewById(R.id.etLysEdeD);
        etLysEdeY = (EditText) findViewById(R.id.etLysEdeY);
        etLysEdeN = (EditText) findViewById(R.id.etLysEdeN);
        etLysCog1D = (EditText) findViewById(R.id.etLysCog1D);
        etLysCog1Y = (EditText) findViewById(R.id.etLysCog1Y);
        etLysCog1N = (EditText) findViewById(R.id.etLysCog1N);
        etLysTarihD = (EditText) findViewById(R.id.etLysTarihD);
        etLysTarihY = (EditText) findViewById(R.id.etLysTarihY);
        etLysTarihN = (EditText) findViewById(R.id.etLysTarihN);
        etLysCog2D = (EditText) findViewById(R.id.etLysCog2D);
        etLysCog2Y = (EditText) findViewById(R.id.etLysCog2Y);
        etLysCog2N = (EditText) findViewById(R.id.etLysCog2N);
        etLysFelD = (EditText) findViewById(R.id.etLysFelD);
        etLysFelY = (EditText) findViewById(R.id.etLysFelY);
        etLysFelN = (EditText) findViewById(R.id.etLysFelN);
        etLysYdilD = (EditText) findViewById(R.id.etLysYdilD);
        etLysYdilY = (EditText) findViewById(R.id.etLysYdilY);
        etLysYdilN = (EditText) findViewById(R.id.etLysYdilN);
        etDiploma = (EditText) findViewById(R.id.etDiploma);
        Button btnHesapla = (Button) findViewById(R.id.btnHesapla);
        Button btnTemizle = (Button) findViewById(R.id.btnTemizle);
        tMf1 = (TextView) findViewById(R.id.tMf1);
        tMf2 = (TextView) findViewById(R.id.tMf2);
        tMf3 = (TextView) findViewById(R.id.tMf3);
        tMf4 = (TextView) findViewById(R.id.tMf4);
        tTm1 = (TextView) findViewById(R.id.tTm1);
        tTm2 = (TextView) findViewById(R.id.tTm2);
        tTm3 = (TextView) findViewById(R.id.tTm3);
        tTs1 = (TextView) findViewById(R.id.tTs1);
        tTs2 = (TextView) findViewById(R.id.tTs2);
        tYdil1 = (TextView) findViewById(R.id.tYdil1);
        tYdil2 = (TextView) findViewById(R.id.tYdil2);
        tYdil3 = (TextView) findViewById(R.id.tYdil3);

        btnHesapla.setOnClickListener(this);
        btnTemizle.setOnClickListener(this);

        myDb = new DatabaseHelper(this);

        lysPuanHesapIslemleri(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHesapla:
                yerlestirmePuanHesapla();
                break;
            case R.id.btnTemizle:
                lysTemizle();
                break;
            default:
                break;
        }
    }

    public void yerlestirmePuanHesapla() {
        Log.d(LOG_TAG, "Lys notları alınıyor.");
        try {
            boolean matD = etLysMatD.getText().toString().equals("");
            boolean matY = etLysMatY.getText().toString().equals("");
            boolean matN = etLysMatN.getText().toString().equals("");
            double lysMatD, lysMatY, lysGeoD, lysGeoY, lysFizikD, lysFizikY, lysKimyaD, lysKimyaY,
                    lysBiyoD, lysBiyoY, lysEdeD, lysEdeY, lysCog1D, lysCog1Y, lysTarihD, lysTarihY, lysCog2D, lysCog2Y, lysFelD, lysFelY, lysYdilD, lysYdilY;

            if (!matD && matY && matN) {
                lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                if (lysMatD > 50) {
                    etLysMatD.setText("");
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysMatN = lysMatD;
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }

            } else if (matD && !matY && matN) {
                lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                if (lysMatY > 50) {
                    etLysMatY.setText("");
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysMat = new NetHesapla(0, lysMatY);
                    lysMatN = lysMat.getNet();
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }
            } else if (matD && matY && !matN) {
                lysMatN = Double.parseDouble(etLysMatN.getText().toString());
                if (lysMatN > 50) {
                    etLysMatN.setText("");
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!matD && !matY && matN) {
                lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                if (lysMatD + lysMatY > 50) {
                    etLysMatD.setText("");
                    etLysMatY.setText("");
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysMat = new NetHesapla(lysMatD, lysMatY);
                    lysMatN = lysMat.getNet();
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }

            } else if (!matD && matY) {
                lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                if (lysMatD > 50) {
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysMatN = lysMatD;
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }

            } else if (matD && !matY) {
                lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                if (lysMatY > 50) {
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysMat = new NetHesapla(0, lysMatY);
                    lysMatN = lysMat.getNet();
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }

            } else if (!matD) {
                lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                if (lysMatD + lysMatY > 50) {
                    String hata_mesaji = "Matematik testinde soru sayısı 50'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysMat = new NetHesapla(lysMatD, lysMatY);
                    lysMatN = lysMat.getNet();
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }

            } else
                lysMatN = 0;

            boolean geoD = etLysGeoD.getText().toString().equals("");
            boolean geoY = etLysGeoY.getText().toString().equals("");
            boolean geoN = etLysGeoN.getText().toString().equals("");
            if (!geoD && geoY && geoN) {
                lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                if (lysGeoD > 30) {
                    etLysGeoD.setText("");
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysGeoN = lysGeoD;
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }

            } else if (geoD && !geoY && geoN) {
                lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                if (lysGeoY > 30) {
                    etLysGeoY.setText("");
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysGeo = new NetHesapla(0, lysGeoY);
                    lysGeoN = lysGeo.getNet();
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }
            } else if (geoD && geoY && !geoN) {
                lysGeoN = Double.parseDouble(etLysGeoN.getText().toString());
                if (lysGeoN > 30) {
                    etLysGeoN.setText("");
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!geoD && !geoY && geoN) {
                lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                if (lysGeoD + lysGeoY > 30) {
                    etLysGeoD.setText("");
                    etLysGeoY.setText("");
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysGeo = new NetHesapla(lysGeoD, lysGeoY);
                    lysGeoN = lysGeo.getNet();
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }

            } else if (!geoD && geoY) {
                lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                if (lysGeoD > 30) {
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysGeoN = lysGeoD;
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }

            } else if (geoD && !geoY) {
                lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                if (lysGeoY > 30) {
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysGeo = new NetHesapla(0, lysGeoY);
                    lysGeoN = lysGeo.getNet();
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }

            } else if (!geoD) {
                lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                if (lysGeoD + lysGeoY > 30) {
                    String hata_mesaji = "Geometri testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysGeo = new NetHesapla(lysGeoD, lysGeoY);
                    lysGeoN = lysGeo.getNet();
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }

            } else
                lysGeoN = 0;

            boolean fizikD = etLysFizikD.getText().toString().equals("");
            boolean fizikY = etLysFizikY.getText().toString().equals("");
            boolean fizikN = etLysFizikN.getText().toString().equals("");
            if (!fizikD && fizikY && fizikN) {
                lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                if (lysFizikD > 30) {
                    etLysFizikD.setText("");
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysFizikN = lysFizikD;
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }

            } else if (fizikD && !fizikY && fizikN) {
                lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                if (lysFizikY > 30) {
                    etLysFizikY.setText("");
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFizik = new NetHesapla(0, lysFizikY);
                    lysFizikN = lysFizik.getNet();
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }
            } else if (fizikD && fizikY && !fizikN) {
                lysFizikN = Double.parseDouble(etLysFizikN.getText().toString());
                if (lysFizikN > 30) {
                    etLysFizikN.setText("");
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!fizikD && !fizikY && fizikN) {
                lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                if (lysFizikD + lysFizikY > 30) {
                    etLysFizikD.setText("");
                    etLysFizikY.setText("");
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFizik = new NetHesapla(lysFizikD, lysFizikY);
                    lysFizikN = lysFizik.getNet();
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }

            } else if (!fizikD && fizikY) {
                lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                if (lysFizikD > 30) {
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysFizikN = lysFizikD;
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }

            } else if (fizikD && !fizikY) {
                lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                if (lysFizikY > 30) {
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFizik = new NetHesapla(0, lysFizikY);
                    lysFizikN = lysFizik.getNet();
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }

            } else if (!fizikD) {
                lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                if (lysFizikD + lysFizikY > 30) {
                    String hata_mesaji = "Fizik testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFizik = new NetHesapla(lysFizikD, lysFizikY);
                    lysFizikN = lysFizik.getNet();
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }

            } else
                lysFizikN = 0;

            boolean kimyaD = etLysKimyaD.getText().toString().equals("");
            boolean kimyaY = etLysKimyaY.getText().toString().equals("");
            boolean kimyaN = etLysKimyaN.getText().toString().equals("");
            if (!kimyaD && kimyaY && kimyaN) {
                lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                if (lysKimyaD > 30) {
                    etLysKimyaD.setText("");
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysKimyaN = lysKimyaD;
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }

            } else if (kimyaD && !kimyaY && kimyaN) {
                lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                if (lysKimyaY > 30) {
                    etLysKimyaY.setText("");
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysKimya = new NetHesapla(0, lysKimyaY);
                    lysKimyaN = lysKimya.getNet();
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }
            } else if (kimyaD && kimyaY && !kimyaN) {
                lysKimyaN = Double.parseDouble(etLysKimyaN.getText().toString());
                if (lysKimyaN > 30) {
                    etLysKimyaN.setText("");
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!kimyaD && !kimyaY && kimyaN) {
                lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                if (lysKimyaD + lysKimyaY > 30) {
                    etLysKimyaD.setText("");
                    etLysKimyaY.setText("");
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysKimya = new NetHesapla(lysKimyaD, lysKimyaY);
                    lysKimyaN = lysKimya.getNet();
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }

            } else if (!kimyaD && kimyaY) {
                lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                if (lysKimyaD > 30) {
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysKimyaN = lysKimyaD;
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }

            } else if (kimyaD && !kimyaY) {
                lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                if (lysKimyaY > 30) {
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysKimya = new NetHesapla(0, lysKimyaY);
                    lysKimyaN = lysKimya.getNet();
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }

            } else if (!kimyaD) {
                lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                if (lysKimyaD + lysKimyaY > 30) {
                    String hata_mesaji = "Kimya testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysKimya = new NetHesapla(lysKimyaD, lysKimyaY);
                    lysKimyaN = lysKimya.getNet();
                    etLysKimyaN.setText(String.valueOf(format.format(lysKimyaN)));
                }

            } else
                lysKimyaN = 0;

            boolean biyoD = etLysBiyoD.getText().toString().equals("");
            boolean biyoY = etLysBiyoY.getText().toString().equals("");
            boolean biyoN = etLysBiyoN.getText().toString().equals("");
            if (!biyoD && biyoY && biyoN) {
                lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                if (lysBiyoD > 30) {
                    etLysBiyoD.setText("");
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysBiyoN = lysBiyoD;
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }

            } else if (biyoD && !biyoY && biyoN) {
                lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                if (lysBiyoY > 30) {
                    etLysBiyoY.setText("");
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysBiyo = new NetHesapla(0, lysBiyoY);
                    lysBiyoN = lysBiyo.getNet();
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }
            } else if (biyoD && biyoY && !biyoN) {
                lysBiyoN = Double.parseDouble(etLysBiyoN.getText().toString());
                if (lysBiyoN > 30) {
                    etLysBiyoN.setText("");
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!biyoD && !biyoY && biyoN) {
                lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                if (lysBiyoD + lysBiyoY > 30) {
                    etLysBiyoD.setText("");
                    etLysBiyoY.setText("");
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysBiyo = new NetHesapla(lysBiyoD, lysBiyoY);
                    lysBiyoN = lysBiyo.getNet();
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }

            } else if (!biyoD && biyoY) {
                lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                if (lysBiyoD > 30) {
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysBiyoN = lysBiyoD;
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }

            } else if (biyoD && !biyoY) {
                lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                if (lysBiyoY > 30) {
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysBiyo = new NetHesapla(0, lysBiyoY);
                    lysBiyoN = lysBiyo.getNet();
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }

            } else if (!biyoD) {
                lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                if (lysBiyoD + lysBiyoY > 30) {
                    String hata_mesaji = "Biyoloji testinde soru sayısı 30'dur.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysBiyo = new NetHesapla(lysBiyoD, lysBiyoY);
                    lysBiyoN = lysBiyo.getNet();
                    etLysBiyoN.setText(String.valueOf(format.format(lysBiyoN)));
                }

            } else
                lysBiyoN = 0;

            boolean edeD = etLysEdeD.getText().toString().equals("");
            boolean edeY = etLysEdeY.getText().toString().equals("");
            boolean edeN = etLysEdeN.getText().toString().equals("");
            if (!edeD && edeY && edeN) {
                lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                if (lysEdeD > 56) {
                    etLysEdeD.setText("");
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysEdeN = lysEdeD;
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }

            } else if (edeD && !edeY && edeN) {
                lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                if (lysEdeY > 56) {
                    etLysEdeY.setText("");
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysEde = new NetHesapla(0, lysEdeY);
                    lysEdeN = lysEde.getNet();
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }
            } else if (edeD && edeY && !edeN) {
                lysEdeN = Double.parseDouble(etLysEdeN.getText().toString());
                if (lysEdeN > 56) {
                    etLysEdeN.setText("");
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!edeD && !edeY && edeN) {
                lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                if (lysEdeD + lysEdeY > 56) {
                    etLysEdeD.setText("");
                    etLysEdeY.setText("");
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysEde = new NetHesapla(lysEdeD, lysEdeY);
                    lysEdeN = lysEde.getNet();
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }

            } else if (!edeD && edeY) {
                lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                if (lysEdeD > 56) {
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysEdeN = lysEdeD;
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }

            } else if (edeD && !edeY) {
                lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                if (lysEdeY > 56) {
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysEde = new NetHesapla(0, lysEdeY);
                    lysEdeN = lysEde.getNet();
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }

            } else if (!edeD) {
                lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                if (lysEdeD + lysEdeY > 56) {
                    String hata_mesaji = "Edebiyat testinde soru sayısı 56'dır.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysEde = new NetHesapla(lysEdeD, lysEdeY);
                    lysEdeN = lysEde.getNet();
                    etLysEdeN.setText(String.valueOf(format.format(lysEdeN)));
                }

            } else
                lysEdeN = 0;

            boolean cog1D = etLysCog1D.getText().toString().equals("");
            boolean cog1Y = etLysCog1Y.getText().toString().equals("");
            boolean cog1N = etLysCog1N.getText().toString().equals("");
            if (!cog1D && cog1Y && cog1N) {
                lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                if (lysCog1D > 24) {
                    etLysCog1D.setText("");
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysCog1N = lysCog1D;
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }

            } else if (cog1D && !cog1Y && cog1N) {
                lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                if (lysCog1Y > 24) {
                    etLysCog1Y.setText("");
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog1 = new NetHesapla(0, lysCog1Y);
                    lysCog1N = lysCog1.getNet();
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }
            } else if (cog1D && cog1Y && !cog1N) {
                lysCog1N = Double.parseDouble(etLysCog1N.getText().toString());
                if (lysCog1N > 24) {
                    etLysCog1N.setText("");
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!cog1D && !cog1Y && cog1N) {
                lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                if (lysCog1D + lysCog1Y > 24) {
                    etLysCog1D.setText("");
                    etLysCog1Y.setText("");
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog1 = new NetHesapla(lysCog1D, lysCog1Y);
                    lysCog1N = lysCog1.getNet();
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }

            } else if (!cog1D && cog1Y) {
                lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                if (lysCog1D > 24) {
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysCog1N = lysCog1D;
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }

            } else if (cog1D && !cog1Y) {
                lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                if (lysCog1Y > 24) {
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog1 = new NetHesapla(0, lysCog1Y);
                    lysCog1N = lysCog1.getNet();
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }

            } else if (!cog1D) {
                lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                if (lysCog1D + lysCog1Y > 24) {
                    String hata_mesaji = "Coğrafya-1 testinde soru sayısı 24'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog1 = new NetHesapla(lysCog1D, lysCog1Y);
                    lysCog1N = lysCog1.getNet();
                    etLysCog1N.setText(String.valueOf(format.format(lysCog1N)));
                }

            } else
                lysCog1N = 0;

            boolean tarihD = etLysTarihD.getText().toString().equals("");
            boolean tarihY = etLysTarihY.getText().toString().equals("");
            boolean tarihN = etLysTarihN.getText().toString().equals("");
            if (!tarihD && tarihY && tarihN) {
                lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                if (lysTarihD > 44) {
                    etLysTarihD.setText("");
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysTarihN = lysTarihD;
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }

            } else if (tarihD && !tarihY && tarihN) {
                lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                if (lysTarihY > 44) {
                    etLysTarihY.setText("");
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysTarih = new NetHesapla(0, lysTarihY);
                    lysTarihN = lysTarih.getNet();
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }
            } else if (tarihD && tarihY && !tarihN) {
                lysTarihN = Double.parseDouble(etLysTarihN.getText().toString());
                if (lysTarihN > 44) {
                    etLysTarihN.setText("");
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!tarihD && !tarihY && tarihN) {
                lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                if (lysTarihD + lysTarihY > 44) {
                    etLysTarihD.setText("");
                    etLysTarihY.setText("");
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysTarih = new NetHesapla(lysTarihD, lysTarihY);
                    lysTarihN = lysTarih.getNet();
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }

            } else if (!tarihD && tarihY) {
                lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                if (lysTarihD > 44) {
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysTarihN = lysTarihD;
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }

            } else if (tarihD && !tarihY) {
                lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                if (lysTarihY > 44) {
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysTarih = new NetHesapla(0, lysTarihY);
                    lysTarihN = lysTarih.getNet();
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }

            } else if (!tarihD) {
                lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                if (lysTarihD + lysTarihY > 44) {
                    String hata_mesaji = "Tarih testinde soru sayısı 44'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysTarih = new NetHesapla(lysTarihD, lysTarihY);
                    lysTarihN = lysTarih.getNet();
                    etLysTarihN.setText(String.valueOf(format.format(lysTarihN)));
                }

            } else
                lysTarihN = 0;

            boolean cog2D = etLysCog2D.getText().toString().equals("");
            boolean cog2Y = etLysCog2Y.getText().toString().equals("");
            boolean cog2N = etLysCog2N.getText().toString().equals("");
            if (!cog2D && cog2Y && cog2N) {
                lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                if (lysCog2D > 14) {
                    etLysCog2D.setText("");
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysCog2N = lysCog2D;
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }

            } else if (cog2D && !cog2Y && cog2N) {
                lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                if (lysCog2Y > 14) {
                    etLysCog2Y.setText("");
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog2 = new NetHesapla(0, lysCog2Y);
                    lysCog2N = lysCog2.getNet();
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }
            } else if (cog2D && cog2Y && !cog2N) {
                lysCog2N = Double.parseDouble(etLysCog2N.getText().toString());
                if (lysCog2N > 14) {
                    etLysCog2N.setText("");
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!cog2D && !cog2Y && cog2N) {
                lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                if (lysCog2D + lysCog2Y > 14) {
                    etLysCog2D.setText("");
                    etLysCog2Y.setText("");
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog2 = new NetHesapla(lysCog2D, lysCog2Y);
                    lysCog2N = lysCog2.getNet();
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }

            } else if (!cog2D && cog2Y) {
                lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                if (lysCog2D > 14) {
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysCog2N = lysCog2D;
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }

            } else if (cog2D && !cog2Y) {
                lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                if (lysCog2Y > 14) {
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog2 = new NetHesapla(0, lysCog2Y);
                    lysCog2N = lysCog2.getNet();
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }

            } else if (!cog2D) {
                lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                if (lysCog2D + lysCog2Y > 14) {
                    String hata_mesaji = "Coğrafya-2 testinde soru sayısı 14'dür.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysCog2 = new NetHesapla(lysCog2D, lysCog2Y);
                    lysCog2N = lysCog2.getNet();
                    etLysCog2N.setText(String.valueOf(format.format(lysCog2N)));
                }

            } else
                lysCog2N = 0;

            boolean felD = etLysFelD.getText().toString().equals("");
            boolean felY = etLysFelY.getText().toString().equals("");
            boolean felN = etLysFelN.getText().toString().equals("");
            if (!felD && felY && felN) {
                lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                if (lysFelD > 32) {
                    etLysFelD.setText("");
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysFelN = lysFelD;
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }

            } else if (felD && !felY && felN) {
                lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                if (lysFelY > 32) {
                    etLysFelY.setText("");
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFel = new NetHesapla(0, lysFelY);
                    lysFelN = lysFel.getNet();
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }
            } else if (felD && felY && !felN) {
                lysFelN = Double.parseDouble(etLysFelN.getText().toString());
                if (lysFelN > 32) {
                    etLysFelN.setText("");
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!felD && !felY && felN) {
                lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                if (lysFelD + lysFelY > 32) {
                    etLysFelD.setText("");
                    etLysFelY.setText("");
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFel = new NetHesapla(lysFelD, lysFelY);
                    lysFelN = lysFel.getNet();
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }

            } else if (!felD && felY) {
                lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                if (lysFelD > 32) {
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysFelN = lysFelD;
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }

            } else if (felD && !felY) {
                lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                if (lysFelY > 32) {
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFel = new NetHesapla(0, lysFelY);
                    lysFelN = lysFel.getNet();
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }

            } else if (!felD) {
                lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                if (lysFelD + lysFelY > 32) {
                    String hata_mesaji = "Felsefe/Din testinde soru sayısı 32'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysFel = new NetHesapla(lysFelD, lysFelY);
                    lysFelN = lysFel.getNet();
                    etLysFelN.setText(String.valueOf(format.format(lysFelN)));
                }

            } else
                lysFelN = 0;

            boolean ydilD = etLysYdilD.getText().toString().equals("");
            boolean ydilY = etLysYdilY.getText().toString().equals("");
            boolean ydilN = etLysYdilN.getText().toString().equals("");
            if (!ydilD && ydilY && ydilN) {
                lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                if (lysYdilD > 80) {
                    etLysYdilD.setText("");
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysYdilN = lysYdilD;
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }

            } else if (ydilD && !ydilY && ydilN) {
                lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                if (lysYdilY > 80) {
                    etLysYdilY.setText("");
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysYdil = new NetHesapla(0, lysYdilY);
                    lysYdilN = lysYdil.getNet();
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }
            } else if (ydilD && ydilY && !ydilN) {
                lysYdilN = Double.parseDouble(etLysYdilN.getText().toString());
                if (lysYdilN > 80) {
                    etLysYdilN.setText("");
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!ydilD && !ydilY && ydilN) {
                lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                if (lysYdilD + lysYdilY > 80) {
                    etLysYdilD.setText("");
                    etLysYdilY.setText("");
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysYdil = new NetHesapla(lysYdilD, lysYdilY);
                    lysYdilN = lysYdil.getNet();
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }

            } else if (!ydilD && ydilY) {
                lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                if (lysYdilD > 80) {
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    lysYdilN = lysYdilD;
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }

            } else if (ydilD && !ydilY) {
                lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                if (lysYdilY > 80) {
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysYdil = new NetHesapla(0, lysYdilY);
                    lysYdilN = lysYdil.getNet();
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }

            } else if (!ydilD) {
                lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                if (lysYdilD + lysYdilY > 80) {
                    String hata_mesaji = "Yabancı Dil testinde soru sayısı 80'dir.";
                    Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla lysYdil = new NetHesapla(lysYdilD, lysYdilY);
                    lysYdilN = lysYdil.getNet();
                    etLysYdilN.setText(String.valueOf(format.format(lysYdilN)));
                }

            } else
                lysYdilN = 0;

            boolean obp = etDiploma.getText().toString().equals("");
            if (obp) {
                OBP = 0;
            } else {
                OBP = Double.parseDouble(etDiploma.getText().toString());
                OBP = (OBP*5)*0.12;
            }

            lysPuanHesapIslemleri(lysMatN, lysGeoN, lysFizikN, lysKimyaN, lysBiyoN, lysEdeN, lysCog1N,
                    lysTarihN, lysCog2N, lysFelN, lysYdilN);

            Log.d(LOG_TAG, "Lys notları alınıp puanlar hesaplandı.");
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            String hata_mesaji = "Alanları boş bırakmayın.";
            Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
        }
    }

    public void lysPuanHesapIslemleri(double lysMatN, double lysGeoN, double lysFizikN, double lysKimyaN,
                                      double lysBiyoN, double lysEdeN, double lysCog1N, double lysTarihN, double lysCog2N, double lysFelN, double lysYdilN) {
        this.lysMatN = lysMatN;
        this.lysGeoN = lysGeoN;
        this.lysFizikN = lysFizikN;
        this.lysKimyaN = lysKimyaN;
        this.lysBiyoN = lysBiyoN;
        this.lysEdeN = lysEdeN;
        this.lysCog1N = lysCog1N;
        this.lysTarihN = lysTarihN;
        this.lysCog2N = lysCog2N;
        this.lysFelN = lysFelN;
        this.lysYdilN = lysYdilN;

        Cursor res = myDb.ygsNetleriAl();

        String[] netler = new String[4];
        int n = 0;

        while (res.moveToNext()) {
            netler[n] = res.getString(1);
            n++;
        }

        LysPuanHesaplama lys = new LysPuanHesaplama(Double.parseDouble(netler[0]), Double.parseDouble(netler[1]),
                Double.parseDouble(netler[2]), Double.parseDouble(netler[3]), lysMatN, lysGeoN, lysFizikN, lysKimyaN,
                lysBiyoN, lysEdeN, lysCog1N, lysTarihN, lysCog2N, lysFelN, lysYdilN);
        tMf1.setText(String.format("MF-1 : %.2f", lys.getMF1() + OBP));
        tMf2.setText(String.format("MF-2 : %.2f", lys.getMF2() + OBP));
        tMf3.setText(String.format("MF-3 : %.2f", lys.getMF3() + OBP));
        tMf4.setText(String.format("MF-4 : %.2f", lys.getMF4() + OBP));
        tTm1.setText(String.format("TM-1 : %.2f", lys.getTM1() + OBP));
        tTm2.setText(String.format("TM-2 : %.2f", lys.getTM2() + OBP));
        tTm3.setText(String.format("TM-3 : %.2f", lys.getTM3() + OBP));
        tTs1.setText(String.format("TS-1 : %.2f", lys.getTS1() + OBP));
        tTs2.setText(String.format("TS-2 : %.2f", lys.getTS2() + OBP));
        tYdil1.setText(String.format("Dil-1 : %.2f", lys.getDil1() + OBP));
        tYdil2.setText(String.format("Dil-2 : %.2f", lys.getDil2() + OBP));
        tYdil3.setText(String.format("Dil-3 : %.2f", lys.getDil3() + OBP));
    }

    public void lysTemizle() {
        lysMatN = 0;
        lysGeoN = 0;
        lysFizikN = 0;
        lysKimyaN = 0;
        lysBiyoN = 0;
        lysEdeN = 0;
        lysCog1N = 0;
        lysTarihN = 0;
        lysCog2N = 0;
        lysFelN = 0;
        lysYdilN = 0;
        etLysMatD.setText("");
        etLysMatY.setText("");
        etLysMatN.setText("");
        etLysGeoD.setText("");
        etLysGeoY.setText("");
        etLysGeoN.setText("");
        etLysFizikD.setText("");
        etLysFizikY.setText("");
        etLysFizikN.setText("");
        etLysKimyaD.setText("");
        etLysKimyaY.setText("");
        etLysKimyaN.setText("");
        etLysBiyoD.setText("");
        etLysBiyoY.setText("");
        etLysBiyoN.setText("");
        etLysEdeD.setText("");
        etLysEdeY.setText("");
        etLysEdeN.setText("");
        etLysCog1D.setText("");
        etLysCog1Y.setText("");
        etLysCog1N.setText("");
        etLysTarihD.setText("");
        etLysTarihY.setText("");
        etLysTarihN.setText("");
        etLysCog2D.setText("");
        etLysCog2Y.setText("");
        etLysCog2N.setText("");
        etLysFelD.setText("");
        etLysFelY.setText("");
        etLysFelN.setText("");
        etLysYdilD.setText("");
        etLysYdilY.setText("");
        etLysYdilN.setText("");

        lysPuanHesapIslemleri(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

}

package demirciy.ygslyspuanhesaplama;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Lys extends AppCompatActivity {

    private EditText etLysMatD, etLysMatY, etLysMatN, etLysGeoD, etLysGeoY,
    etLysGeoN, etLysFizikD, etLysFizikY, etLysFizikN, et_lys_kimya_dogru,
    et_lys_kimya_yanlis, et_lys_kimya_net, et_lys_biyo_dogru, et_lys_biyo_yanlis, et_lys_biyo_net,
    et_lys_ede_dogru, et_lys_ede_yanlis, et_lys_ede_net, et_lys_cog1_dogru, et_lys_cog1_yanlis, et_lys_cog1_net,
    et_lys_tarih_dogru, et_lys_tarih_yanlis, et_lys_tarih_net, et_lys_cog2_dogru, et_lys_cog2_yanlis, et_lys_cog2_net,
    et_lys_fel_dogru, et_lys_fel_yanlis, et_lys_fel_net, et_lys_ydil_dogru, et_lys_ydil_yanlis, et_lys_ydil_net;
    private Button btnHesapla;
    private TextView text_lys_mf1, text_lys_mf2, text_lys_mf3, text_lys_mf4, text_lys_tm1, text_lys_tm2,
    text_lys_tm3, text_lys_ts1, text_lys_ts2, text_lys_dil1, text_lys_dil2, text_lys_dil3;

    private double lysMatD, lysMatY, lysMatN, lysGeoD, lysGeoY, lysGeoN, lysFizikD, lysFizikY, lysFizikN,
    lysKimyaD, lysKimyaY, lysKimyaN, lysBiyoD, lysBiyoY, lysBiyoN, lysEdeD, lysEdeY, lysEdeN, lysCog1D,
    lysCog1Y, lysCog1N, lysTarihD, lysTarihY, lysTarihN, lysCog2D, lysCog2Y, lysCog2N, lysFelD, lysFelY,
    lysFelN, lysYdilD, lysYdilY, lysYdilN;

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
        et_lys_kimya_dogru = (EditText) findViewById(R.id.et_lys_kimya_dogru);
        et_lys_kimya_yanlis = (EditText) findViewById(R.id.et_lys_kimya_yanlis);
        et_lys_kimya_net = (EditText) findViewById(R.id.et_lys_kimya_net);
        et_lys_biyo_dogru = (EditText) findViewById(R.id.et_lys_biyo_dogru);
        et_lys_biyo_yanlis = (EditText) findViewById(R.id.et_lys_biyo_yanlis);
        et_lys_biyo_net = (EditText) findViewById(R.id.et_lys_biyo_net);
        et_lys_ede_dogru = (EditText) findViewById(R.id.et_lys_ede_dogru);
        et_lys_ede_yanlis = (EditText) findViewById(R.id.et_lys_ede_yanlis);
        et_lys_ede_net = (EditText) findViewById(R.id.et_lys_ede_net);
        et_lys_cog1_dogru = (EditText) findViewById(R.id.et_lys_cog1_dogru);
        et_lys_cog1_yanlis = (EditText) findViewById(R.id.et_lys_cog1_yanlis);
        et_lys_cog1_net = (EditText) findViewById(R.id.et_lys_cog1_net);
        et_lys_tarih_dogru = (EditText) findViewById(R.id.et_lys_tarih_dogru);
        et_lys_tarih_yanlis = (EditText) findViewById(R.id.et_lys_tarih_yanlis);
        et_lys_tarih_net = (EditText) findViewById(R.id.et_lys_tarih_net);
        et_lys_cog2_dogru = (EditText) findViewById(R.id.et_lys_cog2_dogru);
        et_lys_cog2_yanlis = (EditText) findViewById(R.id.et_lys_cog2_yanlis);
        et_lys_cog2_net = (EditText) findViewById(R.id.et_lys_cog2_net);
        et_lys_fel_dogru = (EditText) findViewById(R.id.et_lys_fel_dogru);
        et_lys_fel_yanlis = (EditText) findViewById(R.id.et_lys_fel_yanlis);
        et_lys_fel_net = (EditText) findViewById(R.id.et_lys_fel_net);
        et_lys_ydil_dogru = (EditText) findViewById(R.id.et_lys_ydil_dogru);
        et_lys_ydil_yanlis = (EditText) findViewById(R.id.et_lys_ydil_yanlis);
        et_lys_ydil_net = (EditText) findViewById(R.id.et_lys_ydil_net);
        btnHesapla = (Button) findViewById(R.id.btnHesapla);
        text_lys_mf1 = (TextView) findViewById(R.id.text_lys_mf1);
        text_lys_mf2 = (TextView) findViewById(R.id.text_lys_mf2);
        text_lys_mf3 = (TextView) findViewById(R.id.text_lys_mf3);
        text_lys_mf4 = (TextView) findViewById(R.id.text_lys_mf4);
        text_lys_tm1 = (TextView) findViewById(R.id.text_lys_tm1);
        text_lys_tm2 = (TextView) findViewById(R.id.text_lys_tm2);
        text_lys_tm3 = (TextView) findViewById(R.id.text_lys_tm3);
        text_lys_ts1 = (TextView) findViewById(R.id.text_lys_ts1);
        text_lys_ts2 = (TextView) findViewById(R.id.text_lys_ts2);
        text_lys_dil1 = (TextView) findViewById(R.id.text_lys_dil1);
        text_lys_dil2 = (TextView) findViewById(R.id.text_lys_dil2);
        text_lys_dil3 = (TextView) findViewById(R.id.text_lys_dil3);



        myDb = new DatabaseHelper(this);

    }

    public void YerlestirmePuanHesapla(View view)
    {
        Log.d(LOG_TAG, "Lys notları alınıyor.");
        try {
            lysMatD = Double.parseDouble(etLysMatD.getText().toString());
            lysMatY = Double.parseDouble(etLysMatY.getText().toString());
            lysMatN = Double.parseDouble(etLysMatN.getText().toString());
            if (lysMatN == 0) {
                NetHesapla lysMat = new NetHesapla(lysMatD, lysMatY);
                lysMatN = lysMat.getNet();
                etLysMatN.setText(String.valueOf(format.format(lysMatN)));
            } else {
                if (lysMatD == 0 && lysMatY == 0) {
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                } else {
                    NetHesapla lysMat = new NetHesapla(lysMatD, lysMatY);
                    lysMatN = lysMat.getNet();
                    etLysMatN.setText(String.valueOf(format.format(lysMatN)));
                }
            }

            lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
            lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
            lysGeoN = Double.parseDouble(etLysGeoN.getText().toString());
            if (lysGeoN == 0) {
                NetHesapla lysGeo = new NetHesapla(lysGeoD, lysGeoY);
                lysGeoN = lysGeo.getNet();
                etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
            } else {
                if (lysGeoD == 0 && lysGeoY == 0) {
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                } else {
                    NetHesapla lysGeo = new NetHesapla(lysGeoD, lysGeoY);
                    lysGeoN = lysGeo.getNet();
                    etLysGeoN.setText(String.valueOf(format.format(lysGeoN)));
                }
            }

            lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
            lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
            lysFizikN = Double.parseDouble(etLysFizikN.getText().toString());
            if (lysFizikN == 0) {
                NetHesapla lysFizik = new NetHesapla(lysFizikD, lysFizikY);
                lysFizikN = lysFizik.getNet();
                etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
            } else {
                if (lysFizikD == 0 && lysFizikY == 0) {
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                } else {
                    NetHesapla lysFizik = new NetHesapla(lysFizikD, lysFizikY);
                    lysFizikN = lysFizik.getNet();
                    etLysFizikN.setText(String.valueOf(format.format(lysFizikN)));
                }
            }

            lysKimyaD = Double.parseDouble(et_lys_kimya_dogru.getText().toString());
            lysKimyaY = Double.parseDouble(et_lys_kimya_yanlis.getText().toString());
            lysKimyaN = Double.parseDouble(et_lys_kimya_net.getText().toString());
            if (lysKimyaN == 0) {
                NetHesapla lysKimya = new NetHesapla(lysKimyaD, lysKimyaY);
                lysKimyaN = lysKimya.getNet();
                et_lys_kimya_net.setText(String.valueOf(format.format(lysKimyaN)));
            } else {
                if (lysKimyaD == 0 && lysKimyaY == 0) {
                    et_lys_kimya_net.setText(String.valueOf(format.format(lysKimyaN)));
                } else {
                    NetHesapla lysKimya = new NetHesapla(lysKimyaD, lysKimyaY);
                    lysKimyaN = lysKimya.getNet();
                    et_lys_kimya_net.setText(String.valueOf(format.format(lysKimyaN)));
                }
            }

            lysBiyoD = Double.parseDouble(et_lys_biyo_dogru.getText().toString());
            lysBiyoY = Double.parseDouble(et_lys_biyo_yanlis.getText().toString());
            lysBiyoN = Double.parseDouble(et_lys_biyo_net.getText().toString());
            if (lysBiyoN == 0) {
                NetHesapla lysBiyo = new NetHesapla(lysBiyoD, lysBiyoY);
                lysBiyoN = lysBiyo.getNet();
                et_lys_biyo_net.setText(String.valueOf(format.format(lysBiyoN)));
            } else {
                if (lysBiyoD == 0 && lysBiyoY == 0) {
                    et_lys_biyo_net.setText(String.valueOf(format.format(lysBiyoN)));
                } else {
                    NetHesapla lysBiyo = new NetHesapla(lysBiyoD, lysBiyoY);
                    lysBiyoN = lysBiyo.getNet();
                    et_lys_biyo_net.setText(String.valueOf(format.format(lysBiyoN)));
                }
            }

            lysEdeD = Double.parseDouble(et_lys_ede_dogru.getText().toString());
            lysEdeY = Double.parseDouble(et_lys_ede_yanlis.getText().toString());
            lysEdeN = Double.parseDouble(et_lys_ede_net.getText().toString());
            if (lysEdeN == 0) {
                NetHesapla lysEde = new NetHesapla(lysEdeD, lysEdeY);
                lysEdeN = lysEde.getNet();
                et_lys_ede_net.setText(String.valueOf(format.format(lysEdeN)));
            } else {
                if (lysEdeD == 0 && lysEdeY == 0) {
                    et_lys_ede_net.setText(String.valueOf(format.format(lysEdeN)));
                } else {
                    NetHesapla lysEde = new NetHesapla(lysEdeD, lysEdeY);
                    lysEdeN = lysEde.getNet();
                    et_lys_ede_net.setText(String.valueOf(format.format(lysEdeN)));
                }
            }

            lysCog1D = Double.parseDouble(et_lys_cog1_dogru.getText().toString());
            lysCog1Y = Double.parseDouble(et_lys_cog1_yanlis.getText().toString());
            lysCog1N = Double.parseDouble(et_lys_cog1_net.getText().toString());
            if (lysCog1N == 0) {
                NetHesapla lysCog1 = new NetHesapla(lysCog1D, lysCog1Y);
                lysCog1N = lysCog1.getNet();
                et_lys_cog1_net.setText(String.valueOf(format.format(lysCog1N)));
            } else {
                if (lysCog1D == 0 && lysCog1Y == 0) {
                    et_lys_cog1_net.setText(String.valueOf(format.format(lysCog1N)));
                } else {
                    NetHesapla lysCog1 = new NetHesapla(lysCog1D, lysCog1Y);
                    lysCog1N = lysCog1.getNet();
                    et_lys_cog1_net.setText(String.valueOf(format.format(lysCog1N)));
                }
            }

            lysTarihD = Double.parseDouble(et_lys_tarih_dogru.getText().toString());
            lysTarihY = Double.parseDouble(et_lys_tarih_yanlis.getText().toString());
            lysTarihN = Double.parseDouble(et_lys_tarih_net.getText().toString());
            if (lysTarihN == 0) {
                NetHesapla lysTarih = new NetHesapla(lysTarihD, lysTarihY);
                lysTarihN = lysTarih.getNet();
                et_lys_tarih_net.setText(String.valueOf(format.format(lysTarihN)));
            } else {
                if (lysTarihD == 0 && lysTarihY == 0) {
                    et_lys_tarih_net.setText(String.valueOf(format.format(lysTarihN)));
                } else {
                    NetHesapla lysTarih = new NetHesapla(lysTarihD, lysTarihY);
                    lysTarihN = lysTarih.getNet();
                    et_lys_tarih_net.setText(String.valueOf(format.format(lysTarihN)));
                }
            }

            lysCog2D = Double.parseDouble(et_lys_cog2_dogru.getText().toString());
            lysCog2Y = Double.parseDouble(et_lys_cog2_yanlis.getText().toString());
            lysCog2N = Double.parseDouble(et_lys_cog2_net.getText().toString());
            if (lysCog2N == 0) {
                NetHesapla lysCog2 = new NetHesapla(lysCog2D, lysCog2Y);
                lysCog2N = lysCog2.getNet();
                et_lys_cog2_net.setText(String.valueOf(format.format(lysCog2N)));
            } else {
                if (lysCog2D == 0 && lysCog2Y == 0) {
                    et_lys_cog2_net.setText(String.valueOf(format.format(lysCog2N)));
                } else {
                    NetHesapla lysCog2 = new NetHesapla(lysCog2D, lysCog2Y);
                    lysCog2N = lysCog2.getNet();
                    et_lys_cog2_net.setText(String.valueOf(format.format(lysCog2N)));
                }
            }

            lysFelD = Double.parseDouble(et_lys_fel_dogru.getText().toString());
            lysFelY = Double.parseDouble(et_lys_fel_yanlis.getText().toString());
            lysFelN = Double.parseDouble(et_lys_fel_net.getText().toString());
            if (lysFelN == 0) {
                NetHesapla lysFel = new NetHesapla(lysFelD, lysFelY);
                lysFelN = lysFel.getNet();
                et_lys_fel_net.setText(String.valueOf(format.format(lysFelN)));
            } else {
                if (lysFelD == 0 && lysFelY == 0) {
                    et_lys_fel_net.setText(String.valueOf(format.format(lysFelN)));
                } else {
                    NetHesapla lysFel = new NetHesapla(lysFelD, lysFelY);
                    lysFelN = lysFel.getNet();
                    et_lys_fel_net.setText(String.valueOf(format.format(lysFelN)));
                }
            }

            lysYdilD = Double.parseDouble(et_lys_ydil_dogru.getText().toString());
            lysYdilY = Double.parseDouble(et_lys_ydil_yanlis.getText().toString());
            lysYdilN = Double.parseDouble(et_lys_ydil_net.getText().toString());
            if (lysYdilN == 0) {
                NetHesapla lysYdil = new NetHesapla(lysYdilD, lysYdilY);
                lysYdilN = lysYdil.getNet();
                et_lys_ydil_net.setText(String.valueOf(format.format(lysYdilN)));
            } else {
                if (lysYdilD == 0 && lysYdilY == 0) {
                    et_lys_ydil_net.setText(String.valueOf(format.format(lysYdilN)));
                } else {
                    NetHesapla lysYdil = new NetHesapla(lysYdilD, lysYdilY);
                    lysYdilN = lysYdil.getNet();
                    et_lys_ydil_net.setText(String.valueOf(format.format(lysYdilN)));
                }
            }


            Cursor res = myDb.ygsNetleriAl();

            String[] dersler = new String[4];
            String[] netler = new String[4];
            int d = 0, n = 0;

            while (res.moveToNext()) {
                dersler[d] = res.getString(0);
                netler[n] = res.getString(1);
                d++;
                n++;
            }

            LysPuanHesaplama lys = new LysPuanHesaplama(Double.parseDouble(netler[0]), Double.parseDouble(netler[1]),
                    Double.parseDouble(netler[2]), Double.parseDouble(netler[3]), lysMatN, lysGeoN, lysFizikN, lysKimyaN,
                    lysBiyoN, lysEdeN, lysCog1N, lysTarihN, lysCog2N, lysFelN, lysYdilN);
            text_lys_mf1.setText(String.format("MF-1 : %.2f", lys.getMF1()));
            text_lys_mf2.setText(String.format("MF-2 : %.2f", lys.getMF2()));
            text_lys_mf3.setText(String.format("MF-3 : %.2f", lys.getMF3()));
            text_lys_mf4.setText(String.format("MF-4 : %.2f", lys.getMF4()));
            text_lys_tm1.setText(String.format("TM-1 : %.2f", lys.getTM1()));
            text_lys_tm2.setText(String.format("TM-2 : %.2f", lys.getTM2()));
            text_lys_tm3.setText(String.format("TM-3 : %.2f", lys.getTM3()));
            text_lys_ts1.setText(String.format("TS-1 : %.2f", lys.getTS1()));
            text_lys_ts2.setText(String.format("TS-2 : %.2f", lys.getTS2()));
            text_lys_dil1.setText(String.format("Dil-1 : %.2f", lys.getDil1()));
            text_lys_dil2.setText(String.format("Dil-2 : %.2f", lys.getDil2()));
            text_lys_dil3.setText(String.format("Dil-3 : %.2f", lys.getDil3()));

            Log.d(LOG_TAG, "Lys notları alınıp puanlar hesaplandı.");
        }

            catch (Exception e)
            {
                Log.e(LOG_TAG, e.getMessage());

                String hata_mesaji = "Alanları boş bırakmayın.";
                Toast.makeText(Lys.this, hata_mesaji, Toast.LENGTH_LONG).show();
            }

    }

    public void LysTemizle(View view)
    {
        etLysMatD.setText("0");
        etLysMatY.setText("0");
        etLysMatN.setText("0");
        etLysGeoD.setText("0");
        etLysGeoY.setText("0");
        etLysGeoN.setText("0");
        etLysFizikD.setText("0");
        etLysFizikY.setText("0");
        etLysFizikN.setText("0");
        et_lys_kimya_dogru.setText("0");
        et_lys_kimya_yanlis.setText("0");
        et_lys_kimya_net.setText("0");
        et_lys_biyo_dogru.setText("0");
        et_lys_biyo_yanlis.setText("0");
        et_lys_biyo_net.setText("0");
        et_lys_ede_dogru.setText("0");
        et_lys_ede_yanlis.setText("0");
        et_lys_ede_net.setText("0");
        et_lys_cog1_dogru.setText("0");
        et_lys_cog1_yanlis.setText("0");
        et_lys_cog1_net.setText("0");
        et_lys_tarih_dogru.setText("0");
        et_lys_tarih_yanlis.setText("0");
        et_lys_tarih_net.setText("0");
        et_lys_cog2_dogru.setText("0");
        et_lys_cog2_yanlis.setText("0");
        et_lys_cog2_net.setText("0");
        et_lys_fel_dogru.setText("0");
        et_lys_fel_yanlis.setText("0");
        et_lys_fel_net.setText("0");
        et_lys_ydil_dogru.setText("0");
        et_lys_ydil_yanlis.setText("0");
        et_lys_ydil_net.setText("0");
        text_lys_mf1.setText("MF-1 : ");
        text_lys_mf2.setText("MF-2 : ");
        text_lys_mf3.setText("MF-3 : ");
        text_lys_mf4.setText("MF-4 : ");
        text_lys_tm1.setText("TM-1 : ");
        text_lys_tm2.setText("TM-2 : ");
        text_lys_tm3.setText("TM-3 : ");
        text_lys_ts1.setText("TS-1 : ");
        text_lys_ts2.setText("TS-2 : ");
        text_lys_dil1.setText("Dil-1 : ");
        text_lys_dil2.setText("Dil-2 : ");
        text_lys_dil3.setText("Dil-3 : ");
    }

}

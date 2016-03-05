package demirciy.ygslyspuanhesaplama;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private double ygsTrD, ygsTrY, ygsSosD, ygsSosY, ygsMatD,
            ygsMatY, ygsFenD, ygsFenY;
    public double ygsTrN, ygsSosN, ygsMatN, ygsFenN;
    private double ygs1, ygs2, ygs3, ygs4, ygs5, ygs6;

    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "LogCat çıktıları -->";

    DatabaseHelper myDb;

    //başlagıçta ekrana bir bildiri gönder ve sayısal yeşil, eşit ağırlık mor, sözel açık sarı yazdır
    //iconu ayarla

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
        Button btnLys = (Button) findViewById(R.id.btnLys);
        Button btnTemizle = (Button) findViewById(R.id.btnTemizle);

        //btnLys.setOnClickListener(this);
        //btnTemizle.setOnClickListener(this);

        myDb = new DatabaseHelper(this);

        etYgsTrD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean trY = etYgsTrY.getText().toString().equals("");
                    if (trY) {
                        ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                        if (ygsTrD > 40) {
                            etYgsTrD.setText("");
                            etYgsTrN.setText("");
                            ygsTrHataMesaji();
                        } else {
                            ygsTrN = ygsTrD;
                            etYgsTrN.setText(format.format(ygsTrN));
                        }
                    } else {
                        ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                        ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                        if (ygsTrD + ygsTrY > 40) {
                            etYgsTrD.setText("");
                            etYgsTrY.setText("");
                            etYgsTrN.setText("");
                            ygsTrHataMesaji();
                        } else {
                            NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                            ygsTrN = ygsTr.getNet();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsTrY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean trD = etYgsTrD.getText().toString().equals("");

                    if (trD) {
                        ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                        if (ygsTrY > 40) {
                            etYgsTrY.setText("");
                            etYgsTrN.setText("");
                            ygsTrHataMesaji();
                        } else {
                            NetHesapla ygsTr = new NetHesapla(0, ygsTrY);
                            ygsTrN = ygsTr.getNet();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }

                    } else {
                        ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                        ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                        if (ygsTrD + ygsTrY > 40) {
                            etYgsTrD.setText("");
                            etYgsTrY.setText("");
                            etYgsTrN.setText("");
                            ygsTrHataMesaji();
                        } else {
                            NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                            ygsTrN = ygsTr.getNet();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsTrN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    ygsTrN = Double.parseDouble(etYgsTrN.getText().toString());
                    if (ygsTrN > 40) {
                        etYgsTrN.setText("");
                        ygsTrHataMesaji();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });

        etYgsSosD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean sosY = etYgsSosY.getText().toString().equals("");
                    if (sosY) {
                        ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                        if (ygsSosD > 40) {
                            etYgsSosD.setText("");
                            etYgsSosN.setText("");
                            ygsSosHataMesaji();
                        } else {
                            ygsSosN = ygsSosD;
                            etYgsSosN.setText(format.format(ygsSosN));
                        }
                    } else {
                        ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                        ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                        if (ygsSosD + ygsSosY > 40) {
                            etYgsSosD.setText("");
                            etYgsSosY.setText("");
                            etYgsSosN.setText("");
                            ygsSosHataMesaji();
                        } else {
                            NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                            ygsSosN = ygsSos.getNet();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsSosY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean sosD = etYgsSosD.getText().toString().equals("");

                    if (sosD) {
                        ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                        if (ygsSosY > 40) {
                            etYgsSosY.setText("");
                            etYgsSosN.setText("");
                            ygsSosHataMesaji();
                        } else {
                            NetHesapla ygsSos = new NetHesapla(0, ygsSosY);
                            ygsSosN = ygsSos.getNet();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }

                    } else {
                        ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                        ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                        if (ygsSosD + ygsSosY > 40) {
                            etYgsSosD.setText("");
                            etYgsSosY.setText("");
                            etYgsSosN.setText("");
                            ygsSosHataMesaji();
                        } else {
                            NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                            ygsSosN = ygsSos.getNet();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsSosN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    ygsSosN = Double.parseDouble(etYgsSosN.getText().toString());
                    if (ygsSosN > 40) {
                        etYgsSosN.setText("");
                        ygsSosHataMesaji();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });

        etYgsMatD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean matY = etYgsMatY.getText().toString().equals("");
                    if (matY) {
                        ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                        if (ygsMatD > 40) {
                            etYgsMatD.setText("");
                            etYgsMatN.setText("");
                            ygsMatHataMesaji();
                        } else {
                            ygsMatN = ygsMatD;
                            etYgsMatN.setText(format.format(ygsMatN));
                        }
                    } else {
                        ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                        ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                        if (ygsMatD + ygsMatY > 40) {
                            etYgsMatD.setText("");
                            etYgsMatY.setText("");
                            etYgsMatN.setText("");
                            ygsMatHataMesaji();
                        } else {
                            NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                            ygsMatN = ygsMat.getNet();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsMatY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean matD = etYgsMatD.getText().toString().equals("");

                    if (matD) {
                        ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                        if (ygsMatY > 40) {
                            etYgsMatY.setText("");
                            etYgsMatN.setText("");
                            ygsMatHataMesaji();
                        } else {
                            NetHesapla ygsMat = new NetHesapla(0, ygsMatY);
                            ygsMatN = ygsMat.getNet();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }

                    } else {
                        ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                        ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                        if (ygsMatD + ygsMatY > 40) {
                            etYgsMatD.setText("");
                            etYgsMatY.setText("");
                            etYgsMatN.setText("");
                            ygsMatHataMesaji();
                        } else {
                            NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                            ygsMatN = ygsMat.getNet();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsMatN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    ygsMatN = Double.parseDouble(etYgsMatN.getText().toString());
                    if (ygsMatN > 40) {
                        etYgsMatN.setText("");
                        ygsMatHataMesaji();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });

        etYgsFenD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean fenY = etYgsFenY.getText().toString().equals("");
                    if (fenY) {
                        ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                        if (ygsFenD > 40) {
                            etYgsFenD.setText("");
                            etYgsFenN.setText("");
                            ygsFenHataMesaji();
                        } else {
                            ygsFenN = ygsFenD;
                            etYgsFenN.setText(format.format(ygsFenN));
                        }
                    } else {
                        ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                        ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                        if (ygsFenD + ygsFenY > 40) {
                            etYgsFenD.setText("");
                            etYgsFenY.setText("");
                            etYgsFenN.setText("");
                            ygsFenHataMesaji();
                        } else {
                            NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                            ygsFenN = ygsFen.getNet();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsFenY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean fenD = etYgsFenD.getText().toString().equals("");

                    if (fenD) {
                        ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                        if (ygsFenY > 40) {
                            etYgsFenY.setText("");
                            etYgsFenN.setText("");
                            ygsFenHataMesaji();
                        } else {
                            NetHesapla ygsFen = new NetHesapla(0, ygsFenY);
                            ygsFenN = ygsFen.getNet();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }

                    } else {
                        ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                        ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                        if (ygsFenD + ygsFenY > 40) {
                            etYgsFenD.setText("");
                            etYgsFenY.setText("");
                            etYgsFenN.setText("");
                            ygsFenHataMesaji();
                        } else {
                            NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                            ygsFenN = ygsFen.getNet();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });
        etYgsFenN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    ygsFenN = Double.parseDouble(etYgsFenN.getText().toString());
                    if (ygsFenN > 40) {
                        etYgsFenN.setText("");
                        ygsFenHataMesaji();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsToplamYaz();
                ygsPuanGoster();
                ygsVeritabaniNetEkle();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.abTemizle:
                ygsTemizle();
                break;
            case R.id.abKaydet:
                break;
            case R.id.abPaylas:
                break;
            case R.id.abPuanlarim:
                break;
            case R.id.abYgsLysNedir:
                break;
            case R.id.abHakkinda:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHesapla:

                break;
            case R.id.btnLys:
                gitLys();
                break;
            case R.id.btnTemizle:

                break;
            default:
                break;
        }
    }

   /* public void ygsHesapla() {
        Log.d(LOG_TAG, "Ygs notları alınıyor.");

        try {
            boolean trD = etYgsTrD.getText().toString().equals("");
            boolean trY = etYgsTrY.getText().toString().equals("");
            boolean trN = etYgsTrN.getText().toString().equals("");
            if (!trD && trY && trN) {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                if (ygsTrD > 40) {
                    etYgsTrD.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsTrN = ygsTrD;
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }

            } else if (trD && !trY && trN) {
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                if (ygsTrY > 40) {
                    etYgsTrY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsTr = new NetHesapla(0, ygsTrY);
                    ygsTrN = ygsTr.getNet();
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }
            } else if (trD && trY && !trN) {
                ygsTrN = Double.parseDouble(etYgsTrN.getText().toString());
                if (ygsTrN > 40) {
                    etYgsTrN.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!trD && !trY && trN) {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                if (ygsTrD + ygsTrY > 40) {
                    etYgsTrD.setText("");
                    etYgsTrY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                    ygsTrN = ygsTr.getNet();
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }

            } else if (!trD && trY) {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                if (ygsTrD > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsTrN = ygsTrD;
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }

            } else if (trD && !trY) {
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                if (ygsTrY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsTr = new NetHesapla(0, ygsTrY);
                    ygsTrN = ygsTr.getNet();
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }

            } else if (!trD) {
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                if (ygsTrD + ygsTrY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsTr = new NetHesapla(ygsTrD, ygsTrY);
                    ygsTrN = ygsTr.getNet();
                    etYgsTrN.setText(String.valueOf(format.format(ygsTrN)));
                }

            } else
                ygsTrN = 0;


            boolean sosD = etYgsSosD.getText().toString().equals("");
            boolean sosY = etYgsSosY.getText().toString().equals("");
            boolean sosN = etYgsSosN.getText().toString().equals("");
            if (!sosD && sosY && sosN) {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                if (ygsSosD > 40) {
                    etYgsSosD.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsSosN = ygsSosD;
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else if (sosD && !sosY && sosN) {
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                if (ygsSosY > 40) {
                    etYgsSosY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsSos = new NetHesapla(0, ygsSosY);
                    ygsSosN = ygsSos.getNet();
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else if (sosD && sosY && !sosN) {
                ygsSosN = Double.parseDouble(etYgsSosN.getText().toString());
                if (ygsSosN > 40) {
                    etYgsSosN.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!sosD && !sosY && sosN) {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                if (ygsSosD + ygsSosY > 40) {
                    etYgsSosD.setText("");
                    etYgsSosY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                    ygsSosN = ygsSos.getNet();
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else if (!sosD && sosY) {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                if (ygsSosD > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsSosN = ygsSosD;
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else if (sosD && !sosY) {
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                if (ygsSosY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsSos = new NetHesapla(0, ygsSosY);
                    ygsSosN = ygsSos.getNet();
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else if (!sosD) {
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                if (ygsSosD + ygsSosY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsSos = new NetHesapla(ygsSosD, ygsSosY);
                    ygsSosN = ygsSos.getNet();
                    etYgsSosN.setText(String.valueOf(format.format(ygsSosN)));
                }
            } else
                ygsSosN = 0;

            boolean matD = etYgsMatD.getText().toString().equals("");
            boolean matY = etYgsMatY.getText().toString().equals("");
            boolean matN = etYgsMatN.getText().toString().equals("");
            if (!matD && matY && matN) {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                if (ygsMatD > 40) {
                    etYgsMatD.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsMatN = ygsMatD;
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else if (matD && !matY && matN) {
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                if (ygsMatY > 40) {
                    etYgsMatY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsMat = new NetHesapla(0, ygsMatY);
                    ygsMatN = ygsMat.getNet();
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else if (matD && matY && !matN) {
                ygsMatN = Double.parseDouble(etYgsMatN.getText().toString());
                if (ygsMatN > 40) {
                    etYgsMatN.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!matD && !matY && matN) {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                if (ygsMatD + ygsMatY > 40) {
                    etYgsMatD.setText("");
                    etYgsMatY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                    ygsMatN = ygsMat.getNet();
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else if (!matD && matY) {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                if (ygsMatD > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsMatN = ygsMatD;
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else if (matD && !matY) {
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                if (ygsMatY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsMat = new NetHesapla(0, ygsMatY);
                    ygsMatN = ygsMat.getNet();
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else if (!matD) {
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                if (ygsMatD + ygsMatY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsMat = new NetHesapla(ygsMatD, ygsMatY);
                    ygsMatN = ygsMat.getNet();
                    etYgsMatN.setText(String.valueOf(format.format(ygsMatN)));
                }
            } else
                ygsMatN = 0;

            boolean fenD = etYgsFenD.getText().toString().equals("");
            boolean fenY = etYgsFenY.getText().toString().equals("");
            boolean fenN = etYgsFenN.getText().toString().equals("");
            if (!fenD && fenY && fenN) {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                if (ygsFenD > 40) {
                    etYgsFenD.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsFenN = ygsFenD;
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }

            } else if (fenD && !fenY && fenN) {
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                if (ygsFenY > 40) {
                    etYgsFenY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsFen = new NetHesapla(0, ygsFenY);
                    ygsFenN = ygsFen.getNet();
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }
            } else if (fenD && fenY && !fenN) {
                ygsFenN = Double.parseDouble(etYgsFenN.getText().toString());
                if (ygsFenN > 40) {
                    etYgsFenN.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                }
            } else if (!fenD && !fenY && fenN) {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                if (ygsFenD + ygsFenY > 40) {
                    etYgsFenD.setText("");
                    etYgsFenY.setText("");
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                    ygsFenN = ygsFen.getNet();
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }

            } else if (!fenD && fenY) {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                if (ygsFenD > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    ygsFenN = ygsFenD;
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }

            } else if (fenD && !fenY) {
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                if (ygsFenY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsFen = new NetHesapla(0, ygsFenY);
                    ygsFenN = ygsFen.getNet();
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }

            } else if (!fenD) {
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                if (ygsFenD + ygsFenY > 40) {
                    String hata_mesaji = "Soru sayısı 40'ı geçemez.";
                    Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    NetHesapla ygsFen = new NetHesapla(ygsFenD, ygsFenY);
                    ygsFenN = ygsFen.getNet();
                    etYgsFenN.setText(String.valueOf(format.format(ygsFenN)));
                }

            } else
                ygsFenN = 0;


            ygsToplamYaz();

            ygsPuanGoster();

            ygsVeritabaniNetEkle();

            Log.d(LOG_TAG, "Ygs notları alınıp puanlar hesaplandı.");


        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());

            String hata_mesaji = "Alanları boş bırakmayın.";
            Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
        }
    }*/

    public void ygsToplamYaz() {
        boolean trD = etYgsTrD.getText().toString().equals("");
        boolean trY = etYgsTrY.getText().toString().equals("");
        boolean trN = etYgsTrN.getText().toString().equals("");
        boolean sosD = etYgsSosD.getText().toString().equals("");
        boolean sosY = etYgsSosY.getText().toString().equals("");
        boolean sosN = etYgsSosN.getText().toString().equals("");
        boolean matD = etYgsMatD.getText().toString().equals("");
        boolean matY = etYgsMatY.getText().toString().equals("");
        boolean matN = etYgsMatN.getText().toString().equals("");
        boolean fenD = etYgsFenD.getText().toString().equals("");
        boolean fenY = etYgsFenY.getText().toString().equals("");
        boolean fenN = etYgsFenN.getText().toString().equals("");

        if (trD)
            ygsTrD = 0;
        else
            ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
        if (trY)
            ygsTrY = 0;
        else
            ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
        if (trN)
            ygsTrN = 0;
        else
            ygsTrN = Double.parseDouble(etYgsTrN.getText().toString().replace(',', '.'));

        if (sosD)
            ygsSosD = 0;
        else
            ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
        if (sosY)
            ygsSosY = 0;
        else
            ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
        if (sosN)
            ygsSosN = 0;
        else
            ygsSosN = Double.parseDouble(etYgsSosN.getText().toString().replace(',', '.'));

        if (matD)
            ygsMatD = 0;
        else
            ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
        if (matY)
            ygsMatY = 0;
        else
            ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
        if (matN)
            ygsMatN = 0;
        else
            ygsMatN = Double.parseDouble(etYgsMatN.getText().toString().replace(',', '.'));

        if (fenD)
            ygsFenD = 0;
        else
            ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
        if (fenY)
            ygsFenY = 0;
        else
            ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
        if (fenN)
            ygsFenN = 0;
        else
            ygsFenN = Double.parseDouble(etYgsFenN.getText().toString().replace(',', '.'));

        tToplamD.setText(format.format(ygsTrD + ygsSosD + ygsMatD + ygsFenD));
        tToplamY.setText(format.format(ygsTrY + ygsSosY + ygsMatY + ygsFenY));
        tToplamN.setText(format.format(ygsTrN + ygsSosN + ygsMatN + ygsFenN));
    }

    public void ygsPuanGoster() {
        YgsPuanTuruHesaplama Ygs1 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs1 = Ygs1.getYgs1();
        tYgs1.setText(String.format("Ygs-1 : %.2f", ygs1));
        YgsPuanTuruHesaplama Ygs2 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs2 = Ygs2.getYgs2();
        tYgs2.setText(String.format("Ygs-2 : %.2f", ygs2));
        YgsPuanTuruHesaplama Ygs3 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs3 = Ygs3.getYgs3();
        tYgs3.setText(String.format("Ygs-3 : %.2f", ygs3));
        YgsPuanTuruHesaplama Ygs4 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs4 = Ygs4.getYgs4();
        tYgs4.setText(String.format("Ygs-4 : %.2f", ygs4));
        YgsPuanTuruHesaplama Ygs5 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs5 = Ygs5.getYgs5();
        tYgs5.setText(String.format("Ygs-5 : %.2f", ygs5));
        YgsPuanTuruHesaplama Ygs6 = new YgsPuanTuruHesaplama(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs6 = Ygs6.getYgs6();
        tYgs6.setText(String.format("Ygs-6 : %.2f", ygs6));
    }

    public void ygsVeritabaniNetEkle() {
        Log.d(LOG_TAG, "Ygs notları veri tabanına ekleniyor.");

        Cursor res = myDb.ygsNetleriAl();
        if (res.getCount() == 0) {
            myDb.ygsNetEkle("Türkçe", String.valueOf(ygsTrN));
            myDb.ygsNetEkle("Sosyal", String.valueOf(ygsSosN));
            myDb.ygsNetEkle("Matematik", String.valueOf(ygsMatN));
            myDb.ygsNetEkle("Fen", String.valueOf(ygsFenN));

        } else {
            myDb.ygsNetGuncelle("Türkçe", String.valueOf(ygsTrN));
            myDb.ygsNetGuncelle("Sosyal", String.valueOf(ygsSosN));
            myDb.ygsNetGuncelle("Matematik", String.valueOf(ygsMatN));
            myDb.ygsNetGuncelle("Fen", String.valueOf(ygsFenN));

        }
        Log.d(LOG_TAG, "Türkçe: " + ygsTrN + "\nSosyal: " + ygsSosN + "\nMatematik: " + ygsMatN + "\nFen: " + ygsFenN);
        Log.d(LOG_TAG, "Ygs notları veri tabanına eklendi.");
    }

    public void ygsTemizle() {
        Log.d(LOG_TAG, "Temizleme butonuna basıldı.");
        try {
            Cursor res = myDb.ygsNetleriAl();
            if (res.getCount() == 0) {
                myDb.ygsNetEkle("Türkçe", String.valueOf(0));
                myDb.ygsNetEkle("Sosyal", String.valueOf(0));
                myDb.ygsNetEkle("Matematik", String.valueOf(0));
                myDb.ygsNetEkle("Fen", String.valueOf(0));

            } else {
                myDb.ygsNetGuncelle("Türkçe", String.valueOf(0));
                myDb.ygsNetGuncelle("Sosyal", String.valueOf(0));
                myDb.ygsNetGuncelle("Matematik", String.valueOf(0));
                myDb.ygsNetGuncelle("Fen", String.valueOf(0));
            }
            ygsTrD = 0;
            ygsTrY = 0;
            ygsTrN = 0;
            ygsSosD = 0;
            ygsSosY = 0;
            ygsSosN = 0;
            ygsMatD = 0;
            ygsMatY = 0;
            ygsMatN = 0;
            ygsFenD = 0;
            ygsFenY = 0;
            ygsFenN = 0;
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
            tYgs1.setText(R.string.ygs1);
            tYgs2.setText(R.string.ygs2);
            tYgs3.setText(R.string.ygs3);
            tYgs4.setText(R.string.ygs4);
            tYgs5.setText(R.string.ygs5);
            tYgs6.setText(R.string.ygs6);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        Log.d(LOG_TAG, "Temizleme işlemi bitti.");
    }

    public void gitLys() {
        Intent i = new Intent(MainActivity.this, Lys.class);
        if (ygs1 < 180 && ygs2 < 180 && ygs3 < 180 && ygs4 < 180 && ygs5 < 180 && ygs6 < 180) {
            String hata_mesaji = "180 barajını geçemediniz. Lys'ye giremezsiniz.";
            Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();

            startActivity(i);
        } else {
            startActivity(i);
        }
    }

    public void ygsTrHataMesaji() {
        String hata_mesaji = "Ygs Türkçe toplam soru sayısı 40'tır.";
        Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
    }

    public void ygsSosHataMesaji() {
        String hata_mesaji = "Ygs Sosyal toplam soru sayısı 40'tır.";
        Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
    }

    public void ygsMatHataMesaji() {
        String hata_mesaji = "Ygs Matematik toplam soru sayısı 40'tır.";
        Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
    }

    public void ygsFenHataMesaji() {
        String hata_mesaji = "Ygs Fen toplam soru sayısı 40'tır.";
        Toast.makeText(MainActivity.this, hata_mesaji, Toast.LENGTH_LONG).show();
    }
}

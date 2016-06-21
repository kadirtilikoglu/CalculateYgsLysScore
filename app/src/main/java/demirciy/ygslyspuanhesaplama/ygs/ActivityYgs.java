package demirciy.ygslyspuanhesaplama.ygs;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;
import demirciy.ygslyspuanhesaplama.lys.ActivityLys;
import demirciy.ygslyspuanhesaplama.model.AllScores;
import demirciy.ygslyspuanhesaplama.model.CalculateMark;
import demirciy.ygslyspuanhesaplama.model.YgsCalculateScoreType;

//ilk açılan activtiy budur. ygs puanını hesaplar
public class ActivityYgs extends AppCompatActivity {

    private EditText etYgsTrD, etYgsTrY, etYgsTrN, etYgsSosD, etYgsSosY,
            etYgsSosN, etYgsMatD, etYgsMatY, etYgsMatN, etYgsFenD,
            etYgsFenY, etYgsFenN;
    private TextView tToplamD, tToplamY, tToplamN, tYgs1, tYgs2,
            tYgs3, tYgs4, tYgs5, tYgs6;
    private double ygsTrD, ygsTrY, ygsSosD, ygsSosY, ygsMatD,
            ygsMatY, ygsFenD, ygsFenY;
    public double ygsTrN, ygsSosN, ygsMatN, ygsFenN;
    private double ygs1, ygs2, ygs3, ygs4, ygs5, ygs6;

    private String examName, date;

    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "LogCat outputs -->";

    //veritabanının class ına erişmek için tanımlandı
    DatabaseHelper myDb;

    //action bar çağrılıyor
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ygs, menu);
        return true;
    }

    //action bar daki itemlerin tıklanma olayları
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.abLys:
                goLys();
                break;
            case R.id.abClear:
                ygsClear();
                break;
            case R.id.abSave:
                ygsAlertDialog();
                break;
            case R.id.abMyScores:
                goMyScores();
                break;
            case R.id.abWhatisYgsLys:
                goWhatisYgsLys();
                break;
            case R.id.abAbout:
                goAbout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ygs);

        Log.i(LOG_TAG, "App started.");

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

        myDb = new DatabaseHelper(this);

        //app den çıkmadan önceki ygs netlerini otomatik getiriyor
        previousInfo();

        //textwatcher yapısı başlangıç
        //textwatcher: edittext e girilen değerlere anında tepki verir
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
                            questionErrorMessage();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsTr = new CalculateMark(ygsTrD, ygsTrY);
                            ygsTrN = ygsTr.getMark();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsTr = new CalculateMark(0, ygsTrY);
                            ygsTrN = ygsTr.getMark();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }
                    } else {
                        ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());
                        ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());
                        if (ygsTrD + ygsTrY > 40) {
                            etYgsTrD.setText("");
                            etYgsTrY.setText("");
                            etYgsTrN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsTr = new CalculateMark(ygsTrD, ygsTrY);
                            ygsTrN = ygsTr.getMark();
                            etYgsTrN.setText(format.format(ygsTrN));
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                        questionErrorMessage();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsSos = new CalculateMark(ygsSosD, ygsSosY);
                            ygsSosN = ygsSos.getMark();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsSos = new CalculateMark(0, ygsSosY);
                            ygsSosN = ygsSos.getMark();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }

                    } else {
                        ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());
                        ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());
                        if (ygsSosD + ygsSosY > 40) {
                            etYgsSosD.setText("");
                            etYgsSosY.setText("");
                            etYgsSosN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsSos = new CalculateMark(ygsSosD, ygsSosY);
                            ygsSosN = ygsSos.getMark();
                            etYgsSosN.setText(format.format(ygsSosN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsMat = new CalculateMark(ygsMatD, ygsMatY);
                            ygsMatN = ygsMat.getMark();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsMat = new CalculateMark(0, ygsMatY);
                            ygsMatN = ygsMat.getMark();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }

                    } else {
                        ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());
                        ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());
                        if (ygsMatD + ygsMatY > 40) {
                            etYgsMatD.setText("");
                            etYgsMatY.setText("");
                            etYgsMatN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsMat = new CalculateMark(ygsMatD, ygsMatY);
                            ygsMatN = ygsMat.getMark();
                            etYgsMatN.setText(format.format(ygsMatN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsFen = new CalculateMark(ygsFenD, ygsFenY);
                            ygsFenN = ygsFen.getMark();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsFen = new CalculateMark(0, ygsFenY);
                            ygsFenN = ygsFen.getMark();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }

                    } else {
                        ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());
                        ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());
                        if (ygsFenD + ygsFenY > 40) {
                            etYgsFenD.setText("");
                            etYgsFenY.setText("");
                            etYgsFenN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark ygsFen = new CalculateMark(ygsFenD, ygsFenY);
                            ygsFenN = ygsFen.getMark();
                            etYgsFenN.setText(format.format(ygsFenN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
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
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                ygsPrintSum();
                ygsShowScore();
                ygsAddMarkDatabase();
            }
        });
        //textwatcher yapısı son
    }

    //her dersin doğru, yanlış ve net sayılarını alıp topluyor ve toplamını yazdırıyor
    public void ygsPrintSum() {

        Log.d(LOG_TAG, "Ygs / Total Correct, Incorrect, Mark printing.");

        try {
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

        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Printing sum failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs / Total Correct, Incorrect, Mark printed.");
    }

    //netleri YgsCalculateScoreType class ına gönderip ygs puan türlerini hesaplıyor
    public void ygsShowScore() {
        Log.d(LOG_TAG, "Ygs / Scores calculating.");

        try {
            YgsCalculateScoreType Ygs = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
            ygs1 = Ygs.getYgs1();
            tYgs1.setText(String.format("Ygs-1 : %.2f", ygs1));
            ygs2 = Ygs.getYgs2();
            tYgs2.setText(String.format("Ygs-2 : %.2f", ygs2));
            ygs3 = Ygs.getYgs3();
            tYgs3.setText(String.format("Ygs-3 : %.2f", ygs3));
            ygs4 = Ygs.getYgs4();
            tYgs4.setText(String.format("Ygs-4 : %.2f", ygs4));
            ygs5 = Ygs.getYgs5();
            tYgs5.setText(String.format("Ygs-5 : %.2f", ygs5));
            ygs6 = Ygs.getYgs6();
            tYgs6.setText(String.format("Ygs-6 : %.2f", ygs6));
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Showing score failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }
        Log.d(LOG_TAG, "Ygs / Scores calculated.");
    }

    //ygs derslerinin doğru, yanlış ve net sayılarını veritabanına kaydediyor.
    //bir sonraki açılışta previousInfo() metodu kullanılarak bu ygs verileri geri getiriliyor
    public void ygsAddMarkDatabase() {
        Log.d(LOG_TAG, "Ygs / Marks are adding into database.");

        try {
            Cursor res = myDb.ygsGetMark();

            //eğer dönen değer 0 ise bu tablo ilk defa oluşturulup değerler atılıyor
            if (res.getCount() == 0) {
                myDb.ygsAddMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
                myDb.ygsAddMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
                myDb.ygsAddMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
                myDb.ygsAddMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

                //dönen değer 0 değilse tablo daha önce oluşturulmuş demektir. tablo güncelleniyor
            } else {
                myDb.ygsUpdateMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
                myDb.ygsUpdateMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
                myDb.ygsUpdateMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
                myDb.ygsUpdateMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

            }
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Adding marks to database failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs / Marks added into database.");
    }

    //action bar da kaydet butonuna basınca ygs puanını kaydetmek için bu metod tetikleniyor ve bir alertdialog açılıyor
    //alertdialog da denemenin adı soruluyor eğer boş girilirse otomatikman denemenin adı adsız oluyor
    //kaydet butonuna basınca girilen ad ve o anki tarih alınıp veri tabanına gönderiliyor
    public void ygsAlertDialog() {
        Log.d(LOG_TAG, "Ygs / Saving exam alert dialog starting.");

        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            //alertdialog custom olduğu için layout dosyası çağırılıyor
            final View dialogView = inflater.inflate(R.layout.alertdialog_add_score_name, null);
            dialogBuilder.setView(dialogView);

            final EditText etExamName = (EditText) dialogView.findViewById(R.id.etExamName);
            dialogBuilder.setTitle("Ygs puan kaydet");
            dialogBuilder.setMessage("Sınav adı giriniz. Örn: Zambak Ygs denemesi");
            dialogBuilder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    boolean isNull = etExamName.getText().toString().equals("");
                    if (isNull) {
                        date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());
                        examName = "Adsız";
                        if (findSameExamName() == 0) {
                            ygsAddScoreDatabase();
                        } else {
                            ygsAlertDialog();
                        }
                    } else {
                        date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());
                        examName = etExamName.getText().toString();
                        if (findSameExamName() == 0) {
                            ygsAddScoreDatabase();
                        } else {
                            ygsAlertDialog();
                        }
                    }
                }
            });
            dialogBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Showing alert dialog failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }
        Log.d(LOG_TAG, "Ygs / Saving exam alert dialog started.");
    }

    //eğer girilen deneme adı daha önce veritabanına kaydedilmişse 1 değeri, değilse 0 değeri döndürülüyor
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
        Toast.makeText(ActivityYgs.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    //kaydedilmek istenen net ve puanlar allscores nesnesi oluşturulup içine atılıyor
    //myDb.addAllScore(allScores); metoduyla değerler veritabanı classına nesne içinde gönderiliyor
    //veritabanındaki addAllScore() metodu da bu verilere nesne yardımıyla erişip veritabanı tablosuna ekliyor
    public void ygsAddScoreDatabase() {
        Log.d(LOG_TAG, "Ygs / Scores are adding into database.");

        try {
            AllScores allScores = new AllScores();

            allScores.setExamName(examName);
            allScores.setExamDate(date);
            allScores.setTrMark(ygsTrN);
            allScores.setSocialMark(ygsSosN);
            allScores.setMath1Mark(ygsMatN);
            allScores.setScienceMark(ygsFenN);
            allScores.setYgs1(ygs1);
            allScores.setYgs2(ygs2);
            allScores.setYgs3(ygs3);
            allScores.setYgs4(ygs4);
            allScores.setYgs5(ygs5);
            allScores.setYgs6(ygs6);

            myDb.addAllScore(allScores);

            String infoMessage = "Ygs puanı kaydedildi.";
            Toast.makeText(ActivityYgs.this, infoMessage, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Adding score to database failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs / Scores added into database.");
    }

    //hesaplanan değerler temizlenir
    public void ygsClear() {
        Log.d(LOG_TAG, "Ygs / Numbers cleaning.");
        try {
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
            String msg = (e.getMessage() == null) ? "Cleaning numbers failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs / Numbers cleaned.");
    }

    //acitivityLys ye gider
    public void goLys() {
        Intent i = new Intent(ActivityYgs.this, ActivityLys.class);
        if (ygs1 < 180 && ygs2 < 180 && ygs3 < 180 && ygs4 < 180 && ygs5 < 180 && ygs6 < 180) {
            String errorMessage = "180 barajını geçemediniz. Lys'ye giremezsiniz.";
            Toast.makeText(ActivityYgs.this, errorMessage, Toast.LENGTH_SHORT).show();
        }

        //lys puanı hesaplanırken ygs netleri de kullanılıyor
        //bu metod lys activity sine gitmeden kaybolmasın diye girilen ygs netlerini veritabanına kaydediyor
        //daha sonra lys puanı hesaplanırken bu netler veri tabanından çekilip hesaba katılıyor
        ygsDatasForLys();

        startActivity(i);
    }

    public void questionErrorMessage() {
        String errorMessage = "Soru sayısından fazla doğru ve yanlış sayısı girdiniz.";
        Toast.makeText(ActivityYgs.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    //son girilen ygs netlerini getirir
    public void previousInfo() {
        Log.d(LOG_TAG, "Ygs / Previous datas bringing.");

        try {
            Cursor res = myDb.ygsGetMark();

            String[] corrects = new String[4];
            String[] incorrects = new String[4];
            String[] marks = new String[4];
            int c = 0, i = 0, m = 0;

            while (res.moveToNext()) {
                corrects[c] = res.getString(1);
                incorrects[i] = res.getString(2);
                marks[m] = res.getString(3);
                c++;
                i++;
                m++;
            }

            ygsTrD = Double.parseDouble(corrects[0]);
            ygsTrY = Double.parseDouble(incorrects[0]);
            ygsTrN = Double.parseDouble(marks[0]);
            etYgsTrD.setText(format.format(ygsTrD));
            etYgsTrY.setText(format.format(ygsTrY));
            etYgsTrN.setText(format.format(ygsTrN));

            ygsSosD = Double.parseDouble(corrects[1]);
            ygsSosY = Double.parseDouble(incorrects[1]);
            ygsSosN = Double.parseDouble(marks[1]);
            etYgsSosD.setText(format.format(ygsSosD));
            etYgsSosY.setText(format.format(ygsSosY));
            etYgsSosN.setText(format.format(ygsSosN));

            ygsMatD = Double.parseDouble(corrects[2]);
            ygsMatY = Double.parseDouble(incorrects[2]);
            ygsMatN = Double.parseDouble(marks[2]);
            etYgsMatD.setText(format.format(ygsMatD));
            etYgsMatY.setText(format.format(ygsMatY));
            etYgsMatN.setText(format.format(ygsMatN));

            ygsFenD = Double.parseDouble(corrects[3]);
            ygsFenY = Double.parseDouble(incorrects[3]);
            ygsFenN = Double.parseDouble(marks[3]);
            etYgsFenD.setText(format.format(ygsFenD));
            etYgsFenY.setText(format.format(ygsFenY));
            etYgsFenN.setText(format.format(ygsFenN));

            ygsShowScore();
            ygsPrintSum();
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Bringing previous info failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs / Previous datas brought.");
    }

    //lys activity sine geçmeden ygs netlerini veritabanına kaydediyor
    public void ygsDatasForLys() {
        Log.d(LOG_TAG, "Ygs datas are adding into database for Lys.");

        try {
            Cursor res = myDb.getYgsDatasForLys();

            //0 dönerse tablo yeni oluşturuluyor
            if (res.getCount() == 0) {
                AllScores allScores = new AllScores();

                allScores.setTrMark(ygsTrN);
                allScores.setSocialMark(ygsSosN);
                allScores.setMath1Mark(ygsMatN);
                allScores.setScienceMark(ygsFenN);
                allScores.setYgs1(ygs1);
                allScores.setYgs2(ygs2);
                allScores.setYgs3(ygs3);
                allScores.setYgs4(ygs4);
                allScores.setYgs5(ygs5);
                allScores.setYgs6(ygs6);

                myDb.addYgsDatasForLys(allScores);

                //dönmezse tablo var demektir. tablo güncelleniyor
            } else {
                AllScores allScores = new AllScores();

                allScores.setTrMark(ygsTrN);
                allScores.setSocialMark(ygsSosN);
                allScores.setMath1Mark(ygsMatN);
                allScores.setScienceMark(ygsFenN);
                allScores.setYgs1(ygs1);
                allScores.setYgs2(ygs2);
                allScores.setYgs3(ygs3);
                allScores.setYgs4(ygs4);
                allScores.setYgs5(ygs5);
                allScores.setYgs6(ygs6);

                myDb.updateYgsDatasForLys(allScores);

            }
        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Adding ygs datas into database for lys failed!" : e.getMessage();
            Log.e(LOG_TAG, msg);
        }

        Log.d(LOG_TAG, "Ygs datas added into database for Lys.");
    }

    public void goMyScores() {
        Intent i = new Intent(ActivityYgs.this, ActivityMyScores.class);
        startActivity(i);
    }

    public void goWhatisYgsLys() {
        Intent i = new Intent(this, ActivityWhatIsYgsLys.class);
        startActivity(i);
    }

    public void goAbout() {
        Intent i = new Intent(this, ActivityAbout.class);
        startActivity(i);
    }

}

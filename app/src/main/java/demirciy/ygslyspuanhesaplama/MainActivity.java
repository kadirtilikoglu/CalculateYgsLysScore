package demirciy.ygslyspuanhesaplama;

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
import java.util.Date;

import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;
import demirciy.ygslyspuanhesaplama.model.YgsScore;

public class MainActivity extends AppCompatActivity {

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

    DatabaseHelper myDb;

    //başlagıçta ekrana bir bildiri gönder ve sayısal yeşil, eşit ağırlık mor, sözel açık sarı yazdır
    //iconu ayarla

    //puanlarımda puanlarını güzel bi şekilde göster ki ss alınabilsin

    //Listview örnek yap- SONRA CUSTOM LISTVView yap - sonra expandable listview yap


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ygs, menu);
        return true;
    }

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
            case R.id.abYgsMyScores:
                goYgsMyScores();
                break;
            case R.id.abWhatisYgsLys:
                break;
            case R.id.abAbout:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("");
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

        previousInfo();

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

    }

    public void ygsPrintSum() {
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

    public void ygsShowScore() {
        YgsCalculateScoreType Ygs1 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs1 = Ygs1.getYgs1();
        tYgs1.setText(String.format("Ygs-1 : %.2f", ygs1));
        YgsCalculateScoreType Ygs2 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs2 = Ygs2.getYgs2();
        tYgs2.setText(String.format("Ygs-2 : %.2f", ygs2));
        YgsCalculateScoreType Ygs3 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs3 = Ygs3.getYgs3();
        tYgs3.setText(String.format("Ygs-3 : %.2f", ygs3));
        YgsCalculateScoreType Ygs4 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs4 = Ygs4.getYgs4();
        tYgs4.setText(String.format("Ygs-4 : %.2f", ygs4));
        YgsCalculateScoreType Ygs5 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs5 = Ygs5.getYgs5();
        tYgs5.setText(String.format("Ygs-5 : %.2f", ygs5));
        YgsCalculateScoreType Ygs6 = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);
        ygs6 = Ygs6.getYgs6();
        tYgs6.setText(String.format("Ygs-6 : %.2f", ygs6));
    }

    public void ygsAddMarkDatabase() {
        Log.d(LOG_TAG, "Ygs marks are adding into database.");

        Cursor res = myDb.ygsGetMark();
        if (res.getCount() == 0) {
            myDb.ygsAddMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
            myDb.ygsAddMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
            myDb.ygsAddMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
            myDb.ygsAddMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

        } else {
            myDb.ygsUpdateMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
            myDb.ygsUpdateMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
            myDb.ygsUpdateMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
            myDb.ygsUpdateMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

        }
        Log.d(LOG_TAG, "Türkçe: " + ygsTrD + "-" + ygsTrY + "-" + ygsTrN +
                "\nSosyal: " + ygsSosD + "-" + ygsTrY + "-" + ygsSosN +
                "\nMatematik: " + ygsMatD + "-" + ygsMatY + "-" + ygsMatN +
                "\nFen: " + ygsFenD + "-" + ygsFenY + "-" + ygsFenN);
        Log.d(LOG_TAG, "Ygs marks added into database.");
    }

    public void ygsAlertDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_ygs_add_score_name, null);
        dialogBuilder.setView(dialogView);

        final EditText etYgsExamName = (EditText) dialogView.findViewById(R.id.etYgsExamName);
        dialogBuilder.setTitle("YGS puan kaydet");
        dialogBuilder.setMessage("Sınav adı giriniz. Örn: Zambak denemesi");
        dialogBuilder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean isNull = etYgsExamName.getText().toString().equals("");
                if (isNull) {
                    date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());
                    examName = "Adsız";
                    ygsAddScoreDatabase();
                } else {
                    date = new SimpleDateFormat("d-MMM-yyyy").format(new Date());
                    examName = etYgsExamName.getText().toString();
                    ygsAddScoreDatabase();
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
    }

    public void ygsAddScoreDatabase() {
        Log.d(LOG_TAG, "Ygs scores are adding into database.");

//            myDb.ygsAddScore(date, examName, String.valueOf(ygsTrN), "YGS1", String.valueOf(ygs1));
//            myDb.ygsAddScore(date, examName, String.valueOf(ygsSosN), "YGS2", String.valueOf(ygs2));
//            myDb.ygsAddScore(date, examName, String.valueOf(ygsMatN), "YGS3", String.valueOf(ygs3));
//            myDb.ygsAddScore(date, examName, String.valueOf(ygsFenN), "YGS4", String.valueOf(ygs4));
//            myDb.ygsAddScore(date, examName, "0", "YGS5", String.valueOf(ygs5));
//            myDb.ygsAddScore(date, examName, "0", "YGS6", String.valueOf(ygs6));

        YgsScore ygsScore1 = new YgsScore();
        ygsScore1.setsDate(date);
        ygsScore1.setsExamName(examName);
        ygsScore1.setsMarks(ygsTrN);
        ygsScore1.setsScoreType("YGS1");
        ygsScore1.setsScore(ygs1);

        YgsScore ygsScore2 = new YgsScore();
        ygsScore2.setsDate(date);
        ygsScore2.setsExamName(examName);
        ygsScore2.setsMarks(ygsSosN);
        ygsScore2.setsScoreType("YGS2");
        ygsScore2.setsScore(ygs2);

        YgsScore ygsScore3 = new YgsScore();
        ygsScore3.setsDate(date);
        ygsScore3.setsExamName(examName);
        ygsScore3.setsMarks(ygsMatN);
        ygsScore3.setsScoreType("YGS3");
        ygsScore3.setsScore(ygs3);

        YgsScore ygsScore4 = new YgsScore();
        ygsScore4.setsDate(date);
        ygsScore4.setsExamName(examName);
        ygsScore4.setsMarks(ygsFenN);
        ygsScore4.setsScoreType("YGS4");
        ygsScore4.setsScore(ygs4);

        YgsScore ygsScore5 = new YgsScore();
        ygsScore5.setsDate(date);
        ygsScore5.setsExamName(examName);
        ygsScore5.setsMarks(0.0);
        ygsScore5.setsScoreType("YGS5");
        ygsScore5.setsScore(ygs5);

        YgsScore ygsScore6 = new YgsScore();
        ygsScore6.setsDate(date);
        ygsScore6.setsExamName(examName);
        ygsScore6.setsMarks(0.0);
        ygsScore6.setsScoreType("YGS6");
        ygsScore6.setsScore(ygs6);

        myDb.ygsAddScore(ygsScore1);
        myDb.ygsAddScore(ygsScore2);
        myDb.ygsAddScore(ygsScore3);
        myDb.ygsAddScore(ygsScore4);
        myDb.ygsAddScore(ygsScore5);
        myDb.ygsAddScore(ygsScore6);

        String errorMessage = "YGS puanı kaydedildi.";
        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

        Log.d(LOG_TAG, "YGS1: " + ygs1 + "\nYGS2: " + ygs2 + "\nYGS3: " + ygs3 + "\nYGS4: " + ygs4
                + "\nYGS5: " + ygs5 + "\nYGS6: " + ygs6);

        Log.d(LOG_TAG, "Ygs scores added into database.");
    }

    public void ygsClear() {
        Log.d(LOG_TAG, "Pressed clear button.");
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
            setNullText(etYgsTrD);
            setNullText(etYgsTrY);
            setNullText(etYgsTrN);
            setNullText(etYgsSosD);
            setNullText(etYgsSosY);
            setNullText(etYgsSosN);
            setNullText(etYgsMatD);
            setNullText(etYgsMatY);
            setNullText(etYgsMatN);
            setNullText(etYgsFenD);
            setNullText(etYgsFenY);
            setNullText(etYgsFenN);
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

        Log.d(LOG_TAG, "Clear is done.");
    }

    public void goLys() {
        Intent i = new Intent(MainActivity.this, Lys.class);
        if (ygs1 < 180 && ygs2 < 180 && ygs3 < 180 && ygs4 < 180 && ygs5 < 180 && ygs6 < 180) {
            String errorMessage = "180 barajını geçemediniz. Lys'ye giremezsiniz.";
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            startActivity(i);
        } else {
            startActivity(i);
        }
    }

    public void questionErrorMessage() {
        String errorMessage = "Soru sayısından fazla doğru ve yanlış sayısı girdiniz.";
        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void previousInfo() {
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

        }

    }

    public void goYgsMyScores()
    {
        Intent i = new Intent(MainActivity.this, YgsMyScores.class);
        startActivity(i);
    }
    public void setNullText(EditText et){
        et.setText("");
    }
}

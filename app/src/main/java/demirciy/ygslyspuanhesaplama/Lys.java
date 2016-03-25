package demirciy.ygslyspuanhesaplama;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Lys extends AppCompatActivity {

    public EditText etLysMatD, etLysMatY, etLysMatN, etLysGeoD, etLysGeoY,
            etLysGeoN, etLysFizikD, etLysFizikY, etLysFizikN, etLysKimyaD,
            etLysKimyaY, etLysKimyaN, etLysBiyoD, etLysBiyoY, etLysBiyoN,
            etLysEdeD, etLysEdeY, etLysEdeN, etLysCog1D, etLysCog1Y, etLysCog1N,
            etLysTarihD, etLysTarihY, etLysTarihN, etLysCog2D, etLysCog2Y, etLysCog2N,
            etLysFelD, etLysFelY, etLysFelN, etLysYdilD, etLysYdilY, etLysYdilN, etDiploma;
    private TextView tMf1, tMf2, tMf3, tMf4, tTm1, tTm2,
            tTm3, tTs1, tTs2, tYdil1, tYdil2, tYdil3;

    private double lysMatN, lysGeoN, lysFizikN, lysKimyaN, lysBiyoN, lysEdeN, lysCog1N, lysTarihN,
            lysCog2N, lysFelN, lysYdilN, OBP, lysMatD, lysMatY, lysGeoD, lysGeoY, lysFizikD, lysFizikY,
            lysKimyaD, lysKimyaY, lysBiyoD, lysBiyoY, lysEdeD, lysEdeY, lysCog1D, lysCog1Y, lysTarihD,
            lysTarihY, lysCog2D, lysCog2Y, lysFelD, lysFelY, lysYdilD, lysYdilY;


    //ygs puanlarını kaydederken lys puan türleri için 0 değerini gönder.
    //ygs ve lys notları ayrı ayrı kaydedilebilsin.


    DecimalFormat format = new DecimalFormat("#.##");

    final String LOG_TAG = "LogCat outputs -->";

    DatabaseHelper myDb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lys, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.abClear:
                lysClear();
                break;
            case R.id.abSave:
//                ygsAlertDialog();
                break;
            case R.id.abLysMyScores:
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

        myDb = new DatabaseHelper(this);

        lysShowScore();

        etLysMatD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean matY = etLysMatY.getText().toString().equals("");
                    if (matY) {
                        lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                        if (lysMatD > 50) {
                            etLysMatD.setText("");
                            etLysMatN.setText("");
                            questionErrorMessage();
                        } else {
                            lysMatN = lysMatD;
                            etLysMatN.setText(format.format(lysMatN));
                        }
                    } else {
                        lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                        lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                        if (lysMatD + lysMatY > 50) {
                            etLysMatD.setText("");
                            etLysMatY.setText("");
                            etLysMatN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysMat = new CalculateMark(lysMatD, lysMatY);
                            lysMatN = lysMat.getMark();
                            etLysMatN.setText(format.format(lysMatN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysMatY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean matD = etLysMatD.getText().toString().equals("");

                    if (matD) {
                        lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                        if (lysMatY > 50) {
                            etLysMatY.setText("");
                            etLysMatN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysMat = new CalculateMark(0, lysMatY);
                            lysMatN = lysMat.getMark();
                            etLysMatN.setText(format.format(lysMatN));
                        }

                    } else {
                        lysMatD = Double.parseDouble(etLysMatD.getText().toString());
                        lysMatY = Double.parseDouble(etLysMatY.getText().toString());
                        if (lysMatD + lysMatY > 50) {
                            etLysMatD.setText("");
                            etLysMatY.setText("");
                            etLysMatN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysMat = new CalculateMark(lysMatD, lysMatY);
                            lysMatN = lysMat.getMark();
                            etLysMatN.setText(format.format(lysMatN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysMatN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysMatN = Double.parseDouble(etLysMatN.getText().toString());
                    if (lysMatN > 50) {
                        etLysMatN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysGeoD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean geoY = etLysGeoY.getText().toString().equals("");
                    if (geoY) {
                        lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                        if (lysGeoD > 30) {
                            etLysGeoD.setText("");
                            etLysGeoN.setText("");
                            questionErrorMessage();
                        } else {
                            lysGeoN = lysGeoD;
                            etLysGeoN.setText(format.format(lysGeoN));
                        }
                    } else {
                        lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                        lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                        if (lysGeoD + lysGeoY > 30) {
                            etLysGeoD.setText("");
                            etLysGeoY.setText("");
                            etLysGeoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysGeo = new CalculateMark(lysGeoD, lysGeoY);
                            lysGeoN = lysGeo.getMark();
                            etLysGeoN.setText(format.format(lysGeoN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysGeoY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean geoD = etLysGeoD.getText().toString().equals("");

                    if (geoD) {
                        lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                        if (lysGeoY > 30) {
                            etLysGeoY.setText("");
                            etLysGeoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysGeo = new CalculateMark(0, lysGeoY);
                            lysGeoN = lysGeo.getMark();
                            etLysGeoN.setText(format.format(lysGeoN));
                        }

                    } else {
                        lysGeoD = Double.parseDouble(etLysGeoD.getText().toString());
                        lysGeoY = Double.parseDouble(etLysGeoY.getText().toString());
                        if (lysGeoD + lysGeoY > 30) {
                            etLysGeoD.setText("");
                            etLysGeoY.setText("");
                            etLysGeoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysGeo = new CalculateMark(lysGeoD, lysGeoY);
                            lysGeoN = lysGeo.getMark();
                            etLysGeoN.setText(format.format(lysGeoN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysGeoN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysGeoN = Double.parseDouble(etLysGeoN.getText().toString());
                    if (lysGeoN > 30) {
                        etLysGeoN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysFizikD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean fizikY = etLysFizikY.getText().toString().equals("");
                    if (fizikY) {
                        lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                        if (lysFizikD > 30) {
                            etLysFizikD.setText("");
                            etLysFizikN.setText("");
                            questionErrorMessage();
                        } else {
                            lysFizikN = lysFizikD;
                            etLysFizikN.setText(format.format(lysFizikN));
                        }
                    } else {
                        lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                        lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                        if (lysFizikD + lysFizikY > 30) {
                            etLysFizikD.setText("");
                            etLysFizikY.setText("");
                            etLysFizikN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFizik = new CalculateMark(lysFizikD, lysFizikY);
                            lysFizikN = lysFizik.getMark();
                            etLysFizikN.setText(format.format(lysFizikN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysFizikY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean fizikD = etLysFizikD.getText().toString().equals("");

                    if (fizikD) {
                        lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                        if (lysFizikY > 30) {
                            etLysFizikY.setText("");
                            etLysFizikN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFizik = new CalculateMark(0, lysFizikY);
                            lysFizikN = lysFizik.getMark();
                            etLysFizikN.setText(format.format(lysFizikN));
                        }

                    } else {
                        lysFizikD = Double.parseDouble(etLysFizikD.getText().toString());
                        lysFizikY = Double.parseDouble(etLysFizikY.getText().toString());
                        if (lysFizikD + lysFizikY > 30) {
                            etLysFizikD.setText("");
                            etLysFizikY.setText("");
                            etLysFizikN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFizik = new CalculateMark(lysFizikD, lysFizikY);
                            lysFizikN = lysFizik.getMark();
                            etLysFizikN.setText(format.format(lysFizikN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysFizikN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysFizikN = Double.parseDouble(etLysFizikN.getText().toString());
                    if (lysFizikN > 30) {
                        etLysFizikN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysKimyaD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean kimyaY = etLysKimyaY.getText().toString().equals("");
                    if (kimyaY) {
                        lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                        if (lysKimyaD > 30) {
                            etLysKimyaD.setText("");
                            etLysKimyaN.setText("");
                            questionErrorMessage();
                        } else {
                            lysKimyaN = lysKimyaD;
                            etLysKimyaN.setText(format.format(lysKimyaN));
                        }
                    } else {
                        lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                        lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                        if (lysKimyaD + lysKimyaY > 30) {
                            etLysKimyaD.setText("");
                            etLysKimyaY.setText("");
                            etLysKimyaN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysKimya = new CalculateMark(lysKimyaD, lysKimyaY);
                            lysKimyaN = lysKimya.getMark();
                            etLysKimyaN.setText(format.format(lysKimyaN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysKimyaY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean kimyaD = etLysKimyaD.getText().toString().equals("");

                    if (kimyaD) {
                        lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                        if (lysKimyaY > 30) {
                            etLysKimyaY.setText("");
                            etLysKimyaN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysKimya = new CalculateMark(0, lysKimyaY);
                            lysKimyaN = lysKimya.getMark();
                            etLysKimyaN.setText(format.format(lysKimyaN));
                        }

                    } else {
                        lysKimyaD = Double.parseDouble(etLysKimyaD.getText().toString());
                        lysKimyaY = Double.parseDouble(etLysKimyaY.getText().toString());
                        if (lysKimyaD + lysKimyaY > 30) {
                            etLysKimyaD.setText("");
                            etLysKimyaY.setText("");
                            etLysKimyaN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysKimya = new CalculateMark(lysKimyaD, lysKimyaY);
                            lysKimyaN = lysKimya.getMark();
                            etLysKimyaN.setText(format.format(lysKimyaN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysKimyaN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysKimyaN = Double.parseDouble(etLysKimyaN.getText().toString());
                    if (lysKimyaN > 30) {
                        etLysKimyaN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysBiyoD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean biyoY = etLysBiyoY.getText().toString().equals("");
                    if (biyoY) {
                        lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                        if (lysBiyoD > 30) {
                            etLysBiyoD.setText("");
                            etLysBiyoN.setText("");
                            questionErrorMessage();
                        } else {
                            lysBiyoN = lysBiyoD;
                            etLysBiyoN.setText(format.format(lysBiyoN));
                        }
                    } else {
                        lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                        lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                        if (lysBiyoD + lysBiyoY > 30) {
                            etLysBiyoD.setText("");
                            etLysBiyoY.setText("");
                            etLysBiyoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysBiyo = new CalculateMark(lysBiyoD, lysBiyoY);
                            lysBiyoN = lysBiyo.getMark();
                            etLysBiyoN.setText(format.format(lysBiyoN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysBiyoY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean biyoD = etLysBiyoD.getText().toString().equals("");

                    if (biyoD) {
                        lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                        if (lysBiyoY > 30) {
                            etLysBiyoY.setText("");
                            etLysBiyoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysBiyo = new CalculateMark(0, lysBiyoY);
                            lysBiyoN = lysBiyo.getMark();
                            etLysBiyoN.setText(format.format(lysBiyoN));
                        }

                    } else {
                        lysBiyoD = Double.parseDouble(etLysBiyoD.getText().toString());
                        lysBiyoY = Double.parseDouble(etLysBiyoY.getText().toString());
                        if (lysBiyoD + lysBiyoY > 30) {
                            etLysBiyoD.setText("");
                            etLysBiyoY.setText("");
                            etLysBiyoN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysBiyo = new CalculateMark(lysBiyoD, lysBiyoY);
                            lysBiyoN = lysBiyo.getMark();
                            etLysBiyoN.setText(format.format(lysBiyoN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysBiyoN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysBiyoN = Double.parseDouble(etLysBiyoN.getText().toString());
                    if (lysBiyoN > 40) {
                        etLysBiyoN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysEdeD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean edeY = etLysEdeY.getText().toString().equals("");
                    if (edeY) {
                        lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                        if (lysEdeD > 56) {
                            etLysEdeD.setText("");
                            etLysEdeN.setText("");
                            questionErrorMessage();
                        } else {
                            lysEdeN = lysEdeD;
                            etLysEdeN.setText(format.format(lysEdeN));
                        }
                    } else {
                        lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                        lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                        if (lysEdeD + lysEdeY > 56) {
                            etLysEdeD.setText("");
                            etLysEdeY.setText("");
                            etLysEdeN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysEde = new CalculateMark(lysEdeD, lysEdeY);
                            lysEdeN = lysEde.getMark();
                            etLysEdeN.setText(format.format(lysEdeN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysEdeY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean edeD = etLysEdeD.getText().toString().equals("");

                    if (edeD) {
                        lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                        if (lysEdeY > 56) {
                            etLysEdeY.setText("");
                            etLysEdeN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysEde = new CalculateMark(0, lysEdeY);
                            lysEdeN = lysEde.getMark();
                            etLysEdeN.setText(format.format(lysEdeN));
                        }

                    } else {
                        lysEdeD = Double.parseDouble(etLysEdeD.getText().toString());
                        lysEdeY = Double.parseDouble(etLysEdeY.getText().toString());
                        if (lysEdeD + lysEdeY > 56) {
                            etLysEdeD.setText("");
                            etLysEdeY.setText("");
                            etLysEdeN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysEde = new CalculateMark(lysEdeD, lysEdeY);
                            lysEdeN = lysEde.getMark();
                            etLysEdeN.setText(format.format(lysEdeN));
                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysEdeN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    lysEdeN = Double.parseDouble(etLysEdeN.getText().toString());
                    if (lysEdeN > 56) {
                        etLysEdeN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysCog1D.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean cog1Y = etLysCog1Y.getText().toString().equals("");
                    if (cog1Y) {
                        lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                        if (lysCog1D > 24) {
                            etLysCog1D.setText("");
                            etLysCog1N.setText("");
                            questionErrorMessage();
                        } else {
                            lysCog1N = lysCog1D;
                            etLysCog1N.setText(format.format(lysCog1N));
                        }
                    } else {
                        lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                        lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                        if (lysCog1D + lysCog1Y > 24) {
                            etLysCog1D.setText("");
                            etLysCog1Y.setText("");
                            etLysCog1N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog1 = new CalculateMark(lysCog1D, lysCog1Y);
                            lysCog1N = lysCog1.getMark();
                            etLysCog1N.setText(format.format(lysCog1N));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysCog1Y.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean cog1D = etLysCog1D.getText().toString().equals("");

                    if (cog1D) {
                        lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                        if (lysCog1Y > 24) {
                            etLysCog1Y.setText("");
                            etLysCog1N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog1 = new CalculateMark(0, lysCog1Y);
                            lysCog1N = lysCog1.getMark();
                            etLysCog1N.setText(format.format(lysCog1N));
                        }

                    } else {
                        lysCog1D = Double.parseDouble(etLysCog1D.getText().toString());
                        lysCog1Y = Double.parseDouble(etLysCog1Y.getText().toString());
                        if (lysCog1D + lysCog1Y > 24) {
                            etLysCog1D.setText("");
                            etLysCog1Y.setText("");
                            etLysCog1N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog1 = new CalculateMark(lysCog1D, lysCog1Y);
                            lysCog1N = lysCog1.getMark();
                            etLysCog1N.setText(format.format(lysCog1N));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysCog1N.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {

                    lysCog1N = Double.parseDouble(etLysCog1N.getText().toString());
                    if (lysCog1N > 24) {
                        etLysCog1N.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysTarihD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean tarihY = etLysTarihY.getText().toString().equals("");
                    if (tarihY) {
                        lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                        if (lysTarihD > 44) {
                            etLysTarihD.setText("");
                            etLysTarihN.setText("");
                            questionErrorMessage();
                        } else {
                            lysTarihN = lysTarihD;
                            etLysTarihN.setText(format.format(lysTarihN));
                        }
                    } else {
                        lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                        lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                        if (lysTarihD + lysTarihY > 44) {
                            etLysTarihD.setText("");
                            etLysTarihY.setText("");
                            etLysTarihN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysTarih = new CalculateMark(lysTarihD, lysTarihY);
                            lysTarihN = lysTarih.getMark();
                            etLysTarihN.setText(format.format(lysTarihN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysTarihY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean tarihD = etLysTarihD.getText().toString().equals("");

                    if (tarihD) {
                        lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                        if (lysTarihY > 44) {
                            etLysTarihY.setText("");
                            etLysTarihN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysTarih = new CalculateMark(0, lysTarihY);
                            lysTarihN = lysTarih.getMark();
                            etLysTarihN.setText(format.format(lysTarihN));
                        }

                    } else {
                        lysTarihD = Double.parseDouble(etLysTarihD.getText().toString());
                        lysTarihY = Double.parseDouble(etLysTarihY.getText().toString());
                        if (lysTarihD + lysTarihY > 44) {
                            etLysTarihD.setText("");
                            etLysTarihY.setText("");
                            etLysTarihN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysTarih = new CalculateMark(lysTarihD, lysTarihY);
                            lysTarihN = lysTarih.getMark();
                            etLysTarihN.setText(format.format(lysTarihN));
                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysTarihN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    lysTarihN = Double.parseDouble(etLysTarihN.getText().toString());
                    if (lysTarihN > 44) {
                        etLysTarihN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysCog2D.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean cog2Y = etLysCog2Y.getText().toString().equals("");
                    if (cog2Y) {
                        lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                        if (lysCog2D > 14) {
                            etLysCog2D.setText("");
                            etLysCog2N.setText("");
                            questionErrorMessage();
                        } else {
                            lysCog2N = lysCog2D;
                            etLysCog2N.setText(format.format(lysCog2N));
                        }
                    } else {
                        lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                        lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                        if (lysCog2D + lysCog2Y > 14) {
                            etLysCog2D.setText("");
                            etLysCog2Y.setText("");
                            etLysCog2N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog2 = new CalculateMark(lysCog2D, lysCog2Y);
                            lysCog2N = lysCog2.getMark();
                            etLysCog2N.setText(format.format(lysCog2N));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysCog2Y.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean cog2D = etLysCog2D.getText().toString().equals("");

                    if (cog2D) {
                        lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                        if (lysCog2Y > 14) {
                            etLysCog2Y.setText("");
                            etLysCog2N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog2 = new CalculateMark(0, lysCog2Y);
                            lysCog2N = lysCog2.getMark();
                            etLysCog2N.setText(format.format(lysCog2N));
                        }

                    } else {
                        lysCog2D = Double.parseDouble(etLysCog2D.getText().toString());
                        lysCog2Y = Double.parseDouble(etLysCog2Y.getText().toString());
                        if (lysCog2D + lysCog2Y > 14) {
                            etLysCog2D.setText("");
                            etLysCog2Y.setText("");
                            etLysCog2N.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysCog2 = new CalculateMark(lysCog2D, lysCog2Y);
                            lysCog2N = lysCog2.getMark();
                            etLysCog2N.setText(format.format(lysCog2N));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysCog2N.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    lysCog2N = Double.parseDouble(etLysCog2N.getText().toString());
                    if (lysCog2N > 14) {
                        etLysCog2N.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysFelD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean felY = etLysFelY.getText().toString().equals("");
                    if (felY) {
                        lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                        if (lysFelD > 32) {
                            etLysFelD.setText("");
                            etLysFelN.setText("");
                            questionErrorMessage();
                        } else {
                            lysFelN = lysFelD;
                            etLysFelN.setText(format.format(lysFelN));
                        }
                    } else {
                        lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                        lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                        if (lysFelD + lysFelY > 32) {
                            etLysFelD.setText("");
                            etLysFelY.setText("");
                            etLysFelN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFel = new CalculateMark(lysFelD, lysFelY);
                            lysFelN = lysFel.getMark();
                            etLysFelN.setText(format.format(lysFelN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysFelY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean felD = etLysFelD.getText().toString().equals("");

                    if (felD) {
                        lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                        if (lysFelY > 32) {
                            etLysFelY.setText("");
                            etLysFelN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFel = new CalculateMark(0, lysFelY);
                            lysFelN = lysFel.getMark();
                            etLysFelN.setText(format.format(lysFelN));
                        }

                    } else {
                        lysFelD = Double.parseDouble(etLysFelD.getText().toString());
                        lysFelY = Double.parseDouble(etLysFelY.getText().toString());
                        if (lysFelD + lysFelY > 32) {
                            etLysFelD.setText("");
                            etLysFelY.setText("");
                            etLysFelN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysFel = new CalculateMark(lysFelD, lysFelY);
                            lysFelN = lysFel.getMark();
                            etLysFelN.setText(format.format(lysFelN));
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                lysShowScore();

            }
        });
        etLysFelN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    lysFelN = Double.parseDouble(etLysFelN.getText().toString());
                    if (lysFelN > 32) {
                        etLysFelN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etLysYdilD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean ydilY = etLysYdilY.getText().toString().equals("");
                    if (ydilY) {
                        lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                        if (lysYdilD > 40) {
                            etLysYdilD.setText("");
                            etLysYdilN.setText("");
                            questionErrorMessage();
                        } else {
                            lysYdilN = lysYdilD;
                            etLysYdilN.setText(format.format(lysYdilN));
                        }
                    } else {
                        lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                        lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                        if (lysYdilD + lysYdilY > 40) {
                            etLysYdilD.setText("");
                            etLysYdilY.setText("");
                            etLysYdilN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysYdil = new CalculateMark(lysYdilD, lysYdilY);
                            lysYdilN = lysYdil.getMark();
                            etLysYdilN.setText(format.format(lysYdilN));
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysYdilY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boolean ydilD = etLysYdilD.getText().toString().equals("");

                    if (ydilD) {
                        lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                        if (lysYdilY > 40) {
                            etLysYdilY.setText("");
                            etLysYdilN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysYdil = new CalculateMark(0, lysYdilY);
                            lysYdilN = lysYdil.getMark();
                            etLysYdilN.setText(format.format(lysYdilN));
                        }

                    } else {
                        lysYdilD = Double.parseDouble(etLysYdilD.getText().toString());
                        lysYdilY = Double.parseDouble(etLysYdilY.getText().toString());
                        if (lysYdilD + lysYdilY > 40) {
                            etLysYdilD.setText("");
                            etLysYdilY.setText("");
                            etLysYdilN.setText("");
                            questionErrorMessage();
                        } else {
                            CalculateMark lysYdil = new CalculateMark(lysYdilD, lysYdilY);
                            lysYdilN = lysYdil.getMark();
                            etLysYdilN.setText(format.format(lysYdilN));
                        }

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });
        etLysYdilN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    lysYdilN = Double.parseDouble(etLysYdilN.getText().toString());
                    if (lysYdilN > 40) {
                        etLysYdilN.setText("");
                        questionErrorMessage();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();
            }
        });

        etDiploma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean obp = etDiploma.getText().toString().equals("");
                if (obp) {
                    OBP = 0;
                } else {
                    OBP = Double.parseDouble(etDiploma.getText().toString());
                    OBP = (OBP * 5) * 0.12;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                lysShowScore();

            }
        });

    }

    public void lysShowScore() {

        Cursor res = myDb.ygsGetMark();

        String[] marks = new String[4];
        int m = 0;

        while (res.moveToNext()) {
            marks[m] = res.getString(3);
            m++;
        }

        LysCalculateScoreType lys = new LysCalculateScoreType(Double.parseDouble(marks[0]), Double.parseDouble(marks[1]),
                Double.parseDouble(marks[2]), Double.parseDouble(marks[3]), lysMatN, lysGeoN, lysFizikN, lysKimyaN,
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

    public void lysClear() {
        lysMatD = 0;
        lysMatY = 0;
        lysMatN = 0;
        lysGeoD = 0;
        lysGeoY = 0;
        lysGeoN = 0;
        lysFizikD = 0;
        lysFizikY = 0;
        lysFizikN = 0;
        lysKimyaD = 0;
        lysKimyaY = 0;
        lysKimyaN = 0;
        lysBiyoD = 0;
        lysBiyoY = 0;
        lysBiyoN = 0;
        lysEdeD = 0;
        lysEdeY = 0;
        lysEdeN = 0;
        lysCog1D = 0;
        lysCog1Y = 0;
        lysCog1N = 0;
        lysTarihD = 0;
        lysTarihY = 0;
        lysTarihN = 0;
        lysCog2D = 0;
        lysCog2Y = 0;
        lysCog2N = 0;
        lysFelD = 0;
        lysFelY = 0;
        lysFelN = 0;
        lysYdilD = 0;
        lysYdilY = 0;
        lysYdilN = 0;
        OBP = 0;
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
        etDiploma.setText("");

        lysShowScore();
    }

    public void questionErrorMessage() {
        String errorMessage = "Soru sayısından fazla doğru ve yanlış sayısı girdiniz.";
        Toast.makeText(Lys.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}

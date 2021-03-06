package demirciy.ygslyspuanhesaplama.ygs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.util.Locale;

import demirciy.ygslyspuanhesaplama.R;
import demirciy.ygslyspuanhesaplama.base.ActivityBase;
import demirciy.ygslyspuanhesaplama.common.ActivityAbout;
import demirciy.ygslyspuanhesaplama.common.ActivityMyScores;
import demirciy.ygslyspuanhesaplama.common.ActivityWhatIsYgsLys;
import demirciy.ygslyspuanhesaplama.database.DatabaseHelper;
import demirciy.ygslyspuanhesaplama.lys.ActivityLys;
import demirciy.ygslyspuanhesaplama.model.AllScores;
import demirciy.ygslyspuanhesaplama.model.CalculateMark;
import demirciy.ygslyspuanhesaplama.model.YgsCalculateScoreType;
import demirciy.ygslyspuanhesaplama.util.ToastMessage;

public class ActivityYgs extends ActivityBase {

    String LOG_TAG = "ActivityYgs.class : ";

    DecimalFormat format = new DecimalFormat("#.##");
    DatabaseHelper myDb;
    ToastMessage toast;

    AdView adView;

    EditText etYgsTrD, etYgsTrY, etYgsTrN, etYgsSosD, etYgsSosY,
            etYgsSosN, etYgsMatD, etYgsMatY, etYgsMatN, etYgsFenD,
            etYgsFenY, etYgsFenN;
    TextView tToplamD, tToplamY, tToplamN, tYgs1, tYgs2,
            tYgs3, tYgs4, tYgs5, tYgs6, tFindUni;

    public double ygsTrN, ygsSosN, ygsMatN, ygsFenN;
    double ygsTrD, ygsTrY, ygsSosD, ygsSosY, ygsMatD,
            ygsMatY, ygsFenD, ygsFenY;
    double ygs1, ygs2, ygs3, ygs4, ygs5, ygs6;

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
        tFindUni = (TextView) findViewById(R.id.tFindUni);
        adView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        myDb = new DatabaseHelper(this);
        toast = new ToastMessage(this);

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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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

    @Override
    protected void onResume() {
        super.onResume();

        if (adView != null)
            adView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (adView != null)
            adView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adView != null)
            adView.destroy();
    }

    public void ygsPrintSum() {

        try {

            if (etYgsTrD.getText().toString().equals(""))
                ygsTrD = 0;
            else
                ygsTrD = Double.parseDouble(etYgsTrD.getText().toString());

            if (etYgsTrY.getText().toString().equals(""))
                ygsTrY = 0;
            else
                ygsTrY = Double.parseDouble(etYgsTrY.getText().toString());

            if (etYgsTrN.getText().toString().equals(""))
                ygsTrN = 0;
            else
                ygsTrN = Double.parseDouble(etYgsTrN.getText().toString().replace(',', '.'));

            if (etYgsSosD.getText().toString().equals(""))
                ygsSosD = 0;
            else
                ygsSosD = Double.parseDouble(etYgsSosD.getText().toString());

            if (etYgsSosY.getText().toString().equals(""))
                ygsSosY = 0;
            else
                ygsSosY = Double.parseDouble(etYgsSosY.getText().toString());

            if (etYgsSosN.getText().toString().equals(""))
                ygsSosN = 0;
            else
                ygsSosN = Double.parseDouble(etYgsSosN.getText().toString().replace(',', '.'));

            if (etYgsMatD.getText().toString().equals(""))
                ygsMatD = 0;
            else
                ygsMatD = Double.parseDouble(etYgsMatD.getText().toString());

            if (etYgsMatY.getText().toString().equals(""))
                ygsMatY = 0;
            else
                ygsMatY = Double.parseDouble(etYgsMatY.getText().toString());

            if (etYgsMatN.getText().toString().equals(""))
                ygsMatN = 0;
            else
                ygsMatN = Double.parseDouble(etYgsMatN.getText().toString().replace(',', '.'));

            if (etYgsFenD.getText().toString().equals(""))
                ygsFenD = 0;
            else
                ygsFenD = Double.parseDouble(etYgsFenD.getText().toString());

            if (etYgsFenY.getText().toString().equals(""))
                ygsFenY = 0;
            else
                ygsFenY = Double.parseDouble(etYgsFenY.getText().toString());

            if (etYgsFenN.getText().toString().equals(""))
                ygsFenN = 0;
            else
                ygsFenN = Double.parseDouble(etYgsFenN.getText().toString().replace(',', '.'));

            tToplamD.setText(format.format(ygsTrD + ygsSosD + ygsMatD + ygsFenD));
            tToplamY.setText(format.format(ygsTrY + ygsSosY + ygsMatY + ygsFenY));
            tToplamN.setText(format.format(ygsTrN + ygsSosN + ygsMatN + ygsFenN));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void ygsShowScore() {

        try {
            YgsCalculateScoreType Ygs = new YgsCalculateScoreType(ygsTrN, ygsSosN, ygsMatN, ygsFenN);

            ygs1 = Ygs.getYgs1();
            tYgs1.setText(String.format(Locale.getDefault(), "Ygs-1 : %.2f", ygs1));

            ygs2 = Ygs.getYgs2();
            tYgs2.setText(String.format(Locale.getDefault(), "Ygs-2 : %.2f", ygs2));

            ygs3 = Ygs.getYgs3();
            tYgs3.setText(String.format(Locale.getDefault(), "Ygs-3 : %.2f", ygs3));

            ygs4 = Ygs.getYgs4();
            tYgs4.setText(String.format(Locale.getDefault(), "Ygs-4 : %.2f", ygs4));

            ygs5 = Ygs.getYgs5();
            tYgs5.setText(String.format(Locale.getDefault(), "Ygs-5 : %.2f", ygs5));

            ygs6 = Ygs.getYgs6();
            tYgs6.setText(String.format(Locale.getDefault(), "Ygs-6 : %.2f", ygs6));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ygsAddMarkDatabase() {

        try {
            Cursor res = myDb.ygsGetMark();

            if (res.getCount() == 0) {

                myDb.ygsAddMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
                myDb.ygsAddMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
                myDb.ygsAddMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
                myDb.ygsAddMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

                Log.d(LOG_TAG, "Ygs / Marks added into database.");

            } else {

                myDb.ygsUpdateMark("Türkçe", String.valueOf(ygsTrD), String.valueOf(ygsTrY), String.valueOf(ygsTrN));
                myDb.ygsUpdateMark("Sosyal", String.valueOf(ygsSosD), String.valueOf(ygsSosY), String.valueOf(ygsSosN));
                myDb.ygsUpdateMark("Matematik", String.valueOf(ygsMatD), String.valueOf(ygsMatY), String.valueOf(ygsMatN));
                myDb.ygsUpdateMark("Fen", String.valueOf(ygsFenD), String.valueOf(ygsFenY), String.valueOf(ygsFenN));

                Log.d(LOG_TAG, "Ygs / Marks updated into database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ygsAlertDialog() {

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

        createDialog(this, allScores);
    }

    public void ygsClear() {

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
            tToplamD.setText(R.string.correct);
            tToplamY.setText(R.string.incorrect);
            tToplamN.setText(R.string.mark);
            tYgs1.setText(R.string.ygs1);
            tYgs2.setText(R.string.ygs2);
            tYgs3.setText(R.string.ygs3);
            tYgs4.setText(R.string.ygs4);
            tYgs5.setText(R.string.ygs5);
            tYgs6.setText(R.string.ygs6);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void goLys() {

        Intent i = new Intent(ActivityYgs.this, ActivityLys.class);

        if (ygs1 < 180 && ygs2 < 180 && ygs3 < 180 && ygs4 < 180 && ygs5 < 180 && ygs6 < 180) {

            toast.show("180 barajını geçemediniz. Lys'ye giremezsiniz");
        }

        ygsDatasForLys();

        startActivity(i);
    }

    public void questionErrorMessage() {

        toast.show("Soru sayısından fazla doğru ve yanlış sayısı girdiniz.");
    }

    public void previousInfo() {

        Log.d(LOG_TAG, "Ygs / Previous datas bringing.");

        try {
            Cursor res = myDb.ygsGetMark();

            String[] corrects = new String[4];
            String[] incorrects = new String[4];
            String[] marks = new String[4];

            int i = 0;

            while (res.moveToNext()) {

                corrects[i] = res.getString(1);
                incorrects[i] = res.getString(2);
                marks[i] = res.getString(3);

                i++;
            }

            ygsTrD = Double.parseDouble(corrects[0]);
            ygsTrY = Double.parseDouble(incorrects[0]);
            ygsTrN = Double.parseDouble(marks[0]);
            setPreviousInfo(etYgsTrD, ygsTrD);
            setPreviousInfo(etYgsTrY, ygsTrY);
            setPreviousInfo(etYgsTrN, ygsTrN);

            ygsSosD = Double.parseDouble(corrects[1]);
            ygsSosY = Double.parseDouble(incorrects[1]);
            ygsSosN = Double.parseDouble(marks[1]);
            setPreviousInfo(etYgsSosD, ygsSosD);
            setPreviousInfo(etYgsSosY, ygsSosY);
            setPreviousInfo(etYgsSosN, ygsSosN);

            ygsMatD = Double.parseDouble(corrects[2]);
            ygsMatY = Double.parseDouble(incorrects[2]);
            ygsMatN = Double.parseDouble(marks[2]);
            setPreviousInfo(etYgsMatD, ygsMatD);
            setPreviousInfo(etYgsMatY, ygsMatY);
            setPreviousInfo(etYgsMatN, ygsMatN);

            ygsFenD = Double.parseDouble(corrects[3]);
            ygsFenY = Double.parseDouble(incorrects[3]);
            ygsFenN = Double.parseDouble(marks[3]);
            setPreviousInfo(etYgsFenD, ygsFenD);
            setPreviousInfo(etYgsFenY, ygsFenY);
            setPreviousInfo(etYgsFenN, ygsFenN);

            ygsShowScore();
            ygsPrintSum();

        } catch (Exception e) {

            e.printStackTrace();
            Log.e(LOG_TAG, "Bringing previous info failed!");
        }

        Log.d(LOG_TAG, "Ygs / Previous datas brought.");
    }

    private void setPreviousInfo(EditText edt, double value) {

        if (value != 0)
            edt.setText(format.format(value));
    }

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

            e.printStackTrace();
            Log.e(LOG_TAG, "Adding ygs datas into database for lys failed!");
        }

        Log.d(LOG_TAG, "Ygs datas added into database for Lys.");
    }

    public void goMyScores() {
        Intent i = new Intent(this, ActivityMyScores.class);
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

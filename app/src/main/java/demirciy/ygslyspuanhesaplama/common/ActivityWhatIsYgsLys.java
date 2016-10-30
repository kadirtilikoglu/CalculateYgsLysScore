package demirciy.ygslyspuanhesaplama.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demirciy.ygslyspuanhesaplama.R;

//action bar da ygs lys nedir butonuna basınca açılır
public class ActivityWhatIsYgsLys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_ygs_lys);

        //action bar daki geri dön butonunu gösterir
        //bu butonun çalışması için manifest dosyasında parent activity tanımlaması yapılmak zorundadır
        //butonun çalışması için tıklanma olayı konulmak zorunda değildir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

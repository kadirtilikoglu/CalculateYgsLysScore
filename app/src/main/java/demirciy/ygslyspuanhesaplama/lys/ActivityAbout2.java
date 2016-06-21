package demirciy.ygslyspuanhesaplama.lys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demirciy.ygslyspuanhesaplama.R;

//activity lys tarafından gelince bu activity açılır
public class ActivityAbout2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);

        //action bar daki geri dön butonunu gösterir
        //bu butonun çalışması için manifest dosyasında parent activity tanımlaması yapılmak zorundadır
        //butonun çalışması için tıklanma olayı konulmak zorunda değildir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

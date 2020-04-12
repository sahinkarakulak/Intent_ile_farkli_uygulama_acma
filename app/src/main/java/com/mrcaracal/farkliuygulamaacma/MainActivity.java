package com.mrcaracal.farkliuygulamaacma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button, button2, button3;

    public void init() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telefon_no = Uri.parse("tel:532");
                Intent tel_ara = new Intent(Intent.ACTION_DIAL, telefon_no);
                startActivity(tel_ara);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri web_adres = Uri.parse("https://mrcaracal.com");
                Intent web_sayfa_ac = new Intent(Intent.ACTION_VIEW, web_adres);
                // greatChooser kullandık. Cihazda websayfasını açabilecek birden fazla tarayıcı var ise seçenek sunar.
                startActivity(Intent.createChooser(web_sayfa_ac, "Ne ile açmak istersin?"));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ve gideceği konum oluşturuldu
                Uri konum = Uri.parse("geo:40.8458582,26.6487171z=30");
                Intent konuma_git = new Intent(Intent.ACTION_VIEW, konum);

                // gidilecek olan uygulama var mı yok mu kontrolü yapıldı
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> aktivite = packageManager.queryIntentActivities(konuma_git, 0);
                boolean var_ise = aktivite.size() > 0;

                // activity çağrıldı
                if (var_ise) {
                    // greatChooser kullandık ama konumu gösterebilecek yalnızca bir uygulama var ise direkt onunla açar.
                    startActivity(Intent.createChooser(konuma_git,"Ne ile açmak istersin?"));
                } else {
                    Toast.makeText(MainActivity.this, "Konumu gösterebilecek herhangi bir uygulama cihazınızda bulunmamakta", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

package com.example.ptsganjil2021reza23;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    TextView tx_sport, tx_format, tx_description;
    ImageView img_sport;
    Bundle bundle;
    Realm realm;
    RealmHelper realmHelper;
    String name, format, description;
    String img;
    ImageView favorit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tx_sport = findViewById(R.id.tx_sport_name);
        tx_format = findViewById(R.id.text_format);
        tx_description = findViewById(R.id.text_description);
        img_sport = findViewById(R.id.img_sport);
        favorit_button = findViewById(R.id.click_button);

        bundle = getIntent().getExtras();
        if (bundle !=null){
            name = bundle.getString("strSport");
            format = bundle.getString("strFormat");
            description = bundle.getString("strSportDescription");
            img = bundle.getString("strThumb");

        }
        tx_sport.setText(name);
        tx_format.setText(format);
        tx_description.setText(description);
        Picasso.get()
                .load(img)
                .into(img_sport);

        favorit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SportsModel sportsModel = new SportsModel(name,format,img,description);
                RealmHelper.save(sportsModel);
            }
        });
    }


}

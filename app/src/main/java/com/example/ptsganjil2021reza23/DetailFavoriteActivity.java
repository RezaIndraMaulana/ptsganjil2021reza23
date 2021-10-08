package com.example.ptsganjil2021reza23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavoriteActivity extends AppCompatActivity {

    TextView tx_sport, tx_format, tx_description;
    ImageView img_sport;
    Bundle bundle;

    String name, format, description;
    String img;
    ImageView btnDelete;
    SportsModel sportsModel;

    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        tx_sport = findViewById(R.id.tx_sport_name_favorite);
        tx_format = findViewById(R.id.text_format_favorite);
        tx_description = findViewById(R.id.text_description_favorite);
        img_sport = findViewById(R.id.img_sport_favorite);
        btnDelete = findViewById(R.id.btnDelete);

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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        SportsModel result = realm.where(SportsModel.class).equalTo("strSport", name).findFirst();
                        result.deleteFromRealm();
                    }
                });
            }
        });
    }


}


package com.example.ptsganjil2021reza23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SportAdapterFavorite adapterFavorite;
    private List<SportsModel> sportsModels;

    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        recyclerView = findViewById(R.id.recyclerviewFavorite);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        sportsModels = realmHelper.getAllModel();

        show();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapterFavorite.notifyDataSetChanged();
        show();
    }


    public void show(){
        adapterFavorite = new SportAdapterFavorite(sportsModels, new SportAdapterFavorite.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailFavoriteActivity.class);
                SportsModel model = sportsModels.get(position);
                intent.putExtra("strSport", model.getStrSport());
                intent.putExtra("strFormat", model.getStrFormat());
                intent.putExtra("strThumb", model.getStrSportThumb());
                intent.putExtra("strSportDescription", model.getStrSportsDescription());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapterFavorite);
    }
}

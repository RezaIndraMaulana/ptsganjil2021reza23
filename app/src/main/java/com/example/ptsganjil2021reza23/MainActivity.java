package com.example.ptsganjil2021reza23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SportsModel>sportlist;
    RecyclerView rvsport;
    SportsAdapter sportsAdapter;
    ImageView savean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvsport = findViewById(R.id.rv_sport);
        savean = findViewById(R.id.savean_main);

        savean.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritActivity.class);
                startActivity(intent);
            }

        });

        getDataApi();


    }
    void getDataApi() {
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/all_sports.php")

                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            sportlist = new ArrayList<>();
                            JSONArray sports = response.getJSONArray("sports");
                            for (int i = 0; i < sports.length(); i++) {
                                JSONObject sportsobject = sports.getJSONObject(i);
                                String strSport = sportsobject.getString("strSport");
                                String strFormat = sportsobject.getString("strFormat");
                                String strSportThumb = sportsobject.getString("strSportThumb");
                                String strSportDescription = sportsobject.getString("strSportDescription");
                                sportlist.add(new SportsModel(strSport, strFormat, strSportThumb, strSportDescription));
                            }

                            sportsAdapter = new SportsAdapter(sportlist, new SportsAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                    SportsModel model = sportlist.get(position);
                                    intent.putExtra("strSport", model.getStrSport());
                                    intent.putExtra("strFormat", model.getStrFormat());
                                    intent.putExtra("strThumb", model.getStrSportThumb());
                                    intent.putExtra("strSportDescription", model.getStrSportsDescription());
                                    startActivity(intent);
                                }
                            });

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            rvsport.setLayoutManager(layoutManager);
                            rvsport.setAdapter(sportsAdapter);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.toString());
                    }
                });
    }
 }

package com.example.charl.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public List<Anime> list = new ArrayList<>();
    public List<Anime> animeList;

    public List<String> nName = new ArrayList<String>();
    public List<String> nRealName = new ArrayList<String>();
    public List<String> nTeam = new ArrayList<String>();
    public List<String> nFirstappearance = new ArrayList<String>();
    public List<String> nCreatedby = new ArrayList<String>();
    public List<String> nPublisher = new ArrayList<String>();
    public List<String> nImageurl = new ArrayList<String>();
    public List<String> nBio = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getAnimes();



    }


    public void getAnimes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Anime>> call = api.getAnime();

        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                animeList = response.body();


                String[] animes = new String[animeList.size()];
                String[] realName = new String[animeList.size()];
                String[] team = new String[animeList.size()];
                String[] firstappearance = new String[animeList.size()];
                String[] createdby = new String[animeList.size()];
                String[] publisher = new String[animeList.size()];
                String[] imageurl = new String[animeList.size()];
                String[] bio = new String[animeList.size()];

                for(int i = 0; i < animeList.size(); i++){

                    animes[i] = animeList.get(i).getName();
                    realName[i] = animeList.get(i).getRealname();
                    team[i] = animeList.get(i).getTeam();
                    firstappearance[i] = animeList.get(i).getFirstappearance();
                    createdby[i] = animeList.get(i).getCreatedby();
                    publisher[i] = animeList.get(i).getPublisher();
                    imageurl[i] = animeList.get(i).getImageurl();
                    bio[i] = animeList.get(i).getBio();



                    nName.add(i, animeList.get(i).getName());
                    nRealName.add(i, animeList.get(i).getRealname());
                    nTeam.add(i, animeList.get(i).getTeam());
                    nFirstappearance.add(i, animeList.get(i).getFirstappearance());
                    nCreatedby.add(i, animeList.get(i).getCreatedby());
                    nPublisher.add(i, animeList.get(i).getPublisher());
                    nImageurl.add(i, animeList.get(i).getImageurl());
                    nBio.add(i, animeList.get(i).getBio());


                }


                for (int i = 0; i <animeList.size(); i++) {
                    list.add(new Anime(nName.get(i),nRealName.get(i),nTeam.get(i),nFirstappearance.get(i),nCreatedby.get(i),nPublisher.get(i),nImageurl.get(i),nBio.get(i)));

                }

                Display();

            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("aaaa", t.getMessage());
            }
        });
    }


    public void Display() {

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, list));


    }

}

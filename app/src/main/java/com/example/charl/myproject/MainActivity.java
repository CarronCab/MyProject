package com.example.charl.myproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static final String ANIME_UPDATE = "com.octip.cours.inf4042_11.ANIME_UPDATE";
    private AnimeAdaptater animeAdapt;
    public JSONArray team;
    JSONArray JsonData;
    public List<Anime> list = new ArrayList<>();
    public List<String> tmp = new ArrayList<String>();
    public List<Anime> animes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*RecyclerView rv = findViewById(R.id.rv_anime);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        animeAdapt= new AnimeAdaptater(getTeamFromFile());
        rv.setAdapter(animeAdapt);
        */

        //IntentFilter intentFilter = new IntentFilter(ANIME_UPDATE);
        //LocalBroadcastManager.getInstance(this).registerReceiver(new AnimeUpdate(), intentFilter);

        GetServices.startAction(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Anime>> call = api.getAnime();

        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                 animes = response.body();
                int j = 0;
                for (Anime h : animes) {

                    Log.d("iii", h.getName());
                    tmp.add(j, h.getName());
                    Log.d("bbb", tmp.get(j));
                    j++;
                }
                Log.d("bbb", String.valueOf(tmp.size()));
                Display (tmp, tmp.size());
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("aaaa", t.getMessage());
            }
        });



/*        for (int i = 0; i < 4; i++) {
            list.add(new Anime(tmp.get(i)));
            //Log.d("bbb", tmp.get(i));
            list.add(new Anime("1"));
        }
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, list));*/


    }

    public void Display(List<String> listdisplay, int size) {

        Log.d("hhh", String.valueOf(size));
        for(int i = 0; i < listdisplay.size(); i++){
            Log.d("ddd", listdisplay.get(i));
        }
        for (int i = 0; i <listdisplay.size(); i++) {
            list.add(new Anime(listdisplay.get(i)));
            //Log.d("bbb", tmp.get(i));
            
        }
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, list));

    }
/*
    public JSONArray getTeamFromFile(){
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "anime.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            team = new JSONObject(new String(buffer, "UTF-8")).getJSONArray("amiibo");
            return team;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }*/
/*
    public class AnimeUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Log.d("debug", intent.getAction()+ " reçu");
            JsonData = getTeamFromFile();
            Log.d("tag", "longueur du json array : " + JsonData.length());
           // AnimeAdapt.setNewAnime(getTeamFromFile());
        }
    }*/
}

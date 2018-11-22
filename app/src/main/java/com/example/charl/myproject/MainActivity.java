package com.example.charl.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public List<Anime> list = new ArrayList<>();
    public List<String> tmp = new ArrayList<String>();
    public List<Anime> animes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Anime>> call = api.getAnime();
        Call<List<Anime>> url = api.getUrl();

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

/*
        url.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> url, Response<List<Anime>> response) {
                animes = response.body();
                int j = 0;
                for (Anime h : animes) {

                    Log.d("ttt", h.getUrl());
                    tmp.add(j, h.getUrl());
                    Log.d("lll", tmp.get(j));
                    j++;
                }
                Log.d("jjj", String.valueOf(tmp.size()));
                //ShowIamges (tmp, tmp.size());
            }

            @Override
            public void onFailure(Call<List<Anime>> url, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ffff", t.getMessage());
            }
        });
*/

    }

    public void Display(List<String> listdisplay, int size) {

        Log.d("hhh", String.valueOf(size));
        for(int i = 0; i < listdisplay.size(); i++){
            Log.d("ddd", listdisplay.get(i));
        }
        for (int i = 0; i <listdisplay.size(); i++) {
            list.add(new Anime(listdisplay.get(i)));

        }
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, list));

    }

}

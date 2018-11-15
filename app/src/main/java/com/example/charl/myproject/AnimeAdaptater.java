package com.example.charl.myproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class AnimeAdaptater  extends RecyclerView.Adapter<AnimeAdaptater.AnimeHolder> {

    private JSONArray anime;
    AnimeAdaptater(JSONArray anime){
        this.anime=anime;
    }

    @NonNull
    @Override
    public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View view = lI.inflate(R.layout.rv_anime_element,parent,false);
        return new AnimeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeHolder holder, int position) {
        try {

            holder.name.setText( anime.getJSONObject(position).getString("character"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if( anime != null) {
            return  anime.length();
        }
        else{
            return 0;
        }
    }

    public class AnimeHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public AnimeHolder(View itemView) {
            super(itemView);
        //    this.name=(TextView) itemView.findViewById(R.id.rv_anime_element_name);
        }
    }

    public void setNewWorldCup(JSONArray newWorldCup){
        this. anime=newWorldCup;
        notifyDataSetChanged();
        Log.d("TAG", String.valueOf(getItemCount()));
    }
}

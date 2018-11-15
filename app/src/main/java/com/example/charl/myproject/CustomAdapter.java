package com.example.charl.myproject;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import  com.example.charl.myproject.databinding.RvAnimeElementBinding;

public class CustomAdapter extends BaseAdapter {

    private List<Anime> listAnime;
    private Activity activity;

    public CustomAdapter(Activity activity, List<Anime> listPerson){
        this.listAnime = listPerson;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listAnime.size();
    }

    @Override
    public Object getItem(int i) {
        return listAnime.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        RvAnimeElementBinding binding;
        if(view == null){
            view = LayoutInflater.from(activity).inflate(R.layout.rv_anime_element, null);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
        }else{
            binding = (RvAnimeElementBinding) view.getTag();
        }

        binding.setAnime(listAnime.get(i));
        return binding.getRoot();
    }
}

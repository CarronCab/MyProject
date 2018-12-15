package com.example.charl.myproject;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.charl.myproject.databinding.RvAnimeElementBinding;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private List<Anime> listAnime;
    private Activity activity;

    CustomAdapter(Activity activity, List<Anime> listPerson){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {


        RvAnimeElementBinding binding;
        if(view == null){
            view = LayoutInflater.from(activity).inflate(R.layout.rv_anime_element, null);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
        }else{
            binding = (RvAnimeElementBinding) view.getTag();
        }

        Button deleteBtn = view.findViewById(R.id.delete);

        Button detailsBtn = view.findViewById(R.id.details);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                listAnime.remove(i);
                notifyDataSetChanged();
            }
        });

        final View finalView = view;
        detailsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(finalView.getContext(),HeroDetails.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", i);
                v.getContext().startActivity(intent);


            }
        });

        assert binding != null;
        binding.setAnime(listAnime.get(i));
        return binding.getRoot();

    }
}

package com.example.charl.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HeroDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final int id;

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        id = extras.getInt("id");

        final String name = Sta.list.get(id).getName();
        final String realName = Sta.list.get(id).getRealname();
        final String team = Sta.list.get(id).getTeam();
        final String firstappearance = Sta.list.get(id).getFirstappearance();
        final String createdby = Sta.list.get(id).getCreatedby();
        final String publisher = Sta.list.get(id).getPublisher();
        final String imageurl = Sta.list.get(id).getImageurl();
        final String bio = Sta.list.get(id).getBio();


        final EditText nom = findViewById(R.id.name);
        nom.setText(name);

        final EditText vraiNom =  findViewById(R.id.realName);
        vraiNom.setText(realName);

        final EditText equipe =  findViewById(R.id.team);
        equipe.setText(team);

        final EditText age =  findViewById(R.id.firstappearance);
        age.setText(firstappearance);

        final EditText hist =  findViewById(R.id.bio);
        hist.setText(bio);


        Button apply = findViewById(R.id.apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nom.getText().toString();
                String realName = vraiNom.getText().toString();
                String team = equipe.getText().toString();
                String firstappearance = age.getText().toString();
                String bio = hist.getText().toString();


                Sta.list.set(id,new Anime(id, name,realName,team,firstappearance,createdby,publisher,imageurl,bio));


                Intent intent = new Intent(HeroDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        }

}

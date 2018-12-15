package com.example.charl.myproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Display();

        FloatingActionButton floatingActionButton =
                findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    showDialog();
                    Display();

            }
        });

    }

    public void showDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        @SuppressLint("InflateParams") View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = promptView.findViewById(R.id.edittext);
        final EditText edit_realName = promptView.findViewById(R.id.edit_realName);
        final EditText edit_team = promptView.findViewById(R.id.edit_team);
        final EditText edit_firstappearance = promptView.findViewById(R.id.edit_firstappearance);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = String.valueOf(editText.getText());
                        String realName = String.valueOf(edit_realName.getText());
                        String team = String.valueOf(edit_team.getText());
                        String firstappearance = String.valueOf(edit_firstappearance.getText());
                        if (name != null || realName != null || team != null || firstappearance != null) {
                            int newId = Sta.list.size();
                            Sta.list.add(new Anime(newId,name, realName, team, firstappearance, "", "", "https://yt3.ggpht.com/-uGYJvczbuow/AAAAAAAAAAI/AAAAAAAAAAA/VQbgt1FitYs/s900-c-k-no-mo-rj-c0xffffff/photo.jpg", ""));
                            Display();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }



    public void Display() {

        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, Sta.list));

        progressBar.setVisibility(View.GONE);



    }



}

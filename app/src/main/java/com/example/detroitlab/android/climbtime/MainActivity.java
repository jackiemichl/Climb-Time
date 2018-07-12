package com.example.detroitlab.android.climbtime;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SessionHistoryListAdapter adapter;
    private ArrayList<Session> sessionHistory;
    Map<String, Session> store;
    private static final int SESSION_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Session History");

        final ArrayList<Climb> threeclimbs = new ArrayList<Climb>() {{
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.GREEN, Category.BOULDER));
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.ORANGE, Category.BOULDER));
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
        }};

        final ArrayList<Climb> redclimbs = new ArrayList<Climb>() {{
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
        }};

        final ArrayList<Climb> orangeClimbs = new ArrayList<Climb>() {{
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.ORANGE, Category.BOULDER));
        }};

        final ArrayList<Climb> greenClimbs = new ArrayList<Climb>() {{
            add(new Climb("VO", Difficulty.NEUTRAL, Grade.GREEN, Category.BOULDER));
        }};


//        store = ((MyApp) getApplication()).store;
//        sessionHistory = new ArrayList<>(store.values());
        sessionHistory = new ArrayList<Session>() {{
            add(new Session(new DateTime().plusMinutes(2), threeclimbs));
            add(new Session(new DateTime().plusMinutes(0), orangeClimbs));
            add(new Session(new DateTime().plusMinutes(5), redclimbs));
            add(new Session(new DateTime().plusMinutes(3), greenClimbs));
        }};

        Collections.sort(sessionHistory);

        adapter = new SessionHistoryListAdapter(getApplicationContext(), sessionHistory);
        ListView lcg = findViewById(R.id.listSessionHistory);
        lcg.setAdapter(adapter);


        FloatingActionButton addSessionButton = findViewById(R.id.addSession);
        addSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SessionActivity.class);
                startActivityForResult(intent, SESSION_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            sessionHistory.clear();
            sessionHistory.addAll(store.values());
            adapter.notifyDataSetChanged();
            TextView textNoSessions = findViewById(R.id.noSessions);
            textNoSessions.setVisibility(View.GONE);
        }
    }
}

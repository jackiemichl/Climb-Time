package com.example.detroitlab.android.climbtime;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.media.tv.TvInputService;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {

    private SessionClimbsListAdapter adapter;
    private ArrayList<Climb> sessionClimbs;
    private static final int CLIMB_CODE = 1;
    private DateTime sessionDate;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        if (sessionDate == null) {
            sessionDate = new DateTime();
            Toast.makeText(this,"Time Updated", Toast.LENGTH_LONG).show();
        }

        TextView textSessionTime = findViewById(R.id.textSessionTime);
        textSessionTime.setText(sessionDate.toString("h:mm a"));

        sessionClimbs = new ArrayList<>();

        overridePendingTransition(R.anim.tran_left_in, R.anim.trans_left_out);
        FloatingActionButton addClimbButton = findViewById(R.id.addClimb);
        addClimbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionActivity.this, ClimbActivity.class);
                startActivityForResult(intent, CLIMB_CODE);
            }
        });

        adapter = new SessionClimbsListAdapter(this, sessionClimbs);
        ListView lcg = findViewById(R.id.listSessionClimbs);
        lcg.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Climb climb = new Gson().fromJson(data.getStringExtra("climb"), Climb.class);
            sessionClimbs.add(climb);
            adapter.notifyDataSetChanged();
            TextView textNoClimbs = findViewById(R.id.noClimbs);
            textNoClimbs.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public void onBackPressed() {
        if (sessionClimbs.size() != 0) {
            session = new Session(sessionDate, sessionClimbs);
            MyApp myApp = ((MyApp) getApplication());
            String key = String.valueOf(sessionDate.getMillis());
            myApp.store.put(key, session);
            myApp.saveStore();
            this.setResult(RESULT_OK);
        }
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
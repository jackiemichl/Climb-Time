package com.example.detroitlab.android.climbtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClimbActivity extends AppCompatActivity {

    private DifficultyListAdapter adapter;
    private ArrayList<Climb> boulderClimbs;
    private ArrayList<Climb> topRopeClimbs;
    private ArrayList<Climb> sportClimbs;
    private Button boulderButton;
    private Button topRopeButton;
    private Button sportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_climb);

        this.boulderClimbs = new ArrayList<Climb>() {{
            add(new Climb("V0", Difficulty.NEUTRAL, Grade.GREEN, Category.BOULDER));
            add(new Climb("V1", Difficulty.NEUTRAL, Grade.GREEN, Category.BOULDER));
            add(new Climb("V2", Difficulty.NEUTRAL, Grade.GREEN, Category.BOULDER));
            add(new Climb("V3", Difficulty.NEUTRAL, Grade.ORANGE, Category.BOULDER));
            add(new Climb("V4", Difficulty.NEUTRAL, Grade.ORANGE, Category.BOULDER));
            add(new Climb("V5", Difficulty.NEUTRAL, Grade.ORANGE, Category.BOULDER));
            add(new Climb("V6", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
            add(new Climb("V7", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
            add(new Climb("V8", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
            add(new Climb("V9", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
            add(new Climb("V10", Difficulty.NEUTRAL, Grade.RED, Category.BOULDER));
        }};

        this.topRopeClimbs = new ArrayList<Climb>() {{
            add(new Climb("5.8", Difficulty.NEUTRAL, Grade.GREEN, Category.TOP_ROPE));
            add(new Climb("5.9", Difficulty.NEUTRAL, Grade.GREEN, Category.TOP_ROPE));
            add(new Climb("5.10", Difficulty.NEUTRAL, Grade.ORANGE, Category.TOP_ROPE));
            add(new Climb("5.11", Difficulty.NEUTRAL, Grade.ORANGE, Category.TOP_ROPE));
            add(new Climb("5.12", Difficulty.NEUTRAL, Grade.RED, Category.TOP_ROPE));
            add(new Climb("5.13", Difficulty.NEUTRAL, Grade.RED, Category.TOP_ROPE));
        }};

        this.sportClimbs = new ArrayList<Climb>() {{
            add(new Climb("5.8", Difficulty.NEUTRAL, Grade.GREEN, Category.SPORT));
            add(new Climb("5.9", Difficulty.NEUTRAL, Grade.GREEN, Category.SPORT));
            add(new Climb("5.10", Difficulty.NEUTRAL, Grade.ORANGE, Category.SPORT));
            add(new Climb("5.11", Difficulty.NEUTRAL, Grade.ORANGE, Category.SPORT));
            add(new Climb("5.12", Difficulty.NEUTRAL, Grade.RED, Category.SPORT));
            add(new Climb("5.13", Difficulty.NEUTRAL, Grade.RED, Category.SPORT));
        }};


        overridePendingTransition(R.anim.tran_left_in, R.anim.trans_left_out);
        boulderButton = findViewById(R.id.boulderButton);
        boulderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClimbActivity.this, "Boulder", Toast.LENGTH_LONG).show();
                adapter.setList(boulderClimbs);
                boulderButton.setBackgroundColor(getColor(R.color.colorPrimaryLight));
                topRopeButton.setBackgroundColor(getColor(R.color.colorPrimary));
                sportButton.setBackgroundColor(getColor(R.color.colorPrimary));
            }
        });
        topRopeButton = findViewById(R.id.topRopeButton);
        topRopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClimbActivity.this, "Top Rope", Toast.LENGTH_LONG).show();
                adapter.setList(topRopeClimbs);
                topRopeButton.setBackgroundColor(getColor(R.color.colorPrimaryLight));
                boulderButton.setBackgroundColor(getColor(R.color.colorPrimary));
                sportButton.setBackgroundColor(getColor(R.color.colorPrimary));
            }
        });
        sportButton = findViewById(R.id.sportButton);
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClimbActivity.this, "Sport", Toast.LENGTH_LONG).show();
                adapter.setList(sportClimbs);
                sportButton.setBackgroundColor(getColor(R.color.colorPrimaryLight));
                boulderButton.setBackgroundColor(getColor(R.color.colorPrimary));
                topRopeButton.setBackgroundColor(getColor(R.color.colorPrimary));
            }
        });

        adapter = new DifficultyListAdapter(this, new ArrayList<Climb>() {{
            addAll(boulderClimbs);
        }});
        ListView lcg = findViewById(R.id.listClimbGrades);
        lcg.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        Toast.makeText(ClimbActivity.this, "Climb Added!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}

package com.example.detroitlab.android.climbtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DifficultyListAdapter extends ArrayAdapter<Climb> {

    private ArrayList<Climb> climbs;

    DifficultyListAdapter(Context context, ArrayList<Climb> climbs) {
        super(context, R.layout.view_climb_difficulty_selector, climbs);
        this.climbs = climbs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Climb climb = climbs.get(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_climb_difficulty_selector, parent, false);
        }


        Button neutralButton = convertView.findViewById(R.id.neutralButton);
        neutralButton.setText(climb.name);
        neutralButton.setBackgroundColor(Util.getColorBasedOnGrade(getContext(), climb.grade));
        neutralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("climb", (new Gson()).toJson(climb));
                ((ClimbActivity) getContext()).setResult(Activity.RESULT_OK, data);
                ((ClimbActivity) getContext()).finish();
                Toast.makeText(getContext(), "Neutral", Toast.LENGTH_LONG).show();
            }
        });

        Button positiveButton = convertView.findViewById(R.id.positiveButton);
        positiveButton.setBackgroundColor(Util.getSecondaryColorBasedOnGrade(getContext(), climb.grade));
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                climb.difficulty = Difficulty.POSITIVE;
                Intent data = new Intent();
                data.putExtra("climb", (new Gson()).toJson(climb));
                ((ClimbActivity) getContext()).setResult(Activity.RESULT_OK, data);
                ((ClimbActivity) getContext()).finish();
                Toast.makeText(getContext(), "Positive", Toast.LENGTH_LONG).show();
            }
        });

        Button negativeButton = convertView.findViewById(R.id.negativeButton);
        negativeButton.setBackgroundColor(Util.getSecondaryColorBasedOnGrade(getContext(), climb.grade));
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                climb.difficulty = Difficulty.NEGATIVE;
                Intent data = new Intent();
                data.putExtra("climb", (new Gson().toJson(climb)));
                ((ClimbActivity) getContext()).setResult(Activity.RESULT_OK, data);
                ((ClimbActivity) getContext()).finish();
                Toast.makeText(getContext(), "Negative", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }

    public void setList(ArrayList<Climb> climbs) {
        this.climbs.clear();
        if (climbs != null) {
            this.climbs.addAll(climbs);
        }
        notifyDataSetChanged();

    }

}

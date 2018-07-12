package com.example.detroitlab.android.climbtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SessionClimbsListAdapter extends ArrayAdapter<Climb>{

    private ArrayList<Climb> climbs;

    SessionClimbsListAdapter(Context context, ArrayList<Climb> climbs) {
        super(context, R.layout.view_climb_detail, climbs);
        this.climbs = climbs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Climb climb = climbs.get(position);

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_climb_detail, parent, false);
        }

        TextView textClimbName = convertView.findViewById(R.id.textClimbName);
        textClimbName.setText(climb.name + climb.getDifficulty());
        textClimbName.setBackgroundColor(Util.getColorBasedOnGrade(getContext(), climb.grade));

        TextView textClimbType = convertView.findViewById(R.id.textClimbType);
        textClimbType.setText(climb.getClimbCategory());

        return convertView;
    }


}

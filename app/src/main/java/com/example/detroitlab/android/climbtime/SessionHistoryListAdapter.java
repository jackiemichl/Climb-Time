package com.example.detroitlab.android.climbtime;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SessionHistoryListAdapter extends ArrayAdapter<Session> {

    private ArrayList<Session> sessionHistory;

    SessionHistoryListAdapter(Context context, ArrayList<Session> sessionHistory) {
        super(context, R.layout.view_session_overview, sessionHistory);
        this.sessionHistory = sessionHistory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Session session = sessionHistory.get(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_session_overview, parent, false);
        }

        TextView textSessionTitle = convertView.findViewById(R.id.sessionTitle);
        textSessionTitle.setText(session.date.toString("E, MMM d, y"));

        TextView textSessionTime = convertView.findViewById(R.id.sessionOverviewTime);
        textSessionTime.setText(session.date.toString("h:mm a"));

        final View barGreen = convertView.findViewById(R.id.barGreen);
        final View barOrange = convertView.findViewById(R.id.barOrange);
        final View barRed = convertView.findViewById(R.id.barRed);
        final boolean[] setHeight = {false};

        barGreen.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float density = getContext().getResources().getDisplayMetrics().density;
                float px = (int) (75 * density);
                int barMargin = (int) (4 * density);
                float greenSum = 0;
                float orangeSum = 0;
                float redSum = 0;
                float total = session.climbs.size();

                for (Climb climb : session.climbs) {
                    if (climb.grade == Grade.GREEN) {
                        greenSum += 1;
                    } else if (climb.grade == Grade.ORANGE) {
                        orangeSum += 1;
                    } else if (climb.grade == Grade.RED) {
                        redSum += 1;
                    }
                }


                int barGreenHeight = (int) (greenSum/total * px);
                int barOrangeHeight = (int) (orangeSum / total * px);
                int barRedHeight = (int) (redSum / total * px);

                if(!setHeight[0]) {
                    LinearLayout.LayoutParams greenParams = new LinearLayout.LayoutParams(0, barGreenHeight);
                    greenParams.weight = 1;
                    greenParams.gravity = Gravity.BOTTOM;
                    barGreen.setLayoutParams(greenParams);

                    LinearLayout.LayoutParams orangeParams = new LinearLayout.LayoutParams(0, barOrangeHeight);
                    orangeParams.weight = 1;
                    orangeParams.gravity = Gravity.BOTTOM;
                    orangeParams.setMargins(barMargin, 0, barMargin, 0);
                    barOrange.setLayoutParams(orangeParams);

                    LinearLayout.LayoutParams redParams = new LinearLayout.LayoutParams(0, barRedHeight);
                    redParams.weight = 1;
                    redParams.gravity = Gravity.BOTTOM;
                    barRed.setLayoutParams(redParams);

                    setHeight[0] = true;
                }
            }}
        );

        return convertView;
    }
}

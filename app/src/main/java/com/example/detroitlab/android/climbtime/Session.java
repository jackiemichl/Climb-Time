package com.example.detroitlab.android.climbtime;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Session implements Comparable<Session> {
    DateTime date;
    ArrayList<Climb> climbs;

    Session(DateTime date, ArrayList<Climb> climbs) {
        this.date = date;
        this.climbs = climbs;
    }

    @Override
    public int compareTo(@NonNull Session session) {
        return session.date.compareTo(date);
    }
}

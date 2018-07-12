package com.example.detroitlab.android.climbtime;

import android.content.Context;

public final class Util {
    static int getColorBasedOnGrade(Context context, Grade grade) {
        if (grade == Grade.GREEN) {
            return context.getColor(R.color.colorGreen);
        } else if (grade == Grade.ORANGE) {
            return context.getColor(R.color.colorOrange);
        }
        return context.getColor(R.color.colorRed);
    }

    static int getSecondaryColorBasedOnGrade(Context context, Grade grade) {
        if (grade == Grade.GREEN) {
            return context.getColor(R.color.colorGreenDark);
        } else if (grade == Grade.ORANGE) {
            return context.getColor(R.color.colorOrangeDark);
        }
        return context.getColor(R.color.colorRedDark);
    }
}

package com.example.detroitlab.android.climbtime;

import android.content.Context;

enum Difficulty {
    NEGATIVE, NEUTRAL, POSITIVE
}

enum Grade {
    GREEN, ORANGE, RED
}

enum Category {
    BOULDER, TOP_ROPE, SPORT
}

class Climb {
    String name;
    Difficulty difficulty;
    Grade grade;
    Category category;

    Climb(String name, Difficulty difficulty, Grade grade, Category category) {
        this.name = name;
        this.difficulty = difficulty;
        this.grade = grade;
        this.category = category;
    }

    String getClimbCategory() {
        if (this.category == Category.BOULDER) {
            return "Boulder";
        } else if (this.category == Category.TOP_ROPE) {
            return "Top Rope";
        }
        return "Sport";
    }

    String getDifficulty() {
        if (this.difficulty == difficulty.POSITIVE) {
            return " +";
        } else if (this.difficulty == difficulty.NEGATIVE) {
            return " -";
        }
        return "";
    }

}

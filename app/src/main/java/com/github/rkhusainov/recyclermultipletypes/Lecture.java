package com.github.rkhusainov.recyclermultipletypes;

import androidx.annotation.NonNull;

public class Lecture {
    private final int mNumber;
    private final String mDate;
    private final String mTheme;
    private final String mLector;

    public Lecture(@NonNull int number,
                   @NonNull String date,
                   @NonNull String theme,
                   @NonNull String lector
    ) {
        mNumber = number;
        mDate = date;
        mTheme = theme;
        mLector = lector;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getDate() {
        return mDate;
    }

    public String getTheme() {
        return mTheme;
    }

    public String getLector() {
        return mLector;
    }
}

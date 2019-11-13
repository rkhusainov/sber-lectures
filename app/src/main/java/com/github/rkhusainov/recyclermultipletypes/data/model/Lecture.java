package com.github.rkhusainov.recyclermultipletypes.data.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lecture implements Serializable {
    private final int mNumber;
    private final String mDate;
    private final String mTheme;
    private final String mLector;
    private final List<String> mSubtopics;

    @JsonCreator
    public Lecture(
            @JsonProperty("number") int number,
            @JsonProperty("date") @NonNull String date,
            @JsonProperty("theme") @NonNull String theme,
            @JsonProperty("lector") @NonNull String lector,
            @JsonProperty("subtopics") @NonNull List<String> subtopics) {
        mNumber = number;
        mDate = date;
        mTheme = theme;
        mLector = lector;
        mSubtopics = new ArrayList<>(subtopics);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return mNumber == lecture.mNumber &&
                Objects.equals(mDate, lecture.mDate) &&
                Objects.equals(mTheme, lecture.mTheme) &&
                Objects.equals(mLector, lecture.mLector) &&
                Objects.equals(mSubtopics, lecture.mSubtopics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mNumber, mDate, mTheme, mLector, mSubtopics);
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

    public List<String> getSubtopics() {
        return mSubtopics;
    }
}

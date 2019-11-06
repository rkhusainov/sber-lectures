package com.github.rkhusainov.recyclermultipletypes.ui;

import com.github.rkhusainov.recyclermultipletypes.model.Lecture;

import java.util.List;

public interface CourseView {
    void showProgress();

    void hideProgress();

    void showData(List<Lecture> lectures, boolean isFirstCreate);
}

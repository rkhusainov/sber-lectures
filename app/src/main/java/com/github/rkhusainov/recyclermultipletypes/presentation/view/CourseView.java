package com.github.rkhusainov.recyclermultipletypes.presentation.view;

import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;

import java.util.List;

public interface CourseView {
    void showProgress();

    void hideProgress();

    void showData(List<Lecture> lectures, boolean isFirstCreate);
}

package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

public class CoursePresenter {

    private LecturesRepository mRepository;
    private ICourseView mICourseView;
    private boolean mIsFirstCreate;

    public CoursePresenter(LecturesRepository repository, ICourseView ICourseView, boolean isFirstCreate) {
        mRepository = repository;
        mICourseView = ICourseView;
        mIsFirstCreate = isFirstCreate;
    }

    public void loadDataAsync() {
        mRepository.loadDataAsync(mRepository,mICourseView,mIsFirstCreate);
    }
}

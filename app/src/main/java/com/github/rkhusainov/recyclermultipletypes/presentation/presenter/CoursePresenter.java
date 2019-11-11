package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

public class CoursePresenter {

    private LecturesRepository mRepository;
    private ICourseView mCourseView;
    private boolean mIsFirstCreate;

    public CoursePresenter(LecturesRepository repository, ICourseView courseView, boolean isFirstCreate) {
        mRepository = repository;
        mCourseView = courseView;
        mIsFirstCreate = isFirstCreate;
    }

    /**
     * Метод для получения данных в синхронном режиме.
     */
    // Данный метод нужен исключительно для понимания работы Unit-тестов.
    public void loadDataSync() {
        mRepository.loadLecturesFromWeb();
    }

    /**
     * Метод для загрузки данных в ассинхронном режиме.
     */
    public void loadDataAsync() {
        mRepository.loadDataAsync(mCourseView,mIsFirstCreate);
    }
}

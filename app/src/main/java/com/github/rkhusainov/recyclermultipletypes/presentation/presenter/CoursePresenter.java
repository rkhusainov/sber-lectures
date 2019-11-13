package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;
import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

import java.util.List;

public class CoursePresenter {

    private LecturesRepository mRepository;
    private ICourseView mCourseView;

    public CoursePresenter(LecturesRepository repository, ICourseView courseView) {
        mRepository = repository;
        mCourseView = courseView;
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

        mCourseView.showProgress();

        LecturesRepository.OnLoadFinishListener onLoadFinishListener = new LecturesRepository.OnLoadFinishListener() {
            @Override
            public void onFinish(List<Lecture> lectures) {
                mCourseView.showData(lectures);
                mCourseView.hideProgress();
            }
        };

        mRepository.loadDataAsync(onLoadFinishListener);
    }
}

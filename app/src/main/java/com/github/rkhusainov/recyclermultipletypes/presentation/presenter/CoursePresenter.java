package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import android.os.AsyncTask;

import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

import java.util.List;

public class CoursePresenter {

    private LecturesRepository mRepository;
    private ICourseView mICourseView;
    private LoadLecturesTask mLecturesTask;

    public CoursePresenter(ICourseView ICourseView) {
        mICourseView = ICourseView;
    }

    public CoursePresenter(LecturesRepository repository, ICourseView ICourseView, boolean isFirstCreate) {
        mRepository = repository;
        mICourseView = ICourseView;
        mLecturesTask = new LoadLecturesTask(mICourseView, mRepository, isFirstCreate);
        mLecturesTask.execute();
    }

    private static class LoadLecturesTask extends AsyncTask<Void, Void, List<Lecture>> {
        private final boolean mIsFirstCreate;
        LecturesRepository mRepository;
        private ICourseView mICourseView;

        private LoadLecturesTask(ICourseView ICourseView, LecturesRepository repository, boolean isFirstCreate) {
            mICourseView = ICourseView;
            mRepository = repository;
            mIsFirstCreate = isFirstCreate;
        }

        @Override
        protected void onPreExecute() {
            mICourseView.showProgress();
        }

        @Override
        protected List<Lecture> doInBackground(Void... voids) {
            return mRepository.loadLecturesFromWeb();
        }

        @Override
        protected void onPostExecute(List<Lecture> lectures) {
            mICourseView.hideProgress();
            mICourseView.showData(lectures, mIsFirstCreate);
        }
    }
}

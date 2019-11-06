package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import android.os.AsyncTask;

import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.CourseView;

import java.util.List;

public class CoursePresenter {

    private LecturesRepository mProvider;
    private CourseView mCourseView;
    private LoadLecturesTask mLecturesTask;

    public CoursePresenter(LecturesRepository provider, CourseView courseView, boolean isFirstCreate) {
        mProvider = provider;
        mCourseView = courseView;
        mLecturesTask = new LoadLecturesTask(mCourseView, mProvider, isFirstCreate);
        mLecturesTask.execute();
    }

    private static class LoadLecturesTask extends AsyncTask<Void, Void, List<Lecture>> {
        private final boolean mIsFirstCreate;
        LecturesRepository mProvider;
        private CourseView mCourseView;

        private LoadLecturesTask(CourseView courseView, LecturesRepository provider, boolean isFirstCreate) {
            mCourseView = courseView;
            mProvider = provider;
            mIsFirstCreate = isFirstCreate;
        }

        @Override
        protected void onPreExecute() {
            mCourseView.showProgress();
        }

        @Override
        protected List<Lecture> doInBackground(Void... voids) {
            return mProvider.loadLecturesFromWeb();
        }

        @Override
        protected void onPostExecute(List<Lecture> lectures) {
            mCourseView.hideProgress();
            mCourseView.showData(lectures, mIsFirstCreate);
        }
    }
}

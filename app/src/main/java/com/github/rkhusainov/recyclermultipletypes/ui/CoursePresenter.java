package com.github.rkhusainov.recyclermultipletypes.ui;

import android.os.AsyncTask;

import com.github.rkhusainov.recyclermultipletypes.model.Lecture;

import java.util.List;

public class CoursePresenter {

    private CourseListProvider mProvider;
    private CourseView mCourseView;
    private LoadLecturesTask mLecturesTask;

    public CoursePresenter(CourseListProvider provider, CourseView courseView, boolean isFirstCreate) {
        mProvider = provider;
        mCourseView = courseView;
        mLecturesTask = new LoadLecturesTask(mCourseView, mProvider, isFirstCreate);
        mLecturesTask.execute();
    }

    private static class LoadLecturesTask extends AsyncTask<Void, Void, List<Lecture>> {
        private final boolean mIsFirstCreate;
        CourseListProvider mProvider;
        private CourseView mCourseView;

        private LoadLecturesTask(CourseView courseView, CourseListProvider provider, boolean isFirstCreate) {
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

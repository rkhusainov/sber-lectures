package com.github.rkhusainov.recyclermultipletypes.data.repository;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.github.rkhusainov.recyclermultipletypes.BuildConfig.API_URL;

public class LecturesRepository {

    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public List<Lecture> mLectures;

    public List<Lecture> getLectures() {
        return mLectures == null ? null : new ArrayList<>(mLectures);
    }

    public List<String> provideLectors() {
        Set<String> lectorSet = new HashSet<>();
        for (Lecture lecture : mLectures) {
            lectorSet.add(lecture.getLector());
        }
        return new ArrayList<>(lectorSet);
    }

    public List<Lecture> lectureFilterBy(@NonNull String lectorName) {
        List<Lecture> result = new ArrayList<>();
        for (Lecture lecture : mLectures) {
            if (lecture.getLector().equals(lectorName)) {
                result.add(lecture);
            }
        }
        return new ArrayList<>(result);
    }

    public Lecture getLectureByDate(@NonNull Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        for (Lecture lecture : mLectures) {
            try {
                Date lectureDate = format.parse(lecture.getDate());
                if (lectureDate != null && lectureDate.after(date)) {
                    return lecture;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mLectures.get(mLectures.size() - 1);
    }

    @Nullable
    public List<Lecture> loadLecturesFromWeb() {
        if (mLectures != null) {
            return mLectures;
        }
        InputStream is = null;
        try {
            final URL url = new URL(API_URL);
            URLConnection connection = url.openConnection();
            is = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Lecture[] lectures = mapper.readValue(is, Lecture[].class);
            mLectures = Arrays.asList(lectures);
            return new ArrayList<>(mLectures);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void loadDataAsync(OnLoadFinishListener onLoadFinishListener) {
        LoadLecturesTask loadLecturesTask = new LoadLecturesTask(onLoadFinishListener);
        loadLecturesTask.execute();
    }

    private class LoadLecturesTask extends AsyncTask<Void, Void, List<Lecture>> {
        private OnLoadFinishListener mOnLoadFinishListener;

        private LoadLecturesTask(OnLoadFinishListener onLoadFinishListener) {
            mOnLoadFinishListener = onLoadFinishListener;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Lecture> doInBackground(Void... voids) {
            return loadLecturesFromWeb();
        }

        @Override
        protected void onPostExecute(List<Lecture> lectures) {
            mOnLoadFinishListener.onFinish(lectures);
        }
    }

    public interface OnLoadFinishListener {
        void onFinish(List<Lecture> lectures);
    }
}

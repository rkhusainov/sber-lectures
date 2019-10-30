package com.github.rkhusainov.recyclermultipletypes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.rkhusainov.recyclermultipletypes.R;
import com.github.rkhusainov.recyclermultipletypes.model.Lecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CourseListFragment extends Fragment {

    public static final int POSITION_ALL = 0;
    private static final int NON_GROUP = 0, GROUP = 1;

    private CourseAdapter mCourseAdapter;
    private CourseListProvider mProvider = new CourseListProvider();
    private List<Lecture> mLectures = new ArrayList<>();
    private List<String> mLectors;
    private int mLectorPosition;
    private int mWeekGroupStatus;

    public static CourseListFragment newInstance() {
        return new CourseListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getLectors();
        initRecyclerView(view, savedInstanceState == null);
        initSpinner(view);

    }

    private void getLectors() {
        mLectors = mProvider.provideLectors();
        Collections.sort(mLectors);
        mLectors.add(POSITION_ALL, getResources().getString(R.string.all));
    }

    private void initRecyclerView(View view, boolean isFirstCreate) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mCourseAdapter = new CourseAdapter(getResources());
        mCourseAdapter.setLectures(mProvider.provideLectures());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mCourseAdapter);

        if (isFirstCreate) {
            Date date = new Date();
            Lecture nextLecture = mProvider.getLectureByDate(date);
            int nextLecturePosition = mCourseAdapter.getLecturePosition(nextLecture);
            if (nextLecturePosition != -1) {
                recyclerView.scrollToPosition(nextLecturePosition);
            }
        }
    }

    private void initSpinner(View view) {
        Spinner lectorSpinner = view.findViewById(R.id.lectors_spinner);
        lectorSpinner.setAdapter(new LectorSpinnerAdapter(mLectors));

        lectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mLectorPosition = position;

                setGroupStatus();
                setLectures(mLectorPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner weekSpinner = view.findViewById(R.id.week_group_spinner);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(), R.array.group_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(adapter);

        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mWeekGroupStatus = position;

                setGroupStatus();
                setLectures(mLectorPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setGroupStatus() {
        if (mWeekGroupStatus == 0) {
            mCourseAdapter.setGroupStatus(NON_GROUP);
        } else {
            mCourseAdapter.setGroupStatus(GROUP);
        }
    }

    public void setLectures(int lectorPosition) {
        if (lectorPosition == POSITION_ALL) {
            mLectures = mProvider.provideLectures();
        } else {
            mLectures = mProvider.lectureFilterBy(mLectors.get(mLectorPosition));
        }
        mCourseAdapter.setLectures(mLectures);
    }
}

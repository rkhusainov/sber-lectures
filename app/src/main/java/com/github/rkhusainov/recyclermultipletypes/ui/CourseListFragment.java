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

public class CourseListFragment extends Fragment implements OnItemClickListener, CourseView {

    public static final int POSITION_ALL = 0;
    private static final int NON_GROUP = 0, GROUP = 1;

    private CourseAdapter mCourseAdapter;
    private RecyclerView mRecyclerView;
    private Spinner mLectorSpinner;
    private Spinner mWeekSpinner;
    private CourseListProvider mCourseListProvider = new CourseListProvider();
    private List<Lecture> mLectures = new ArrayList<>();
    private List<String> mLectors;
    private int mLectorPosition;
    private int mWeekGroupStatus;

    private View mLoadingView;
    private CoursePresenter mPresenter;

    public static CourseListFragment newInstance() {
        return new CourseListFragment();
    }

    {
        // нужно для того, чтобы инстанс LecturesProvider не убивался после смены конфигурации
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingView = view.findViewById(R.id.loading_view);
        mRecyclerView = view.findViewById(R.id.recycler);
        mLectorSpinner = view.findViewById(R.id.lectors_spinner);
        mWeekSpinner = view.findViewById(R.id.week_group_spinner);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Lecture> lectures = mCourseListProvider.getLectures();
        if (lectures == null) {
            mPresenter = new CoursePresenter(mCourseListProvider, this, savedInstanceState == null);
        } else {
            initRecyclerView(savedInstanceState == null, lectures);
            initLectorsSpinner();
            initWeekSpinner();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initRecyclerView(boolean isFirstCreate, @NonNull List<Lecture> lectures) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mCourseAdapter = new CourseAdapter(getResources(), this);
        mCourseAdapter.setLectures(lectures);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mCourseAdapter);

        if (isFirstCreate) {
            Date date = new Date();
            Lecture nextLecture = mCourseListProvider.getLectureByDate(date);
            int nextLecturePosition = mCourseAdapter.getLecturePosition(nextLecture);
            if (nextLecturePosition != -1) {
                mRecyclerView.scrollToPosition(nextLecturePosition);
            }
        }
    }

    private void initLectorsSpinner() {
        mLectors = mCourseListProvider.provideLectors();
        Collections.sort(mLectors);
        mLectors.add(POSITION_ALL, getResources().getString(R.string.all));
        mLectorSpinner.setAdapter(new LectorSpinnerAdapter(mLectors));

        mLectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    }

    private void initWeekSpinner() {
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(), R.array.group_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWeekSpinner.setAdapter(adapter);

        mWeekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            mLectures = mCourseListProvider.getLectures();
        } else {
            mLectures = mCourseListProvider.lectureFilterBy(mLectors.get(mLectorPosition));
        }
        mCourseAdapter.setLectures(mLectures);
    }

    @Override
    public void onItemClick(Lecture lecture) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.newInstance(lecture))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showProgress() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<Lecture> lectures, boolean isFirstCreate) {
        initRecyclerView(isFirstCreate, lectures);
        initLectorsSpinner();
        initWeekSpinner();
        mCourseAdapter.setLectures(lectures);
    }
}

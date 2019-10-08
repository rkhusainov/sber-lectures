package com.github.rkhusainov.recyclermultipletypes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int POSITION_ALL = 0;
    private static final int NON_GROUP = 0, GROUP = 1;

    private RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter = new CourseAdapter();
    private CourseListProvider mProvider = new CourseListProvider();
    private List<Lecture> mLectures = new ArrayList<>();
    private List<String> mLectors;
    private int mLectorPosition;
    private int mWeekGroupStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLectors();
        initRecyclerView();
        initSpinner();
    }

    private void getLectors() {

        mLectors = mProvider.provideLectors();
        Collections.sort(mLectors);
        mLectors.add(POSITION_ALL, getResources().getString(R.string.all));
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mCourseAdapter);
    }

    private void initSpinner() {
        Spinner lectorSpinner = findViewById(R.id.lectors_spinner);
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

        Spinner weekSpinner = findViewById(R.id.week_group_spinner);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.group_status, android.R.layout.simple_spinner_item);
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

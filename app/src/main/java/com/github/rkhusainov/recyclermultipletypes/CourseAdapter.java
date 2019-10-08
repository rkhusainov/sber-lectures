package com.github.rkhusainov.recyclermultipletypes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int LECTURE = 0, WEEK = 1;

    private List<Object> mWeekLectures;

    public CourseAdapter(List<Object> weekLectures) {
        mWeekLectures = weekLectures;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case LECTURE:
                View v1 = inflater.inflate(R.layout.li_item, parent, false);
                viewHolder = new LectureHolder(v1);
                break;
            case WEEK:
                View v2 = inflater.inflate(R.layout.li_week_item, parent, false);
                viewHolder = new WeekHolder(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new LectureHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object currentItem = mWeekLectures.get(position);
        switch (holder.getItemViewType()) {
            case LECTURE:
                Lecture lecture = (Lecture) currentItem;
                ((LectureHolder) holder).bindLecture(lecture);
                break;
            case WEEK:
                String week = (String) currentItem;
                ((WeekHolder) holder).bindWeek(week);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mWeekLectures.get(position) instanceof Lecture) {
            return LECTURE;
        } else if (mWeekLectures.get(position) instanceof String) {
            return WEEK;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mWeekLectures.size();
    }

    static class LectureHolder extends RecyclerView.ViewHolder {

        private final TextView mNumber;
        private final TextView mDate;
        private final TextView mTheme;
        private final TextView mLecture;

        public LectureHolder(@NonNull View itemView) {
            super(itemView);

            mNumber = itemView.findViewById(R.id.number);
            mDate = itemView.findViewById(R.id.date);
            mTheme = itemView.findViewById(R.id.theme);
            mLecture = itemView.findViewById(R.id.lector);
        }

        private void bindLecture(Lecture currentLecture) {
            mNumber.setText(currentLecture.getNumber());
            mDate.setText(currentLecture.getDate());
            mTheme.setText(currentLecture.getTheme());
            mLecture.setText(currentLecture.getLector());
        }
    }

    static class WeekHolder extends RecyclerView.ViewHolder {

        private TextView mWeek;

        public WeekHolder(@NonNull View itemView) {
            super(itemView);

            mWeek = itemView.findViewById(R.id.week_name);
        }

        private void bindWeek(String currentWeek) {
            mWeek.setText(currentWeek);
        }
    }
}

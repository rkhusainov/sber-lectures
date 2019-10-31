package com.github.rkhusainov.recyclermultipletypes.ui;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.rkhusainov.recyclermultipletypes.R;
import com.github.rkhusainov.recyclermultipletypes.model.Lecture;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int LECTURE = 0, WEEK = 1;
    private static final int NON_GROUP = 0, GROUP = 1;
    private static final int LECTURES_IN_WEEK = 3;

    private List<Object> mWeekLectures = new ArrayList<>();
    private int mGroupStatus = 0;
    private Resources mResources;

    private OnItemClickListener mOnItemClickListener;

    public CourseAdapter(@NonNull Resources resources, OnItemClickListener listener) {
        mResources = resources;
        mOnItemClickListener = listener;
    }

    public void setLectures(@NonNull List<Lecture> lectures) {
        generateLectures(lectures);
        notifyDataSetChanged();
    }

    public void setGroupStatus(int status) {
        mGroupStatus = status;
    }

    private void generateLectures(@NonNull List<Lecture> lectures) {
        if (mGroupStatus == NON_GROUP) {
            mWeekLectures = new ArrayList<Object>(lectures);
        } else {
            mWeekLectures.clear();
            int weekIndex = -1;
            int weekNumber;
            for (Lecture lecture : lectures) {
                weekNumber = (lecture.getNumber() - 1) / LECTURES_IN_WEEK;
                if (weekNumber > weekIndex) {
                    weekIndex = weekNumber;
                    mWeekLectures.add(mResources.getString(R.string.week_num, +weekIndex + 1));
                }
                mWeekLectures.add(lecture);
            }
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

    public int getLecturePosition(@NonNull Lecture lecture) {
        return mWeekLectures.indexOf(lecture);
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
                viewHolder.itemView.setBackgroundColor(mResources.getColor(R.color.colorPrimary));
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
                ((LectureHolder) holder).bindLecture(lecture, mOnItemClickListener);
                break;
            case WEEK:
                String week = (String) currentItem;
                ((WeekHolder) holder).bindWeek(week);
                ((WeekHolder) holder).mWeek.setTextColor(mResources.getColor(R.color.colorWhite));
            default:
                break;
        }
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

        private void bindLecture(final Lecture currentLecture, final OnItemClickListener listener) {
            mNumber.setText(String.valueOf(currentLecture.getNumber()));
            mDate.setText(currentLecture.getDate());
            mTheme.setText(currentLecture.getTheme());
            mLecture.setText(currentLecture.getLector());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(currentLecture);
                }
            });
        }
    }

    static class WeekHolder extends RecyclerView.ViewHolder {

        private TextView mWeek;

        public WeekHolder(@NonNull View itemView) {
            super(itemView);

            mWeek = itemView.findViewById(R.id.week_name);
        }

        private void bindWeek(@NonNull String currentWeek) {
            mWeek.setText(currentWeek);
        }
    }
}

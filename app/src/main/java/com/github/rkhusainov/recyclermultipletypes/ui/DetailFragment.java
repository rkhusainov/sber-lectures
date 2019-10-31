package com.github.rkhusainov.recyclermultipletypes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.rkhusainov.recyclermultipletypes.R;
import com.github.rkhusainov.recyclermultipletypes.model.Lecture;

import java.util.List;

public class DetailFragment extends Fragment {

    public static final String LECTURE_KEY = "LECTURE_KEY";

    private TextView mTopic1TextView;
    private Lecture mLecture;

    public static DetailFragment newInstance(Lecture lecture) {

        Bundle args = new Bundle();

        args.putSerializable(LECTURE_KEY, lecture);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            mLecture = (Lecture) getArguments().getSerializable(LECTURE_KEY);

            ((TextView) view.findViewById(R.id.number)).setText(String.valueOf(mLecture.getNumber()));
            ((TextView) view.findViewById(R.id.date)).setText(mLecture.getDate());
            ((TextView) view.findViewById(R.id.theme)).setText(mLecture.getTheme());
            ((TextView) view.findViewById(R.id.lector)).setText(mLecture.getLector());

            mTopic1TextView = view.findViewById(R.id.topic_1_text_view);

            List<String> subTopicsList = mLecture.getSubtopics();
            for (int i = 0; i < subTopicsList.size(); i++) {
                mTopic1TextView.append(subTopicsList.get(i) + "\n\n");
            }
        }
    }
}

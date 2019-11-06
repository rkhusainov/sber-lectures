package com.github.rkhusainov.recyclermultipletypes.presentation.view;

import androidx.fragment.app.Fragment;

import com.github.rkhusainov.recyclermultipletypes.presentation.common.SingleFragmentActivity;

public class CourseActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return CourseListFragment.newInstance();
    }
}

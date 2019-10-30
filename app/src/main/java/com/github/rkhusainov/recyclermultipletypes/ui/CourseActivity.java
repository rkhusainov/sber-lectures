package com.github.rkhusainov.recyclermultipletypes.ui;

import androidx.fragment.app.Fragment;

import com.github.rkhusainov.recyclermultipletypes.common.SingleFragmentActivity;

public class CourseActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return CourseListFragment.newInstance();
    }
}

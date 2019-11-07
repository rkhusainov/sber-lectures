package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import android.content.Context;

import com.github.rkhusainov.recyclermultipletypes.R;
import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;
import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoursePresenterTest {

    @Mock
    private ICourseView mICourseView;

    @Mock
    private Context mContext;

    @Mock
    private LecturesRepository mRepository;
    private CoursePresenter mPresenter;
    private boolean mIsFirstCreate;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        mICourseView = Mockito.mock(ICourseView.class);
        mPresenter = new CoursePresenter(mRepository, mICourseView, true);
        when(mRepository.loadLecturesFromWeb()).thenReturn(createTestData());
    }

    @Test
    public void testScreen() throws Exception {
        String appName = mContext.getString(R.string.app_name);
        assertNotNull(appName);
        mRepository.loadLecturesFromWeb();
        InOrder inOrder = Mockito.inOrder(mICourseView);
        Mockito.verify(mICourseView, times(0)).showProgress();
        Mockito.verify(mICourseView).showData(createTestData(), true);
        Mockito.verify(mICourseView).hideProgress();
        inOrder.verifyNoMoreInteractions();
//        Mockito.verifyNoMoreInteractions(mICourseView);
    }

    public List<Lecture> createTestData() {
        List<Lecture> testData = new ArrayList<>();
        testData.add(new Lecture(0, "31.10.2019", "MVP", "Leonidov", Arrays.asList("MVP", "Tests")));
        return testData;
    }
}
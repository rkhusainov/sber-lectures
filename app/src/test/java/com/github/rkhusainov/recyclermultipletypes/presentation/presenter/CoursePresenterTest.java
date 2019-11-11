package com.github.rkhusainov.recyclermultipletypes.presentation.presenter;

import com.github.rkhusainov.recyclermultipletypes.data.model.Lecture;
import com.github.rkhusainov.recyclermultipletypes.data.repository.LecturesRepository;
import com.github.rkhusainov.recyclermultipletypes.presentation.view.ICourseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoursePresenterTest {

    @Mock
    private ICourseView mICourseView;

    @Mock
    private LecturesRepository mRepository;
    private CoursePresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new CoursePresenter(mRepository, mICourseView, true);
    }

    @Test
    public void testLoadDataSync() {
        when(mRepository.loadLecturesFromWeb()).thenReturn(createTestData());

        mPresenter.loadDataSync();

        //Проверка, что презентер действительно вызывает методы представления
        verify(mICourseView).showProgress();
        verify(mICourseView).showData(createTestData(),true);
        verify(mICourseView).hideProgress();
    }

    @Test
    public void testLoadDataSync_withOrder() {
        when(mRepository.loadLecturesFromWeb()).thenReturn(createTestData());

        mPresenter.loadDataSync();

        InOrder inOrder = Mockito.inOrder(mICourseView);

        //Проверка, что презентер действительно вызывает методы представления
        inOrder.verify(mICourseView).showProgress();
        inOrder.verify(mICourseView).hideProgress();
        inOrder.verify(mICourseView).showData(createTestData(),true);

        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testLoadDataAsync() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                createTestData();
                return null;
            }
        }).when(mRepository).loadDataAsync(mICourseView, true);

        mPresenter.loadDataAsync();

        InOrder inOrder = Mockito.inOrder(mICourseView);
        inOrder.verify(mICourseView).showProgress();
        inOrder.verify(mICourseView).showData(createTestData(), true);
        inOrder.verify(mICourseView).hideProgress();

    }

    private List<Lecture> createTestData() {
        List<Lecture> testData = new ArrayList<>();
        testData.add(new Lecture(0, "31.10.2019", "MVP", "Leonidov", Arrays.asList("MVP", "Tests")));
        return testData;
    }
}
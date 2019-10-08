package com.github.rkhusainov.recyclermultipletypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseListProvider {
    public List<Lecture> mLectures = Arrays.asList(
            new Lecture("1", "24.09.2019", "Вводное занятие", "Соколов"),
            new Lecture("2", "26.09.2019", "View, Layouts", "Соколов"),
            new Lecture("3", "28.09.2019", "Drawables", "Соколов"),
            new Lecture("4", "01.10.2019", "Activity", "Сафарян"),
            new Lecture("5", "03.10.2019", "Адаптеры", "Чумак"),
            new Lecture("6", "05.10.2019", "UI: практика", "Кудрявцев"),
            new Lecture("7", "08.10.2019", "Custom View", "Кудрявцев"),
            new Lecture("8", "10.10.2019", "Touch Events", "Бильчук"),
            new Lecture("9", "12.10.2019", "Сложные жесты", "Соколов"),
            new Lecture("10", "15.10.2019", "Layout & Measurement", "Кудрявцев"),
            new Lecture("11", "17.10.2019", "Custom ViewGroup", "Кудрявцев"),
            new Lecture("12", "19.10.2019", "Анимации", "Чумак"),
            new Lecture("13", "22.10.2019", "Практика View", "Соколов"),
            new Lecture("14", "24.10.2019", "Фрагменты: база", "Бильчук"),
            new Lecture("15", "26.10.2019", "Фрагменты: практика", "Соколов"),
            new Lecture("16", "29.10.2019", "Фоновая работа", "Чумак"),
            new Lecture("17", "31.10.2019", "Абстракции фон/UI", "Леонидов"),
            new Lecture("18", "5.11.2019", "Фон: практика", "Чумак"),
            new Lecture("19", "7.11.2019", "BroadcastReceiver", "Бильчук"),
            new Lecture("20", "9.11.2019", "Runtime permissions", "Кудрявцев"),
            new Lecture("21", "12.11.2019", "Service", "Леонидов"),
            new Lecture("22", "14.11.2019", "Service: практика", "Леонидов"),
            new Lecture("23", "16.11.2019", "Service: биндинг", "Леонидов"),
            new Lecture("24", "19.11.2019", "Preferences", "Сафарян"),
            new Lecture("25", "21.11.2019", "SQLite", "Бильчук"),
            new Lecture("26", "23.11.2019", "SQLite: Room", "Соколов"),
            new Lecture("27", "26.11.2019", "ContentProvider", "Сафарян"),
            new Lecture("28", "28.11.2019", "FileProvider", "Соколов"),
            new Lecture("29", "30.11.2019", "Геолокация", "Леонидов"),
            new Lecture("30", "3.12.2019", "Material", "Чумак"),
            new Lecture("31", "5.12.2019", "UI-тесты", "Сафарян"),
            new Lecture("32", "7.12.2019", "Финал", "Соколов")
    );

    public List<Object> getWeekLectures() {
        List<Object> items = new ArrayList<>();
        items.add("Week 1");
        items.add(new Lecture("1", "24.09.2019", "Вводное занятие", "Соколов"));
        items.add(new Lecture("2", "26.09.2019", "View, Layouts", "Соколов"));
        items.add(new Lecture("3", "28.09.2019", "Drawables", "Соколов"));
        items.add("Week 2");
        items.add(new Lecture("4", "01.10.2019", "Activity", "Сафарян"));
        items.add(new Lecture("5", "03.10.2019", "Адаптеры", "Чумак"));
        items.add(new Lecture("6", "05.10.2019", "UI: практика", "Кудрявцев"));
        items.add("Week 3");
        items.add(new Lecture("7", "08.10.2019", "Custom View", "Кудрявцев"));
        items.add(new Lecture("8", "10.10.2019", "Touch Events", "Бильчук"));
        items.add(new Lecture("9", "12.10.2019", "Сложные жесты", "Соколов"));
        items.add("Week 4");
        items.add(new Lecture("10", "15.10.2019", "Layout & Measurement", "Кудрявцев"));
        items.add(new Lecture("11", "17.10.2019", "Custom ViewGroup", "Кудрявцев"));
        items.add(new Lecture("12", "19.10.2019", "Анимации", "Чумак"));
        items.add("Week 5");
        items.add(new Lecture("13", "22.10.2019", "Практика View", "Соколов"));
        items.add(new Lecture("14", "24.10.2019", "Фрагменты: база", "Бильчук"));
        items.add(new Lecture("15", "26.10.2019", "Фрагменты: практика", "Соколов"));
        items.add("Week 6");
        items.add(new Lecture("16", "29.10.2019", "Фоновая работа", "Чумак"));
        items.add(new Lecture("17", "31.10.2019", "Абстракции фон/UI", "Леонидов"));
        items.add(new Lecture("18", "5.11.2019", "Фон: практика", "Чумак"));
        items.add("Week 7");
        items.add(new Lecture("19", "7.11.2019", "BroadcastReceiver", "Бильчук"));
        items.add(new Lecture("20", "9.11.2019", "Runtime permissions", "Кудрявцев"));
        items.add(new Lecture("21", "12.11.2019", "Service", "Леонидов"));
        items.add("Week 8");
        items.add(new Lecture("22", "14.11.2019", "Service: практика", "Леонидов"));
        items.add(new Lecture("23", "16.11.2019", "Service: биндинг", "Леонидов"));
        items.add(new Lecture("24", "19.11.2019", "Preferences", "Сафарян"));
        items.add("Week 9");
        items.add(new Lecture("25", "21.11.2019", "SQLite", "Бильчук"));
        items.add(new Lecture("26", "23.11.2019", "SQLite: Room", "Соколов"));
        items.add(new Lecture("27", "26.11.2019", "ContentProvider", "Сафарян"));
        items.add("Week 10");
        items.add(new Lecture("28", "28.11.2019", "FileProvider", "Соколов"));
        items.add(new Lecture("29", "30.11.2019", "Геолокация", "Леонидов"));
        items.add(new Lecture("30", "3.12.2019", "Material", "Чумак"));
        items.add("Week 11");
        items.add(new Lecture("31", "5.12.2019", "UI-тесты", "Сафарян"));
        items.add(new Lecture("32", "7.12.2019", "Финал", "Соколов"));
        return items;
    }


    public List<Lecture> provideLectures() {
        return mLectures;
    }

    public List<Object> provideWeekLectures() {
        return getWeekLectures();
    }

    public List<String> provideLectors() {
        Set<String> lectorSet = new HashSet<>();
        for (Lecture lecture : mLectures) {
            lectorSet.add(lecture.getLector());
        }
        return new ArrayList<>(lectorSet);
    }

    public List<Lecture> lectureFilterBy(String lectorName) {
        List<Lecture> result = new ArrayList<>();
        for (Lecture lecture : mLectures) {
            if (lecture.getLector().equals(lectorName)) {
                result.add(lecture);
            }
        }
        return new ArrayList<>(result);
    }
}

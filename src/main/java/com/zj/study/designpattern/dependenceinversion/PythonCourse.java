package com.zj.study.designpattern.dependenceinversion;

/**
 * Created by geely
 */
public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Geely在学习Python课程");
    }
}

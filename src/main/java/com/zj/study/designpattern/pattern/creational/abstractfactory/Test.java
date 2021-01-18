package com.zj.study.designpattern.pattern.creational.abstractfactory;

/**
 * Created by geely
 * <br>
 * <br>
 * Sample:
 * <p>
 * {@link java.sql.Connection}
 * {@link org.apache.ibatis.session.SqlSessionFactory}
 * >/p>
 * 
 */
public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }
}

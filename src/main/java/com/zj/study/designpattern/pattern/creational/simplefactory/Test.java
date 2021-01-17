package com.zj.study.designpattern.pattern.creational.simplefactory;

/**
 * Created by geely
 * 
 * 简单工厂实现
 * 
 * 相关
 * JDK：Calendar
 * Logback:LoggerFactory
 */
public class Test {
    public static void main(String[] args) {
//        VideoFactory videoFactory = new VideoFactory();
//        Video video = videoFactory.getVideo("java");
//        if(video == null){
//            return;
//        }
//        video.produce();

        VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo(JavaVideo.class);
        if(video == null){
            return;
        }
        video.produce();



    }

}

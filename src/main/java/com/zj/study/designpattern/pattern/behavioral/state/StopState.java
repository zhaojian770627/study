package com.zj.study.designpattern.pattern.behavioral.state;

public class StopState extends CourseVideoState {

	@Override
	public void play() {
		super.courseVideoContext.setCourseVideoState(CourseVideoContext.PLAY_STATE);
	}

	@Override
	public void speed() {
		System.out.println("ERROR 停止状态不能快进");
	}

	@Override
	public void pause() {
		System.out.println("ERROR 停止状态不能暂停");
	}

	@Override
	public void stop() {
		System.out.println("正常停止课程视频状态");
	}

}

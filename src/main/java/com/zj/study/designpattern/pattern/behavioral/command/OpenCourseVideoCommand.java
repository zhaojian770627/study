package com.zj.study.designpattern.pattern.behavioral.command;

public class OpenCourseVideoCommand implements Command {
	private CourseVideo courseVideo;
	public OpenCourseVideoCommand(CourseVideo courseVideo) {
		super();
		this.courseVideo = courseVideo;
	}

	@Override
	public void execute() {
		courseVideo.open();
	}

}

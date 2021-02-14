package com.zj.study.designpattern.pattern.behavioral.command;

public class CloseCourseVideoCommand implements Command {
	private CourseVideo courseVideo;

	public CloseCourseVideoCommand(CourseVideo courseVideo) {
		super();
		this.courseVideo = courseVideo;
	}

	@Override
	public void execute() {
		courseVideo.close();
	}

}

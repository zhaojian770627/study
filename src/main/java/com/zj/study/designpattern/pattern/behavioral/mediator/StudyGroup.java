package com.zj.study.designpattern.pattern.behavioral.mediator;

import java.util.Date;

/**
 * 中介者 
 * 
 * @author zhaojian
 *
 */
public class StudyGroup {
	public static void showMessage(User user, String message) {
		System.out.println(new Date().toString() + " [" + user.getName() + "] " + message);
	}
}

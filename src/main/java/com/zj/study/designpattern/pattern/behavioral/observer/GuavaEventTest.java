package com.zj.study.designpattern.pattern.behavioral.observer;

import com.google.common.eventbus.EventBus;

public class GuavaEventTest {

	public static void main(String[] args) {
		EventBus eventBus = new EventBus();
		GuavaEvent guavaEvent = new GuavaEvent();
		eventBus.register(guavaEvent);
		eventBus.post("post的内容");
	}

}

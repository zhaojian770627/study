// Generated by RevEngAPI for class io.netty.handler.traffic.TrafficCounter
package io.netty.handler.traffic;

public class TrafficCounter {

	// Constructors
	public TrafficCounter(java.util.concurrent.ScheduledExecutorService arg0, java.lang.String arg1, long arg2) {
	}	public TrafficCounter(io.netty.handler.traffic.AbstractTrafficShapingHandler arg0, java.util.concurrent.ScheduledExecutorService arg1, java.lang.String arg2, long arg3) {
	}
	// Methods
	public long cumulativeWrittenBytes() {
	return 0;
	}
	public long lastTime() {
	return 0;
	}
	public long cumulativeReadBytes() {
	return 0;
	}
	public long lastCumulativeTime() {
	return 0;
	}
	public long lastWriteThroughput() {
	return 0;
	}
	public long lastReadThroughput() {
	return 0;
	}
	public long currentWrittenBytes() {
	return 0;
	}
	public long currentReadBytes() {
	return 0;
	}
	public long lastWrittenBytes() {
	return 0;
	}
	public long lastReadBytes() {
	return 0;
	}
	public long checkInterval() {
	return 0;
	}
	public static long milliSecondFromNano() {
	return 0;
	}
	synchronized void resetAccounting(long arg0) {
	return 0;
	}
	public void configure(long arg0) {
	return 0;
	}
	void bytesRecvFlowControl(long arg0) {
	return 0;
	}
	void bytesWriteFlowControl(long arg0) {
	return 0;
	}
	void bytesRealWriteFlowControl(long arg0) {
	return 0;
	}
	public class java.util.concurrent.atomic.AtomicLong getRealWrittenBytes() {
	return 0;
	}
	public long getRealWriteThroughput() {
	return 0;
	}
	public void resetCumulativeTime() {
	return 0;
	}
	public long readTimeToWait(long arg0, long arg1, long arg2) {
	return 0;
	}
	public long readTimeToWait(long arg0, long arg1, long arg2, long arg3) {
	return 0;
	}
	public long writeTimeToWait(long arg0, long arg1, long arg2) {
	return 0;
	}
	public long writeTimeToWait(long arg0, long arg1, long arg2, long arg3) {
	return 0;
	}
	public class java.lang.String name() {
	return 0;
	}
	public class java.lang.String toString() {
	return 0;
	}
	public synchronized void start() {
	return 0;
	}
	public synchronized void stop() {
	return 0;
	}

	// Fields
	final java.util.concurrent.atomic.AtomicLong lastTime; // java.lang.IllegalAccessException: Class com.zj.study.java2s.reflection.classes.RevEngAPI can not access a member of class io.netty.handler.traffic.TrafficCounter with modifiers "final";
	final java.util.concurrent.atomic.AtomicLong checkInterval; // java.lang.IllegalAccessException: Class com.zj.study.java2s.reflection.classes.RevEngAPI can not access a member of class io.netty.handler.traffic.TrafficCounter with modifiers "final";
	final java.lang.String name; // java.lang.IllegalAccessException: Class com.zj.study.java2s.reflection.classes.RevEngAPI can not access a member of class io.netty.handler.traffic.TrafficCounter with modifiers "final";
	final io.netty.handler.traffic.AbstractTrafficShapingHandler trafficShapingHandler; // java.lang.IllegalAccessException: Class com.zj.study.java2s.reflection.classes.RevEngAPI can not access a member of class io.netty.handler.traffic.TrafficCounter with modifiers "final";
	final java.util.concurrent.ScheduledExecutorService executor; // java.lang.IllegalAccessException: Class com.zj.study.java2s.reflection.classes.RevEngAPI can not access a member of class io.netty.handler.traffic.TrafficCounter with modifiers "final";
	java.lang.Runnable monitor;
	volatile java.util.concurrent.ScheduledFuture scheduledFuture;
	volatile boolean monitorActive;
}

package com.zj.study.zookeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SerialDemo {

	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
		Student student = new Student("zyl", 11, 7);
		String path = "D:\\tmp\\student";
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
			oos.writeObject(student);
		} finally {
			oos.close();
		}
		ObjectInputStream ois = null;
		Student obj;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(path)));
			obj = (Student) ois.readObject();
		} finally {
			oos.close();
		}

		System.out.println("obj:" + obj);
	}

}

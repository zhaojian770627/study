package com.zj.study.zookeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.junit.Test;

public class SerialDemo {

	@Test
	public void testJavaS() throws FileNotFoundException, IOException, ClassNotFoundException {
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

	@Test
	public void testJute() throws IOException {
		Student student = new Student("zyl", 11, 7);
		String path = "D:\\tmp\\student";
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(new File(path));
			BinaryOutputArchive binaryOutputArchive = BinaryOutputArchive.getArchive(outputStream);
			binaryOutputArchive.writeString(student.getUserName(), "username");
			binaryOutputArchive.writeInt(student.getAge(), "age");
			binaryOutputArchive.writeInt(student.getGrade(), "grade");
			outputStream.flush();
			outputStream.close();
			inputStream = new FileInputStream(new File(path));
			BinaryInputArchive binaryInputArchive = BinaryInputArchive.getArchive(inputStream);
			Student stu = new Student();
			stu.setUserName(binaryInputArchive.readString("username"));
			stu.setAge(binaryInputArchive.readInt("age"));
			stu.setGrade(binaryInputArchive.readInt("grade"));
			System.out.println("obj:" + stu);
		} finally {
			inputStream.close();
			outputStream.close();
		}
	}

}

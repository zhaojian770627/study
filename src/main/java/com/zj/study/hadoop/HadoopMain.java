package com.zj.study.hadoop;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopMain {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = null;
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://hadoop1:9000/");
		fs = FileSystem.get(new URI("hdfs://hadoop1:9000/"), conf, "hadoop");
		Path dst = new Path("hdfs://hadoop1:9000/aaa");
		FSDataOutputStream os = fs.create(dst);
		FileInputStream is = new FileInputStream("/home/zj/aaa");
		IOUtils.copy(is, os);
		os.close();
		is.close();
	}

}

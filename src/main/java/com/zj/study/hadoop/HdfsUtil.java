package com.zj.study.hadoop;

import java.io.FileInputStream;
import java.net.URI;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

public class HdfsUtil {

	FileSystem fs = null;

	@Before
	public void init() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://hadoop1:9000/");
		conf.set("dfs.replication", "1");
		fs = FileSystem.get(new URI("hdfs://hadoop1:9000/"), conf, "hadoop");
	}

	@Test
	public void upload() throws Exception {
		Path dst = new Path("hdfs://hadoop1:9000/aa/aaa.txt");
		FSDataOutputStream os = fs.create(dst);
		FileInputStream is = new FileInputStream("/home/zj/aaa");
		IOUtils.copy(is, os);
		// os.close();
		// is.close();
	}

	@Test
	public void upload2() throws Exception {
		fs.copyFromLocalFile(new Path("/home/zj/bpb.txt"), new Path("hdfs://hadoop1:9000/bpb.txt"));
	}

	@Test
	public void list() throws Exception {
		FileStatus[] fst = fs.listStatus(new Path("hdfs://hadoop1:9000/"));
		for (FileStatus f : fst) {
			System.out.println(f.getPath().getName());
		}
	}

	@Test
	public void rm() throws Exception {
		System.out.println(fs.delete(new Path("hdfs://hadoop1:9000/aa"), true));
	}

	@Test
	public void mkdir() throws Exception {
		fs.mkdirs(new Path("hdfs://hadoop1:9000/aaa"));
	}

	@Test
	public void download() throws Exception {
		fs.copyToLocalFile(new Path("hdfs://hadoop1:9000/bpb.txt"), new Path("/home/zj/temp/bpb.txt"));
	}
}

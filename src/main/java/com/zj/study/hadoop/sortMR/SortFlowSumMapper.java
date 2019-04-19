package com.zj.study.hadoop.sortMR;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortFlowSumMapper extends Mapper<LongWritable, Text, FlowBean, NullWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		String[] fields = StringUtils.split(line, "\t");

		String phoneNB = fields[0];
		long u_flow = Long.parseLong(fields[1]);
		long d_flow = Long.parseLong(fields[2]);

		context.write(new FlowBean(phoneNB, u_flow, d_flow), NullWritable.get());
	}
}

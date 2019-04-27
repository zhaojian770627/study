package com.zj.study.hadoop.ii;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class InverseIndexStepOne extends Configured implements Tool {

	public static class StepOneMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String line = value.toString();
			String[] fields = StringUtils.split(line, " ");

			FileSplit inputSplit = (FileSplit) context.getInputSplit();
			String fileName = inputSplit.getPath().getName();

			for (String field : fields) {
				context.write(new Text(field + "-->" + fileName), new LongWritable(1));
			}
		}
	}

	public static class StepOneReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

		@Override
		protected void reduce(Text key, Iterable<LongWritable> values, Context context)
				throws IOException, InterruptedException {

			long counter = 0;
			for (LongWritable value : values) {
				counter += value.get();
			}
			context.write(key, new LongWritable(counter));

		}
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		job.setJarByClass(InverseIndexStepOne.class);

		job.setMapperClass(StepOneMapper.class);
		job.setReducerClass(StepOneReduce.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));

		Path output = new Path(args[1]);
		FileSystem fs = FileSystem.get(conf);
		if (fs.exists(output)) {
			fs.delete(output, true);
		}

		FileOutputFormat.setOutputPath(job, output);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int code = ToolRunner.run(new Configuration(), new InverseIndexStepOne(), args);
		System.exit(code);
	}

}

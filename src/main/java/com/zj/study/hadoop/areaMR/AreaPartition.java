package com.zj.study.hadoop.areaMR;

import java.util.HashMap;

import org.apache.hadoop.mapreduce.Partitioner;

public class AreaPartition<KEY, VALUE> extends Partitioner<KEY, VALUE> {

	private static HashMap<String, Integer> areaMap = new HashMap<>();

	static {
		areaMap.put("186", 0);
		areaMap.put("188", 1);
	}

	@Override
	public int getPartition(KEY key, VALUE value, int numPartitions) {
		int areaCode = areaMap.get(key.toString().substring(0, 3));
		return areaCode;
	}

}

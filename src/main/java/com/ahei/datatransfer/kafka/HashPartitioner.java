package com.ahei.datatransfer.kafka;

import java.util.Random;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class HashPartitioner implements Partitioner {

	private static final Random random = new Random();

	public HashPartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		int result = 0;
		if (key == null) {
			result = random.nextInt(numPartitions);
		} else {
			result = Math.abs(key.hashCode()) % numPartitions;
		}
		System.out.println("partition:"+result);
		return result;
	}

}
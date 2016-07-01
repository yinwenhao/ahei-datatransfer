package com.when_how.datatransfer.framwork;

import com.when_how.datatransfer.kafka.DataConsumer;

public class TaskListener {
	
	private DataConsumer dataConsumer;
	
	public void init() {
		dataConsumer.startConsume(1, "key");
	}

}

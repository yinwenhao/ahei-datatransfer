package com.when_how.datatransfer.kafka;

public interface DataConsumer {

	public void startConsume(int numThreads, String key);

}

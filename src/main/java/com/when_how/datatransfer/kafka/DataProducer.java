package com.when_how.datatransfer.kafka;

public interface DataProducer {

	public void sendMessage(String topic, String key, String value);

}

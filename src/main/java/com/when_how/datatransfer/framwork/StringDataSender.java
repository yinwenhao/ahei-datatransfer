package com.when_how.datatransfer.framwork;

import com.when_how.datatransfer.kafka.DataProducer;

public class StringDataSender implements DataSender {

	private DataProducer dataProducer;

	private DataManager<String> dataManager;

	private String key;

	private String topic;

	@Override
	public void sendData() {
		String data = dataManager.getData();
		dataProducer.sendMessage(topic, key, data);
	}

	public DataProducer getDataProducer() {
		return dataProducer;
	}

	public void setDataProducer(DataProducer dataProducer) {
		this.dataProducer = dataProducer;
	}

	public DataManager<String> getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager<String> dataManager) {
		this.dataManager = dataManager;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}

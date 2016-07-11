package com.ahei.datatransfer.kafka;

import com.ahei.datatransfer.framwork.MessageSender;

/**
 * 使用kafka的MessageSender实现
 * 
 * @author yinwenhao
 *
 */
public class KafkaMessageSender implements MessageSender {

	private DataProducer dataProducer;

	public KafkaMessageSender(String metadataBrokerList) {
		dataProducer = new DataProducerImpl(metadataBrokerList);
	}

	@Override
	public void sendMessage(String topic, String key, String value) {
		dataProducer.sendMessage(topic, key, value);
	}

}

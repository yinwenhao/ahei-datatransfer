package com.ahei.datatransfer.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerMsgTask implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());

	private KafkaStream<String, String> m_stream;
	private int m_threadNumber;

	private Map<String, MessageConsumer> consumerMap;

	public ConsumerMsgTask(KafkaStream<String, String> stream, int threadNumber,
			Map<String, MessageConsumer> consumerToGetDataMap) {
		this.m_threadNumber = threadNumber;
		this.m_stream = stream;
		this.consumerMap = consumerToGetDataMap;
	}

	public void run() {
		ConsumerIterator<String, String> it = m_stream.iterator();
		while (it.hasNext()) {
			MessageAndMetadata<String, String> mam = it.next();
			if (consumerMap.containsKey(mam.key())) {
				log.info("Thread " + m_threadNumber + ": " + mam.message());
				consumerMap.get(mam.key()).doConsume(mam);
				// System.out.println("Thread " + m_threadNumber + ": " +
				// mam.message());
			}
		}
		log.info("Shutting down Thread: " + m_threadNumber);
	}
}
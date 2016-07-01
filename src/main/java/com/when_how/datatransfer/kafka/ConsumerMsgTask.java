package com.when_how.datatransfer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerMsgTask implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());

	private KafkaStream<String, String> m_stream;
	private int m_threadNumber;
	private String key;
	private Consumer consumerToGetData;

	public ConsumerMsgTask(KafkaStream<String, String> stream,
			int threadNumber, String key) {
		m_threadNumber = threadNumber;
		m_stream = stream;
		this.key = key;
	}

	public void run() {
		ConsumerIterator<String, String> it = m_stream.iterator();
		while (it.hasNext()) {
			MessageAndMetadata<String, String> mam = it.next();
			if (key.equals(mam.key())) {
				log.info("Thread " + m_threadNumber + ": " + mam.message());
				consumerToGetData.doConsume(mam);
//				System.out.println("Thread " + m_threadNumber + ": " + mam.message());
			}
		}
		log.info("Shutting down Thread: " + m_threadNumber);
	}
}
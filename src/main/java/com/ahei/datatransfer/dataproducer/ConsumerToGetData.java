package com.ahei.datatransfer.dataproducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ahei.datatransfer.framwork.MessageSender;
import com.ahei.datatransfer.kafka.MessageConsumer;
import com.ahei.datatransfer.serialize.JacksonMapper;

import kafka.message.MessageAndMetadata;

/**
 * 数据生产方消费定时任务消息，需要拿数据，并将数据发送到消息队列
 * 
 * @author yinwenhao
 *
 */
public class ConsumerToGetData implements MessageConsumer {

	private Logger log = LoggerFactory.getLogger(getClass());

	private String key;

	private String topic;

	private DataToSend dataToSend;

	private MessageSender messageSender;

	@SuppressWarnings("unchecked")
	@Override
	public void doConsume(MessageAndMetadata<String, String> mam) {
		// 查数据，发到消息队列
		Map<String, Object> param = null;
		if (mam.message() != null && !mam.message().isEmpty()) {
			try {
				param = JacksonMapper.objectMapper.readValue(mam.message(), HashMap.class);
			} catch (IOException e) {
				log.error("param json error from String:" + mam.message() + ", key:" + mam.key(), e);
			}
		}

		String message = dataToSend.getDataToSend(param);
		messageSender.sendMessage(topic, key, message);
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

	public DataToSend getDataToSend() {
		return dataToSend;
	}

	public void setDataToSend(DataToSend dataToSend) {
		this.dataToSend = dataToSend;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

}

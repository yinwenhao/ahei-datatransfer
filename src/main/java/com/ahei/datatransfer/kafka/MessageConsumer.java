package com.ahei.datatransfer.kafka;

import kafka.message.MessageAndMetadata;

/**
 * 消费消息队列消息的，数据提供方需要消费定时任务消息，数据消费方需要消费数据消息
 * 
 * @author yinwenhao
 *
 */
public interface MessageConsumer {

	public void doConsume(MessageAndMetadata<String, String> mam);

}

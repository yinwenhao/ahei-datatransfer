package com.ahei.datatransfer.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * 详细可以参考：https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+Producer+
 * Example
 * 
 * @author yinwenhao
 *
 */
public class DataProducerImpl implements DataProducer {

	private Producer<String, String> producer;

	public DataProducerImpl(String metadataBrokerList, String serializerClass,
			String keySerializerClass, String partitionerClass,
			int requestRequiredAcks) {
		// 设置配置属性
		Properties props = new Properties();
		props.put("metadata.broker.list", metadataBrokerList);
		props.put("serializer.class", serializerClass);
		// key.serializer.class默认为serializer.class
		props.put("key.serializer.class", keySerializerClass);
		// 可选配置，如果不配置，则使用默认的partitioner
		props.put("partitioner.class", partitionerClass);
		// 值为0,1,-1,可以参考
		// http://kafka.apache.org/08/configuration.html
		props.put("request.required.acks", String.valueOf(requestRequiredAcks));
		ProducerConfig config = new ProducerConfig(props);
		// 创建producer
		producer = new Producer<String, String>(config);
	}

	public DataProducerImpl(String metadataBrokerList) {
		// 设置配置属性
		Properties props = new Properties();
		props.put("metadata.broker.list", metadataBrokerList);
		props.put("serializer.class", "com.when_how.datatransfer.compress.GzipEncoder");
		// key.serializer.class默认为serializer.class
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		// 可选配置，如果不配置，则使用默认的partitioner
		props.put("partitioner.class", "com.when_how.datatransfer.kafka.HashPartitioner");
		// 值为0,1,-1,可以参考
		// http://kafka.apache.org/08/configuration.html
		props.put("request.required.acks", String.valueOf(1));
		ProducerConfig config = new ProducerConfig(props);
		// 创建producer
		producer = new Producer<String, String>(config);
	}

	public void close() {
		if (producer != null) {
			producer.close();
		}
	}

	@Override
	public void sendMessage(String topic, String key, String value) {
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(
				topic, key, value);
		producer.send(data);
	}

}

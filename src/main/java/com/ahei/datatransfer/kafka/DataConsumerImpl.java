package com.ahei.datatransfer.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ahei.datatransfer.compress.GzipDecoder;
import com.ahei.datatransfer.framwork.DataConsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

/**
 * 详细可以参考：https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+
 * Example
 * 
 * @author yinwenhao
 *
 */
public class DataConsumerImpl implements DataConsumer {
	private final ConsumerConnector consumer;
	private final String topic;
	private ExecutorService executor;

	private Map<String, MessageConsumer> consumerToGetDataMap;

	public DataConsumerImpl(String a_zookeeper, String a_groupId, String a_topic,
			Map<String, MessageConsumer> consumerToGetDataMap) {
		consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper, a_groupId));
		this.topic = a_topic;
		this.consumerToGetDataMap = consumerToGetDataMap;
	}

	public void shutdown() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
	}

	@Override
	public void startConsume() {
		int numThreads = 1;
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(numThreads));
		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap,
				new StringDecoder(new VerifiableProperties()), new GzipDecoder());
		List<KafkaStream<String, String>> streams = consumerMap.get(topic);

		// now launch all the threads
		executor = Executors.newFixedThreadPool(numThreads);

		// now create an object to consume the messages
		int threadNumber = 0;
		for (final KafkaStream<String, String> stream : streams) {
			executor.execute(new ConsumerMsgTask(stream, threadNumber, consumerToGetDataMap));
			threadNumber++;
		}
		consumer.commitOffsets();
	}

	private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
		Properties props = new Properties();
		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}

}
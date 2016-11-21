package com.ahei.datatransfer.dataproducer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;

import com.ahei.datatransfer.framwork.DataConsumer;
import com.ahei.datatransfer.kafka.DataConsumerImpl;
import com.ahei.datatransfer.kafka.KafkaMessageSender;
import com.ahei.datatransfer.kafka.MessageConsumer;

public class DataProducerListener implements InitializingBean {

	private String kafkaZookeeper;

	private String kafkaGroupId;

	private String kafkaTopic;

	private Map<String, DataManager<?>> dataManagerMap;

	private String metadataBrokerList;

	public DataProducerListener(String kafkaZookeeper, String kafkaGroupId, String kafkaTopic,
			Map<String, DataManager<?>> dataManagerMap, String metadataBrokerList) {
		this.kafkaZookeeper = kafkaZookeeper;
		this.kafkaGroupId = kafkaGroupId;
		this.kafkaTopic = kafkaTopic;
		this.dataManagerMap = dataManagerMap;
		this.metadataBrokerList = metadataBrokerList;
	}

	private void init() {
		Map<String, MessageConsumer> consumerToGetDataMap = new HashMap<String, MessageConsumer>();
		for (Entry<String, DataManager<?>> en : dataManagerMap.entrySet()) {
			ConsumerToGetData mc = new ConsumerToGetData();
			mc.setKey(en.getKey() + "-data");
			mc.setTopic(kafkaTopic);
			mc.setMessageSender(new KafkaMessageSender(metadataBrokerList));
			mc.setDataToSend(new DataToSendImpl(en.getValue()));
			consumerToGetDataMap.put(en.getKey(), mc);
		}
		DataConsumer dataConsumer = new DataConsumerImpl(kafkaZookeeper, kafkaGroupId, kafkaTopic,
				consumerToGetDataMap);
		dataConsumer.startConsume();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}

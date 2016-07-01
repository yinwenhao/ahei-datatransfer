package com.when_how.datatransfer.framwork.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kafka.message.MessageAndMetadata;

import com.when_how.datatransfer.framwork.DataSender;
import com.when_how.datatransfer.kafka.Consumer;

public class ConsumerToGetData implements Consumer {

	@Autowired
	private DataSender stringDataSender;

	@Override
	public void doConsume(MessageAndMetadata<String, String> mam) {
		// TODO 查数据，发到kafka
		stringDataSender.sendData();
	}

}

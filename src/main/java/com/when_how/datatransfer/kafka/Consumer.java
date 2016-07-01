package com.when_how.datatransfer.kafka;

import kafka.message.MessageAndMetadata;

public interface Consumer {

	public void doConsume(MessageAndMetadata<String, String> mam);

}

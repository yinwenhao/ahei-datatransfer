package com.ahei.datatransfer.framwork;

public interface MessageSender {
	
	public void sendMessage(String topic, String key, String value);

}

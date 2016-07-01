package com.when_how.datatransfer.kafka;

import com.when_how.datatransfer.util.GZipUtils;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class GzipEncoder implements Encoder<String> {

	/**
	 * kafka要求必须要这个构造函数
	 * 
	 * @param props
	 */
	public GzipEncoder(VerifiableProperties props) {
	}

	@Override
	public byte[] toBytes(String msg) {
		try {
			return GZipUtils.compress(msg.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return msg.getBytes();
		}
	}

}

package com.when_how.datatransfer.kafka;

import com.when_how.datatransfer.util.GZipUtils;

import kafka.serializer.Decoder;

public class GzipDecoder implements Decoder<String> {

	@Override
	public String fromBytes(byte[] msg) {
		try {
			return new String(GZipUtils.decompress(msg));
		} catch (Exception e) {
			e.printStackTrace();
			return new String(msg);
		}
	}

}

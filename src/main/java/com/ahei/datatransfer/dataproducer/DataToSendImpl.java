package com.ahei.datatransfer.dataproducer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ahei.datatransfer.serialize.JacksonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DataToSendImpl implements DataToSend {

	private Logger log = LoggerFactory.getLogger(getClass());

	private DataManager<?> dataManager;

	public DataToSendImpl(DataManager<?> dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public String getDataToSend(Map<String, Object> param) {
		Object data = dataManager.getDataToTransfer(param);
		try {
			return JacksonMapper.objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			log.error("get data to string error", e);
		}
		return null;
	}

}

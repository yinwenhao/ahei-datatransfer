package com.ahei.datatransfer.dataproducer;

import java.util.List;
import java.util.Map;

public interface DataManager<T> {

	public List<T> getDataToTransfer(Map<String, Object> param);

}

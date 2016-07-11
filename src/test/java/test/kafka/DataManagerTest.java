package test.kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ahei.datatransfer.kafka.DataProducerImpl;

public class DataManagerTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dataTransfer.xml");
		
		DataProducerImpl kafkaProducer = context.getBean(DataProducerImpl.class);
		// 产生并发送消息
		kafkaProducer.sendMessage("lang",  "data_transfer_test", "{\"modifyTime\":\"2016-07-03 15:00:00\",\"limit\":2}");
		// 关闭producer
		kafkaProducer.close();
	}

}

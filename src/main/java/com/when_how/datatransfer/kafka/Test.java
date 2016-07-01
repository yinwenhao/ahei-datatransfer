package com.when_how.datatransfer.kafka;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-main.xml");
		DataProducerImpl kafkaProducer = context.getBean(DataProducerImpl.class);
		int events = 15;
		// 产生并发送消息
		long start = System.currentTimeMillis();
		for (long i = 0; i < events; i++) {
			long runtime = new Date().getTime();
			String ip = "192.168.2." + i;// rnd.nextInt(255);
			String msg = runtime + ",www.example.com," + ip;
			kafkaProducer.sendMessage("lang",  "192.168.2.1", msg);
		}
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
		// 关闭producer
		kafkaProducer.close();
	}

}

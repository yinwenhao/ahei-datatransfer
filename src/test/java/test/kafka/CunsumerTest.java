package test.kafka;

import com.ahei.datatransfer.kafka.DataConsumerImpl;

public class CunsumerTest {

	public static void main(String[] arg) {
		String[] args = { "10.128.8.57:2181", "group-testywh2", "lang", "1" };
		String zooKeeper = args[0];
		String groupId = args[1];
		String topic = args[2];
		int threads = Integer.parseInt(args[3]);

//		DataConsumerImpl demo = new DataConsumerImpl(zooKeeper, groupId, topic);
//		demo.startConsume(threads);
//
//		try {
//			Thread.currentThread().join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		demo.shutdown();
	}

}

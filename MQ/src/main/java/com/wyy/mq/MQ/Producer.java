package com.wyy.mq.MQ;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 消息发送者
 * @author wyy
 *
 */
public class Producer {

	public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("mfp");
		producer.setNamesrvAddr("192.168.253.101:9876");
		producer.start();
		Message msg1 = new Message("mfp","满福朋是不是傻逼".getBytes());
	
		producer.sendOneway(msg1);;
		producer.shutdown();
		
	}
}

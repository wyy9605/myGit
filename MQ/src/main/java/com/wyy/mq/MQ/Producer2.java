package com.wyy.mq.MQ;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 消息发送者
 * @author wyy
 *
 */
public class Producer2 {

	public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("mfp");
		producer.setNamesrvAddr("192.168.253.101:9876");
		producer.start();
		List<Message> list = new ArrayList<Message>();
		Message msg1 = new Message("mfp","满福朋是不是傻逼".getBytes());
		Message msg2 = new Message("mfp","满福朋是傻逼".getBytes());
		Message msg3 = new Message("mfp","陈永栋也是傻逼".getBytes());

		list.add(msg1);
		list.add(msg2);
		list.add(msg3);
		producer.send(list,new SendCallback() {
			
			public void onSuccess(SendResult sendResult) {
				System.out.println("消息发送成功");
				System.out.println("sendResult:"+sendResult);
				
			}
			
			public void onException(Throwable e) {
				System.out.println("消息发送异常");
				e.printStackTrace();
				
			}
		});
		
	}
}

package com.wyy.mq.MQ;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class Consumer {

	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mfp");
		consumer.setNamesrvAddr("192.168.253.101:9876");
		//过滤器 * 表示不过率
		consumer.subscribe("mfp", "*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for(MessageExt msg:msgs) {
					System.out.println(new String(msg.getBody()));
				}
				//默认情况下该消息只会被一个consumer消费 
				//message 状态修改
				//ack
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.setMessageModel(MessageModel.BROADCASTING);
		consumer.start();
	}

}

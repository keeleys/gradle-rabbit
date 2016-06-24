package com.ttianjun.config;

import com.ttianjun.listen.ListenDemo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @user tianjun
 * @date 16/6/24
 */
@Configuration
public class MessageConfig {
	@Value("${default-exchange}")
	private String exchange;

	private final String queueName="queue-fanout";
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, ListenDemo listenDemo) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(new MessageListenerAdapter(listenDemo,"handleMessage"));
		return container;
	}
}

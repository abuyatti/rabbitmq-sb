package com.example;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange exchange;

	@GetMapping("/fib/{n}")
	public void send(@PathVariable("n") String n) {
		System.out.println("Requesting fib(" + n + ")");
		Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), "rpc", n);
		System.out.println("Got " + response);
	}

}

package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Server {

	@RabbitListener(queues = "rpc.queue")
	public int fibonacci(int n) {
		System.out.println("Received request for " + n);
		int result = fib(n);
		System.out.println("Returned " + result);
		return result;
	}

	public int fib(int n) {
		return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
	}

}

package hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${default-exchange}")
	private String exchange;

	@Autowired
	AnnotationConfigApplicationContext context;

	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(exchange,null,"Hello from RabbitMQ!");
		context.close();
	}
}
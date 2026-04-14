package com.example.day11;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.example.day11.config.Car;
import lombok.Data;

@SpringBootApplication
public class Day11Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =SpringApplication.run(Day11Application.class, args);
		ApplicationProperties properties = context.getBean(ApplicationProperties.class);
		System.out.println("Application Name: " + properties.getAppName());
        // PaymentService paymentService = context.getBean("creditCardPaymentService", PaymentService.class);
		// paymentService.processPayment(100.0);
		// PaymentService creditCardPaymentService = context.getBean(PaymentService.class);
		// creditCardPaymentService.processPayment(200.0);

		Car car= context.getBean(Car.class);
		System.out.println("Model: " + car.brand);
		System.out.println("Model: " + car.model);
		System.out.println("Year: " + car.year);
		//System.out.println("Car: " + car.brand + " " + car.model + " " + car.year);
    }
}
@Component
@Data
class ApplicationProperties {
	@Value("${app.name}")
	private String appName;
}

interface PaymentService {
	void processPayment(double amount);
}

@Component
@Primary
class UpiPaymentService implements PaymentService {
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing UPI payment of amount: " + amount);
	}
}
@Component
class CreditCardPaymentService implements PaymentService {
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing Credit Card payment of amount: " + amount);
	}
}
// Bean class
// @Component
// @Lazy
// @Scope("prototype") // Default scope, can be omitted
// class Car {
//     public Car() {
//         System.out.println("Car created");
//     }
// }

// Runner class
@Component
class FirstRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("FirstRunner is running");
    }
}
//scope definition
//singleton: A single instance of the bean is created and shared across the entire application context. This is the default scope in Spring. When you define a bean without specifying a scope, it is treated as a singleton. Example: @Component public class MySingletonBean { ... }
//prototype: A new instance of the bean is created each time it is requested from the application context. This is useful when you want to have multiple instances of a bean with different states. Example: @Component @Scope("prototype") public class MyPrototypeBean { ... }		
//bean lifecycle: Spring manages the lifecycle of beans, which includes instantiation, dependency injection, initialization, and destruction. You can define custom initialization and destruction methods using @PostConstruct and @PreDestroy annotations, respectively. Example: @Component public class MyBean { @PostConstruct public void init() { ... } @PreDestroy public void destroy() { ... } }
//bean scopes and lifecycle management are essential concepts in Spring that allow you to control the creation and management of beans in your application. By understanding these concepts, you can design your application to be more efficient and maintainable.
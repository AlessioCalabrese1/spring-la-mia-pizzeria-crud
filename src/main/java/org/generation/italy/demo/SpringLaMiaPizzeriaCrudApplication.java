package org.generation.italy.demo;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "é una margherita", 5);
		Pizza p2 = new Pizza("Rustica", "patatine e salsiccia", 7);
		Pizza p3 = new Pizza("Siciliana", "margherita con melanzane", 8);
		
		pizzaServ.save(p1);
		pizzaServ.save(p2);
		pizzaServ.save(p3);
		
		List<Pizza> pizzas = pizzaServ.all();
		System.err.println(pizzas);
	}
}

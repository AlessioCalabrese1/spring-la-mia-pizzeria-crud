package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzeriaController {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizzas = pizzaServ.all();
		System.err.println("sono nel controller");
		System.err.println(pizzas);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("size", pizzas.size());
		return "Main";
	}
	
	@GetMapping("create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		return "Create";
	}
	
	@PostMapping("store")
	public String store(@Valid Pizza pizza) {
		
		pizzaServ.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("update/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		Optional<Pizza> pizza = pizzaServ.findPizzaById(id);
		model.addAttribute("pizza", pizza);
		
		return "Update";
	}
}

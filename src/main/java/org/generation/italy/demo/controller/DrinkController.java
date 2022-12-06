package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.DrinkServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drink")
public class DrinkController {
	@Autowired
	private DrinkServ drinkServ;
	
	@GetMapping
	public String index(Model model) {
		List<Drink> drinks = drinkServ.all();
		model.addAttribute("drinks", drinks);
		model.addAttribute("size", drinks.size());
		return "MainDrink";
	}
	
	@GetMapping("create")
	public String create(Model model) {
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "DrinkCreate";
	}
	
	@PostMapping("store")
	public String store(@Valid Drink drink) {
		
		drinkServ.save(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("update/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		Optional<Drink> drink = drinkServ.findDrinkById(id);
		model.addAttribute("drink", drink);
		
		return "DrinkUpdate";
	}
	
	
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		drinkServ.deleteById(id);
		return "redirect:/drink";
	}
	
	
}
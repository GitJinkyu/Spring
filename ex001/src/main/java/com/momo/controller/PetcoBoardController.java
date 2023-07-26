package com.momo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.momo.service.PetcoBoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PetcoBoardController {

	@Autowired
	PetcoBoardService service;
	
	@GetMapping("/petco/board")
	public void board(Model model) {
		
		service.getList(model);
		
		
	}
	
	@GetMapping("/petco/board/{bno}")
	public void getOne() {
		
	}
	
	
	
	
}

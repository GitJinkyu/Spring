package com.momo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("list")
	public void getList(Model model) {
		List<BoardVO> list = boardService.getListXml();
		log.info("=========================");
		log.info(list);
		model.addAttribute("list",list);
	
	}
	
	@GetMapping("view")
	public void getOne(Model model,int bno) {
	
		log.info("=========================");
		
		model.addAttribute("view",boardService.getOne(bno));
	}
	
	@GetMapping("write")
	public void write(Model model) {
		
	}
	
	@PostMapping("write")
	public String writeAction(Model model,BoardVO board) {
		log.info(board);
		board.setTitle(board.getTitle());
		board.setContent(board.getContent());
		board.setWriter(board.getWriter());
		
		boardService.insert(board);
		
		String msg = "등록되었습니다";
		model.addAttribute("msg", msg);
		
		return "redirect:/board/list";
	}
}

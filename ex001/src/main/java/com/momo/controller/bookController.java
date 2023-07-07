package com.momo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BookService;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book/*")
@Log4j
public class bookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("list")
	public void getList(Criteria cri,Model model) {
		//pageNo 타입 = int -> '' 공백 예외발생 유의
		
		//리스트 조회
		bookService.getList(cri,model);

	}
	
	@GetMapping("view")
	public void getOne(BookVO book,Model model) {
		book = bookService.getOne(book.getNo());
		model.addAttribute("book", book);
	}
	
	@GetMapping("delete")
	public String delete(RedirectAttributes rttr ,Model model,@RequestParam("delNo") String delNo, @RequestParam(value = "no", required = false) String no) {
	    if (delNo == null) {
	        delNo = no;
	    }
	    System.out.println("delNo: " + delNo);
	    int res = bookService.delete(delNo);
	    String msg = "";
		
		if(res>0) {
			
			msg = no+"번 삭제되었습니다.";
		
			//rttr.addAttribute는 
			//url?msg=등록되었습니다 (쿼리스트링으로 전환됨. 화면에서 받을때 param.으로 받아야함)
			//rttr.addAttribute("msg",msg);
			
			//세션영역에 잠시 저장 -> param. 안붙이고 msg로 호출 가능
			//잠깐 쓰고 사라지기때문에 새로고침시 유지되지않음
			rttr.addFlashAttribute("msg",msg);
			
			return "redirect:/book/list";
			
		}else {
			msg="등록중 오류가 발생하였습니다.";
			model.addAttribute("msg",msg);
			return "redirect:/book/list";
		}
	    
	    
	}

}

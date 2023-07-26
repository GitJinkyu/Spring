package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.PetcoBoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.PetcoBoardVO;

@Service
public class PetcoBoardServiceImpl implements PetcoBoardService {

	@Autowired
	PetcoBoardMapper mapper;
	
	@Override
	public List<PetcoBoardVO> getList(Model model) {
		List<PetcoBoardVO> list = mapper.getList();
		
		model.addAttribute("list",list);
		return list;
	}

}

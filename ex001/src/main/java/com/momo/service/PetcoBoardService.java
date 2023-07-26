package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.PetcoBoardVO;

@Service
public interface PetcoBoardService {

	public List<PetcoBoardVO> getList(Model model);
	
}

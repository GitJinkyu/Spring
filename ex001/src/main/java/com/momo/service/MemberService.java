package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

@Service
public interface MemberService {

	public Member login(Member member);
	
	public int signUp(Member member);
	
	public int idCheck(Member member);
	
	public List<String> getMemberRole(String id);

}

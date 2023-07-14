package com.momo.mapper;

import org.springframework.ui.Model;

import com.momo.vo.Member;

public interface MemberMapper {
	public Member login(Member member);
	
	public int signUp(Member member);
	
	public int idCheck(Member member);
	
}

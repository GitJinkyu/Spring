package com.momo.mapper;

import java.util.List;

import org.springframework.ui.Model;

import com.momo.vo.Member;

public interface MemberMapper {
	public Member login(Member member);
	
	public int signUp(Member member);
	
	public int idCheck(Member member);
	
	public List<String> getMemberRole(String id);
	
}

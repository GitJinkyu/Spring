package com.momo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BCryptPasswordEncoder encoder; 

	@Override
	public Member login(Member paramMember) {
		//암호화되어 저장된 DB의 암호를 가져오기 위해 id만으로 사용자 조회한다
		Member member = memberMapper.login(paramMember);
		if(member != null) {
			//사용자로부터 화면에서 입력받은 패스워드, DB에 저장된 암호화된 패스워드를 비교
			boolean res = encoder.matches(paramMember.getPw(), member.getPw());
			
			//비밀번호가 일치할 경우 member객체를 반환
			if(res) {
				return member;
			}
		}
		
		
		return null ;
	}

	@Override
	public int signUp(Member member) {
		
		member.setPw(encoder.encode(member.getPw()));
		return memberMapper.signUp(member);
	}

	@Override
	public int idCheck(Member member) {
		return memberMapper.idCheck(member);
	}

}

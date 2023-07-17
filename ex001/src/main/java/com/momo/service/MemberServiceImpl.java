package com.momo.service;

import java.util.List;

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
				//비밀번호까지 일치하는 인증된 유저일 경우 권한조회후 role을 멤버객체에 저장해줌
				member.setRole_id(memberMapper.getMemberRole(member.getId()));
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

	@Override
	public List<String> getMemberRole(String id) {
	
		return memberMapper.getMemberRole(id);
	}

}

package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;


/**
 * 각 계층간의 연결은 인터페이스를 활용하여 느슨한 결합을 합니다.
 * 느슨한 결합 : 하나의 컴포넌트의 변경이 다른 컴포넌트들의 변경을 요구하는 위험을 줄이는 것을
 * 				목적으로 하는 시스템에서 컴포넌트간의 내부 의존성을 줄이는 것을 추구하는 디자인 목표
 * 
 * 🎃 Service
 * 		계층 구조상 비즈니스 영역을 담당하는 객체임을 표시
 * 
 * 🎃root-context.xml
 * 		component-scan 속성에 패키지를 등록합니다.
 *
 *	서비스를 interface로 생성하는 이유
 *
 *	1. 내부로직의 분리
 *		인터페이스를 사용함으로써 내부로직의 변경, 수정시 유연하게 대체할 수 있다
 *	2. 구현체의 전환이 용이
 *		구현체의 변경, 교체가 용이합니다.
 *	3. 테스트 용이성
 *		단위테스트시 테스트를 구현체를 이용함으로써 테스트를 수행
 */
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getListXml() {

		return boardMapper.getListXml();
	}

	@Override
	public int insert(BoardVO boardvo) {
		int res = boardMapper.insert(boardvo);
		return 0;
	}

	@Override
	public int insertSelectKey(BoardVO boardvo) {
		
		return boardMapper.insertSelectKey(boardvo);
	}

	@Override
	public BoardVO getOne(int bno) {
		return boardMapper.getOne(bno);
	}

	@Override
	public int delete(int bno) {
		return boardMapper.delete(bno);
		
	}

	@Override
	public int update(BoardVO boardvo) {
		return boardMapper.update(boardvo);
	
	}

	@Override
	public int getTotalCnt() {
		return  boardMapper.getTotalCnt();
		
	}

}

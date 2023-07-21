package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.FileuploadVO;
import com.momo.vo.PageDto;


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
	
	@Autowired
	private FileuploadService fileuploadservice;
	
	
	
	@Override
	public List<BoardVO> getListXml(Criteria cri, Model model) {
		/*
		 * 1.리스트 조회
		 * 		-검색어,페이지 정보(startNo~endNo까지 조회)
		 * 2.총건수 조회
		 * 3.pageDto 객체 생성
		 */
		List<BoardVO> list = boardMapper.getListXml(cri);
		int totalCnt = boardMapper.getTotalCnt(cri);
		
		PageDto pageDto = new PageDto(cri,totalCnt);
		
		model.addAttribute("list",list);
		model.addAttribute("totalCnt",totalCnt);
		model.addAttribute("pageDto",pageDto);
		
		return list;
		
		
	}

	@Override
	public int insert(BoardVO boardvo) {

		return  boardMapper.insert(boardvo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertSelectKey(BoardVO boardvo,List<MultipartFile> files ) throws Exception {
		
		//게시물 등록
		int res = boardMapper.insertSelectKey(boardvo);
		
		//파일 첨부
		fileuploadservice.fileupload(files,boardvo.getBno());
		
		
		return res;
	}

	@Override
	public BoardVO getOne(int bno) {
		return boardMapper.getOne(bno);
	}

	@Override
	public int delete(int bno) {
		//게시물을 삭제시 첨부된 파일이 있는 경우 오류가 발생
		//첨부파일을 모두 삭제해야함 (또는 DB테이블 제약조건의 옵션을 변경해도된다.)

		//첨부파일 리스트 조회 - fileuploadService
		List<FileuploadVO> list = fileuploadservice.getList(bno);
		int res = 0 ;
		for(FileuploadVO vo : list) {
			//리스트 돌면서 삭제 처리 - fileuploadService
			res += fileuploadservice.delete(bno, vo.getUuid());
			
		}
		
		/* 2.댓글 리스트 삭제 추가해야함 */
		//replyMapper.deleteReplyList(bno);
		
		//3.게시글 삭제
		return boardMapper.delete(bno);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(BoardVO boardvo, List<MultipartFile> files) throws Exception {
		
		int res = boardMapper.update(boardvo);
		
		fileuploadservice.fileupload(files, boardvo.getBno());
		
		return res;
	
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return  boardMapper.getTotalCnt(cri);
		
	}

	@Override
	public int updateReplyCnt(int bno,int amount) {
		
		return boardMapper.updateReplyCnt(bno, amount);
	}



}

package com.momo.vo;

import lombok.Data;

@Data
public class PageDto {

	private Criteria cri;		//페이지번호,페이지당 게시물수
	private int total;			//총 게시물의 수
	
	private int startNo;		//페이지 블록의 시작번호
	private int endNo;			//페이지 블록의 끝번호
	
	private boolean prev, next;	//이전,다음 버튼 활성true/비활성false
	
	private int realEnd;		//페이지 끝 번호
	
	public PageDto(Criteria cri, int total){
		this.cri = cri;
		this.total = total;
		
		//페이지 블럭의 끝번호
		this.endNo = (int)(Math.ceil(cri.getPageNo()/5.0)* 5);

		//페이지 블록의 시작번호
		this.startNo = this.endNo - 4;
		
		//총 게시물의 수를 페이지당 보여지는 게시물의 수로 나눠서 실제 끝 페이지 번호를 구함
		realEnd = (int)Math.ceil((total*1.0)/cri.getAmount());
		
		//게시글이 없을때는 realEnd값이 0이되면서 페이지 끝을 누르면  예외발생
		//realEnd 값을 1로 설정해서 1페이지로 계속 가도록 처리
		if (realEnd <= 0) {
		        realEnd = 1; // 최소한 1로 설정하거나 다른 값으로 설정할 수도 있습니다.
		}
		
		
		endNo = endNo > realEnd ? realEnd : endNo;
		
		prev = startNo > 1 ? true : false;
		next = endNo == realEnd ? false : true;
	}
	
}

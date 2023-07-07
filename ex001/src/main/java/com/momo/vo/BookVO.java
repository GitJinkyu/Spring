package com.momo.vo;

import lombok.Data;

@Data
public class BookVO {
	private String no;			//도서번호
	private String title;		//제목
	private String author;		//작가
	
	private String ofile;		//원본파일명
	private String sfile;		//저장파일명
	
	private String id;			//대여자ID
	private String rentyn;		//도서대여여부
	private String rentStr;
	
	private String rentno;		//대여번호
	private String startDate;	//대여시작일
	private String endDate;		//반납가능일
	
	// 도서를 추가할 경우 도서명과 작가명만 알고 있으면 생성 가능
	public BookVO(String title, String author) {
		this.title = title;
		this.author = author;
		// 신규도서이므로 N
		this.rentyn = "N";
	}
}

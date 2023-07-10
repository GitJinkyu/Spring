CREATE TABLE BOOK(
    NO NUMBER CONSTRAINT BOOK_PK PRIMARY KEY,
    TITLE VARCHAR2(100),
    RENTYN CHAR(1) DEFAULT 'N' CHECK(RENTYN IN('Y','N')), 
    AUTHOR VARCHAR2(20)
);
SELECT * FROM BOOK;
DROP TABLE BOOK;

INSERT INTO BOOK VALUES(1,'책1','N','작가1');
INSERT INTO BOOK VALUES(2,'책2','N','작가2');
INSERT INTO BOOK VALUES(3,'책3','N','작가3');
INSERT INTO BOOK VALUES(4,'책4','N','작가4');
INSERT INTO BOOK VALUES(5,'책5','N','작가5');
INSERT INTO BOOK VALUES(55,'책5','N','작가5');


CREATE TABLE MEMBER(
    ID VARCHAR2(20) CONSTRAINT MEMBER_PK PRIMARY KEY,
    PW VARCHAR2(20),
    NAME VARCHAR2(20),
    ADMINYN CHAR(1) DEFAULT 'N' CHECK(ADMINYN IN('Y','N')), 
    STATUS CHAR(1) DEFAULT 'N' CHECK(STATUS IN('Y','N')), 
    GRADE CHAR(1) DEFAULT 'B'
);

SELECT * FROM MEMBER;
DROP TABLE MEMBER;

INSERT INTO MEMBER VALUES('admin','1234','관리자','Y','Y','B');


CREATE SEQUENCE SEQ_BOOK_NO;

DROP SEQUENCE SEQ_BOOK_NO;

SELECT SEQ_BOOK_NO.NEXTVAL FROM DUAL;    
SELECT SEQ_BOOK_NO.CURRVAL FROM DUAL;

ALTER SEQUENCE SEQ_BOOK_NO INCREMENT BY -24;

SELECT * FROM BOOK ORDER BY NO;


INSERT INTO BOOK VALUES(SEQ_BOOK_NO.NEXTVAL,'d','N','d');

delete from Book where no =55;

private String id;
private String pw;
private String name;
private String adminyn;
private String status;
private String grade;

SELECT id,name,adminyn,status,grade FROM MEMBER WHERE ID='admin' AND PW='1234';

INSERT INTO MEMBER VALUES('guest','1234','사용자','N','Y','B');

INSERT INTO MEMBER (id,pw,name) VALUES('guest2','1234','사용자');

delete from member where id =

select * from member ;

delete from member where id ='na' and pw='1234';

delete from member where id ='id'and pw ='pw';

SELECT RENTYN FROM BOOK WHERE NO='2';

--book테이블의 rentYN -> 대여테이블의 대여여부 컬럼을 이용

CREATE TABLE 대여(
    대여번호 varchar2(10) primary key,
    아이디 varchar2(20)not null, 
    도서번호 number not null, 
    대여여부 char(1) default 'Y' CHECK(대여여부 IN('Y','N')), 
    대여일 date default sysdate,
    반납일 date,
    반납가능일 date,
    연체일 number
);

CREATE SEQUENCE seq_대여;
insert into 대여 values (seq_대여.nextval,'user',1,'Y',SYSDATE,null,to_date(sysdate)+7,null);
insert into 대여 values (seq_대여.nextval,'user',2,'Y','2023-04-28',null,to_date('2023-04-28')+7,null);
insert into 대여 values (seq_대여.nextval,'user',1,'N','2023-04-28',null,to_date('2023-04-28')+7,null);

SELECT NO,TITLE,NVL(대여여부,'N'),AUTHOR FROM BOOK,대여 WHERE NO=도서번호(+) AND 대여여부(+)='Y' ORDER BY 1;

SELECT 대여여부 FROM 대여 WHERE 도서번호=2;
    
    UPDATE 대여 SET 대여여부 = 'N',반납일=SYSDATE WHERE 도서번호 = 2;
    
    SELECT 대여여부 FROM 대여 WHERE 대여여부='Y' AND 도서번호=1;
    
DROP TABLE BOOK;
DROP TABLE MEMBER;
DROP TABLE 대여;
DROP SEQUENCE SEQ_BOOK_NO;
DROP SEQUENCE SEQ_대여;

SELECT count(*) FROM 대여 WHERE 대여여부='Y' AND 도서번호=3;

UPDATE 대여 d, BOOK b
SET d.대여여부 = 'N', d.반납일 = SYSDATE, b.RENTyn = 'N'
WHERE d.도서번호 = b.NO AND d.도서번호 = A;

INSERT INTO BOOK VALUES (seq_book_no.nextval, '20세기 소년', 'N', '우라사와 나오키');
INSERT INTO BOOK VALUES (seq_book_no.nextval, 'Yellow', 'N', 'COLDPLAY');
INSERT INTO BOOK VALUES (seq_book_no.nextval, '슬램덩크', 'N', '이노우에 다케히코');
INSERT INTO BOOK VALUES (seq_book_no.nextval, '베르나르 베르베르의 상상력 사전', 'N', '베르나르 베르베르');
INSERT INTO BOOK VALUES (seq_book_no.nextval, '드래곤볼', 'N', '토리야마 아키라');
INSERT INTO BOOK VALUES (seq_book_no.nextval, '몬스터', 'N', '이노우에 다케히코');
INSERT INTO BOOK VALUES (seq_book_no.nextval, '1Q84', 'N', '무라카미 하루키');

insert into book (no,title,rentyn,author) values (SEQ_BOOK_NO.NEXTVAL, '안녕', 'N', '하세요');

delete
from book
where no in (39);

select *
from dual;

--대여번호 구하기
select 'R'||Lpad(seq_대여.nextval,5,0) from dual;

--대여가능한 도서 조회하는 조건
select * from book where no=3 and (rentno is null or rentno='');

--북 테이블에 대여번호 업데이트하기
update book set rentno='대여번호넣기' where no=3 and (rentno is null or rentno='');

select * from 대여;

--대여 테이블에 대여 추가하기
insert into 대여 values('대여번호', '아이디', 3,'Y',sysdate,null,sysdate+14,null);


select * from(select t.*, rownum rn from( select no, title, author from book
ORDER BY no DESC)t);

select b.no, b.title, b.author, d.대여여부, d.아이디, to_char(대여일,'yy/mm/dd') 대여일,to_char(반납가능일,'yy/mm/dd') 반납가능일, 반납일, sfile, ofile, d.대여번호 from book b, 대여 d where b.rentno = d.대여번호(+) and b.no=1;

update book set rentno= 'r00034' where no= 249 and (rentno is null or rentno='');


--반납 쿼리문
update book set rentno= NULL, rentyn='N' where no=187;
update 대여 set 대여여부='N', 반납일=sysdate,연체일 = CASE WHEN SYSDATE - 반납가능일 < 0 THEN '0' ELSE TO_CHAR(SYSDATE - 반납가능일, 'FM99990') END  where 대여번호='R00041' and (대여번호 is not null or 대여번호 != '');


select to_char(sysdate,'yyyy/mm/')from dual;


delete from member where id= 'guest1';
delete from member where id='guest1';


create sequence seq_board;
 
create table tbl_board (
  bno number(10,0),
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  regdate date default sysdate, 
  updatedate date default sysdate
);
 
alter table tbl_board add constraint pk_board 
primary key (bno);

insert into tbl_board (bno, title, content, writer) 
values (seq_board.nextval, '테스트 제목','테스트 내용','user00');
insert into tbl_board (bno, title, content, writer) 
values (seq_board.nextval, '테스트 제목','테스트 내용','user00');
insert into tbl_board (bno, title, content, writer) 
values (seq_board.nextval, '테스트 제목','테스트 내용','user00');
insert into tbl_board (bno, title, content, writer) 
values (seq_board.nextval, '테스트 제목','테스트 내용','user00');


select * from tbl_board;

		select seq_board.nextval from dual;


select * from tbl_board where bno=5;
select * from tbl_board where bno=10; 

	SELECT COUNT(*) FROM tbl_board;


--댓글 테이블
create table tbl_reply (
  rno number(10,0), 
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null, 
  replyDate date default sysdate, 
  updateDate date default sysdate
);
 
 --댓글 시퀀스
create sequence seq_reply;
 
 --댓글 테이블 기본키 주기 rno
alter table tbl_reply add constraint pk_reply primary key (rno);
 
 
 --댓글 테이블 <-> 게시글 테이블 bno 외래키 설정
alter table tbl_reply  add constraint fk_reply_board  
foreign key (bno)  references  tbl_board (bno); 

insert into tbl_reply (rno, bno, reply, replyer)
    values (seq_reply.nextval,148,'댓글입니다','댓글작성자입니다');
    
    
    select rno,bno,reply,replyer,to_char(replyDate,'yyyy-MM-dd')replyDate ,updateDate
 	from tbl_reply 
 	where bno=148
 	order by rno desc
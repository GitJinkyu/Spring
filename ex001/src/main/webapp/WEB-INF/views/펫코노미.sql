create table pet_board (
  bno number(10,0) primary key, 
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  visitcount number default 0 not null ,
  likecount number default 0 not null,
  regdate date default sysdate, 
  updatedate date default sysdate
);

CREATE SEQUENCE seq_petboard;

insert into pet_board (bno, title, content, nickname) values (seq_petboard.nextval,'우리강아지','귀엽죠?','김진규');
insert into pet_board (bno, title, content, nickname) values (seq_petboard.nextval,'사료 나눔 해주실분?','사료가 다 떨어졌어요','허윤빈');
insert into pet_board (bno, title, content, nickname) values (seq_petboard.nextval,'시바견 키우시는분 있나요..','너무 힘드네요','안태영');
insert into pet_board (bno, title, content, nickname) values (seq_petboard.nextval,'강아지 교육시키는법','어케하나요','김지아');

select bno,title,content,nickname,visitcount,DECODE(TRUNC(sysdate), TRUNC(regdate), TO_CHAR(regdate, 'HH24:MI:SS'), TO_CHAR(regdate, 'YYYY-MM-DD')) AS regdate, updatedate 
                from pet_board
                order by bno desc; 
        
     
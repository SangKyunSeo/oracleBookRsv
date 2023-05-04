-- 회원 테이블
CREATE TABLE member(
	me_num NUMBER PRIMARY KEY, -- 회원 번호
	me_id VARCHAR2(10) UNIQUE NOT NULL, -- 아이디
	me_passwd VARCHAR2(10) NOT NULL, -- 비밀번호
	me_name VARCHAR2(30) NOT NULL, -- 이름
	me_phone VARCHAR2(13) NOT NULL, -- 전화번호
	me_regdate DATE DEFAULT SYSDATE NOT NULL -- 가입일
);
CREATE SEQUENCE member_seq;

-- 도서 테이블
CREATE TABLE book(
	bk_num NUMBER PRIMARY KEY, -- 도서 번호
	bk_name VARCHAR2(90) NOT NULL, -- 책 제목
	bk_category VARCHAR2(30) NOT NULL, -- 카테고리
	bk_regdate DATE DEFAULT SYSDATE NOT NULL -- 등록일
);
CREATE SEQUENCE book_seq;

-- 예약 테이블
CREATE TABLE reservation(
	re_num NUMBER PRIMARY KEY, -- 예약 번호
	re_status NUMBER(1) NOT NULL, -- 0.반납(미대출) 1.대출중
	bk_num NUMBER NOT NULL REFERENCES book (bk_num), -- 도서 번호
	me_num NUMBER NOT NULL REFERENCES member (me_num) -- 회원 번호
	re_regdate DATE DEFAULT SYSDATE NOT NULL, -- 예약(대출) 날짜
	re_modifydate DATE -- 수정(반납) 날짜
);
CREATE SEQUENCE reservation_seq;

create or replace proceduer deleteReservation(n IN book.bk_num%type)
IS
	rsvNum reservation.re_num%type;
BEGIN
	SELECT re_num INTO rsvNum FROM reservation WHERE bk_num = n;
	UPDATE reservation SET re_status=0,re_modifydate=SYSDATE WHERE re_num=rsvNum;
	
END 







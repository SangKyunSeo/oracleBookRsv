package kr.s08.book;

public class SqlQuery {
	// 도서목록 조회
	static final String SELECT_BOOK_SQL = "SELECT * FROM book";
	
	// 아이디 중복체크
	static final String SELECT_CHECK_ID_SQL = "SELECT me_id FROM member WHERE me_id=?";
	
	// 로그인 체크
	static final String SELECT_LOGIN_CHECK_SQL = "SELECT me_num FROM member WHERE me_id=? AND me_passwd=?";
	
	// 회원 목록 조회
	static final String SELECT_MEMBER_SQL = "SELECT * FROM member";
	
	// 대출 가능여부 확인을 위한 예약 테이블 정보 조회
	static final String SELECT_RESERVE_CHECK_JOIN_SQL = "SELECT * FROM book b,(SELECT * FROM reservation WHERE re_status=1) r WHERE r.bk_num(+) = b.bk_num AND b.bk_num=?";

	// 예약 테이블 정보 조회
	static final String SELECT_RESERVATION_SQL = "SELECT * FROM reservation r,member m,book b WHERE r.me_num=m.me_num AND r.bk_num=b.bk_num ORDER BY r.re_num DESC";
	
	// MY 대출 목록 조회
	static final String SELECT_MY_RESERVATION_SQL = "SELECT * FROM reservation r, book b WHERE r.bk_num = b.bk_num AND me_num=? AND r.re_status=1 ORDER BY r.re_num DESC";
	
	// 반납 가능여부 확인을 위한 나의 대출 정보 조회
	static final String SELECT_CHECK_RETURN_BOOK_SQL = "SELECT * FROM reservation WHERE me_num=? AND re_num=?";

	// 도서 등록
	static final String REGISTER_BOOK_SQL = "INSERT INTO book VALUES(book_seq.nextval,?,?,SYSDATE)";
	
	// 회원 등록
	static final String REGISTER_MEMBER_SQL = "INSERT INTO member VALUES(member_seq.nextval,?,?,?,?,SYSDATE)";
	
	// 대출 등록
	static final String REGISTER_RESERVATION_SQL = "INSERT INTO reservation(re_num,re_status,bk_num,me_num,re_regdate) VALUES(reservation_seq.nextval,1,?,?,SYSDATE)";

	// 반납에 의한 예약 테이블 수정
	static final String UPDATE_RESERVAION_SQL = "UPDATE reservation SET re_status=0,re_modifydate=SYSDATE WHERE me_num=? AND re_num=?";
	
}

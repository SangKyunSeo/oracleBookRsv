package kr.s08.book;

public class BookDAO {
	
	private BookUserModel bum = new BookUserModel();
	private BookAdminModel bam = new BookAdminModel();
	private BookLogicModel blm = new BookLogicModel();
	// 도서등록 - 관리자
	public void registerBook(String name, String category) {
		bam.registerBook(name, category);
	}

	// 도서 목록 보기 - 관리자,사용자
	public void selectBook() {
		bam.selectBook();
	}

	// 아이디 중복 체크
	public int checkId(String me_id) {
		return blm.checkId(me_id);
	}

	// 회원가입
	public void registerMember(String me_id, String me_passwd, String me_name, String me_phone) {
		bum.registerMember(me_id, me_passwd, me_name, me_phone);
	}

	// 로그인 체크
	public int loginCheck(String me_id, String me_passwd) {
		return blm.loginCheck(me_id, me_passwd);
	}

	// 회원 목록 보기 - 관리자
	public void selectMember() {
		bam.selectMember();
	}

	// 도서 대출 여부 확인
	public int reserveCheck(int bk_num) {
		return blm.reserveCheck(bk_num);
	}

	// 도서 대출 등록
	public void registerReservationBook(int bk_num, int me_num) {
		bum.registerReservationBook(bk_num, me_num);
	}

	// 대출 목록 보기 - 관리자
	// 대출 및 반납 모든 데이터 표시
	public void selectReservation() {
		bam.selectReservation();
	}

	// MY 대출 목록 보기(현재 대출한 목록만 표시)
	public void myReservaionDetail(int me_num) {
		bum.myReservaionDetail(me_num);
	}

	// 반납 가능 여부
	// 대출번호(re_num)와 회원번호(me_num)를 함께 조회해서 re_status가 1인 것은 반납 가능
	public int checkReturnBook(int me_num, int re_num) {
		return blm.checkReturnBook(me_num, re_num);
	}

	// 반납 처리
	public void updateReservation(int me_num, int re_num) {
		bum.updateReservation(me_num, re_num);
	}
}
package kr.s08.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BookUserMain {
	private BufferedReader br;
	private BookDAO dao;
	private int me_num; // 회원 번호
	private boolean flag; // 로그인 여부(로그인 true,미로그인 false)

	public BookUserMain() {
		try {
			dao = new BookDAO();
			br = new BufferedReader(new InputStreamReader(System.in));

			callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) try {br.close();} catch (IOException e) {}
		}
	}

	public void callMenu() throws IOException {
		loginMenu();
		activeMenu();
	}

	public void loginMenu() throws IOException {
		while (true) {
			System.out.print("1.로그인, 2.회원가입, 3.종료 > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if (no == 1) { // 로그인
					login();
					if(flag)return;
				} else if (no == 2) { // 회원가입
					register();
				} else if (no == 3) { // 종료
					System.out.println("프로그램 종료");
					return;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능!!");
			}
		}
	}

	public void activeMenu() throws IOException {
		while (flag) {
			System.out.print("1.도서대출,2.MY대출목록,3.대출도서 반납,4.종료");
			try {
				int no = Integer.parseInt(br.readLine());
				if (no == 1) { // 도서대출
					// 도서 목록
					rentBook();
				} else if (no == 2) { // MY대출목록
					dao.myReservaionDetail(me_num);
				} else if (no == 3) { // 대출도서 반납
					dao.myReservaionDetail(me_num);
					System.out.print("반납할 도서의 예약번호를 입력 : ");
					int n = Integer.parseInt(br.readLine());
					if (dao.checkReturnBook(me_num, n) == 1) {
						dao.updateReservation(me_num, n);
					} else {
						System.out.println("반납할 수 없는 상태입니다.");
					}
				} else if (no == 4) { // 종료
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능!!");
			}
		}
	}
	
	public void login() throws IOException{
		String me_id = inputId();
		String me_passwd = inputPasswd();
		loginCheck(me_id,me_passwd);
	}
	
	public String inputId() throws IOException{
		System.out.print("아이디 : ");
		return br.readLine();
	}
	
	public String inputPasswd() throws IOException{
		System.out.print("비밀번호 : ");
		return br.readLine();
	}
	
	public String inputName() throws IOException{
		System.out.print("이름 : ");
		return br.readLine();
	}
	
	public String inputPhone() throws IOException{
		System.out.print("전화번호 : ");
		return br.readLine();
	}
	
	public int inputBookNumber() throws IOException, NumberFormatException{
		System.out.print("책 번호 입력 : ");
		return Integer.parseInt(br.readLine());
	}
	
	public int inputReservationNumber() throws IOException, NumberFormatException{
		System.out.print("예약번호 입력 : ");
		return Integer.parseInt(br.readLine());
	}
	
	public void loginCheck(String me_id,String me_passwd) {
		me_num = dao.loginCheck(me_id, me_passwd);
		if (me_num > 0) {
			System.out.println(me_id + "님 로그인 되었습니다.");
			flag = true;
			return;
		}
		System.out.println("아이디 또는 비밀번호 불일치");
	}
	
	public void register() throws IOException{
		String me_id = registerId();
		String me_passwd = inputPasswd();
		String me_name = inputName();
		String me_phone = inputPhone();
		dao.registerMember(me_id, me_passwd, me_name, me_phone);
	}
	
	public String registerId() throws IOException{
		String id = null;
		while(true) {
			id = inputId();
			if(duplicateCheck(id)) {
				break;
			}else {
				System.out.println("아이디가 중복되었습니다.");
			}
		}
		return id;
	}
	
	public boolean duplicateCheck(String id) {
		if (dao.checkId(id) == 1) {
			return false;
		}
		return true;
	}
	
	public void rentBook() throws IOException,NumberFormatException{
		dao.selectBook();
		rentCheckBook(inputBookNumber());
	}

	public void rentCheckBook(int bk_num) {
		if (dao.reserveCheck(bk_num) == 1) {
			System.out.println(bk_num + "번 책은 현재 대출중으로 대출이 불가능합니다.");
		} else if (dao.reserveCheck(bk_num) == 2) {
			System.out.println("존재하지 않는 책입니다.");
		} else {
			dao.registerReservationBook(bk_num, me_num);
		}

	}
	public static void main(String[] args) {
		new BookUserMain();
	}
}
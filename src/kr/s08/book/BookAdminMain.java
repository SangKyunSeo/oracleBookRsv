package kr.s08.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BookAdminMain {
	private BufferedReader br;
	private BookDAO dao;
	
	public BookAdminMain() {
		try {
			dao = new BookDAO();
			br = new BufferedReader(new InputStreamReader(System.in));

			callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)try {br.close();} catch (IOException e) {}
		}
	}

	public void callMenu() throws IOException {
		while (true) {
			System.out.print("1.도서등록, 2.도서목록, 3.대출목록, 4.회원목록, 5.종료 > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if (no == 1) { // 도서 등록
					registerMenu();
				} else if (no == 2) { // 도서 목록
					dao.selectBook();
				} else if (no == 3) { // 대출 목록
					dao.selectReservation();
				} else if (no == 4) { // 회원 목록
					dao.selectMember();
				} else if (no == 5) { // 종료
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

	public void registerMenu() throws IOException {
		System.out.print("책 제목 : ");
		String name = br.readLine();

		System.out.print("카테고리 : ");
		String category = br.readLine();

		dao.registerBook(name, category);
	}

	public static void main(String[] args) {
		new BookAdminMain();
	}
}
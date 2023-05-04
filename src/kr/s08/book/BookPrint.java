package kr.s08.book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPrint {

	public void selectBookPrint(ResultSet rs) throws SQLException {
		System.out.println("----------------------------------");
		System.out.println("번호\t제목\t카테고리\t등록일");
		
		while (rs.next()) {
			System.out.print(rs.getInt("bk_num") + "\t");
			System.out.print(rs.getString("bk_name") + "\t");
			System.out.print(rs.getString("bk_category") + "\t");
			System.out.println(rs.getDate("bk_regdate"));
		}
		System.out.println("----------------------------------");
	}
	
	public void selectMemberPrint(ResultSet rs) throws SQLException{
		System.out.println("----------------------------------------");
		System.out.println("번호\t아이디\t비밀번호\t이름\t전화번호\t\t가입일");

		while (rs.next()) {
			System.out.print(rs.getInt("me_num") + "\t");
			System.out.print(rs.getString("me_id") + "\t");
			System.out.print(rs.getString("me_passwd") + "\t");
			System.out.print(rs.getString("me_name") + "\t");
			System.out.print(rs.getString("me_phone") + "\t");
			System.out.println(rs.getDate("me_regdate"));
		}
		System.out.println("----------------------------------------");
	}
	
	public void selectReservationPrint(ResultSet rs) throws SQLException{
		System.out.println("---------------------------------------------");
		System.out.println("번호\t상태\t도서번호\t책제목\t회원번호\t회원이름\t예약(대출)날짜\t수정(반납)날짜");

		while (rs.next()) {
			System.out.print(rs.getInt("re_num") + "\t");
			if (rs.getInt("re_status") == 0) {
				System.out.print("반납" + "\t");
			} else
				System.out.print("대출중" + "\t");
			System.out.print(rs.getInt("bk_num") + "\t");
			System.out.print(rs.getString("bk_name") + "\t");
			System.out.print(rs.getInt("me_num") + "\t");
			System.out.print(rs.getString("me_name") + "\t");
			System.out.print(rs.getDate("re_regdate") + "\t");

			if (rs.getDate("re_modifydate") == null) {
				System.out.println("");
			} else
				System.out.println(rs.getDate("re_modifydate"));
		}
		System.out.println("---------------------------------------------");
	}
	
	public void myReservationPrint(ResultSet rs) throws SQLException {
		System.out.println("---------------------------------------------");
		System.out.println("예약번호\t도서번호\t책제목\t예약(대출)날짜");

		while (rs.next()) {
			System.out.print(rs.getInt("re_num") + "\t");
			System.out.print(rs.getInt("bk_num") + "\t");
			System.out.print(rs.getString("bk_name") + "\t");
			System.out.println(rs.getDate("re_regdate"));
		}
		System.out.println("---------------------------------------------");
	}
}

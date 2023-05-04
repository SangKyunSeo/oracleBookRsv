package kr.s08.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class BookUserModel {
	private BookPrint bp = new BookPrint();
	// 회원가입
	public void registerMember(String me_id, String me_passwd, String me_name, String me_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.REGISTER_MEMBER_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			pstmt.setString(3, me_name);
			pstmt.setString(4, me_phone);

			int count = pstmt.executeUpdate();
			if (count > 0)
				System.out.println("회원가입을 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 도서 대출 등록
	public void registerReservationBook(int bk_num, int me_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.REGISTER_RESERVATION_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bk_num);
			pstmt.setInt(2, me_num);

			int count = pstmt.executeUpdate();
			if (count > 0)
				System.out.println("도서 대출을 등록했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// my대출목록
	public void myReservaionDetail(int me_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.SELECT_MY_RESERVATION_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, me_num);
			rs = pstmt.executeQuery();
			
			bp.myReservationPrint(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	// 반납 처리
	public void updateReservation(int me_num, int re_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.UPDATE_RESERVAION_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, me_num);
			pstmt.setInt(2, re_num);
			int count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("반납을 성공했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}

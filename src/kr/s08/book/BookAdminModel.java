package kr.s08.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class BookAdminModel {

	private BookPrint bp = new BookPrint();
	// 도서등록
	public void registerBook(String name, String category) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {

			conn = DBUtil.getConnection();
			sql = SqlQuery.REGISTER_BOOK_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, category);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서 정보를 등록했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}

	// 도서목록보기
	public void selectBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.SELECT_BOOK_SQL;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			bp.selectBookPrint(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	// 회원목록보기
	public void selectMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.SELECT_MEMBER_SQL;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			bp.selectMemberPrint(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	// 대출목록보기
	public void selectReservation() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.SELECT_RESERVATION_SQL;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			bp.selectReservationPrint(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}

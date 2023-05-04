package kr.s08.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class BookLogicModel {

	// 아이디 중복 체크
	public int checkId(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = SqlQuery.SELECT_CHECK_ID_SQL;
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, me_id);
			// JDBC 수행 4단계 : SQL문 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return count; // 아이디 중복 1, 아이디 미중복 0
	}

	//로그인체크
	public int loginCheck(String me_id, String me_passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int me_num = 0; // 회원 번호

		try {
			// JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = SqlQuery.SELECT_LOGIN_CHECK_SQL;
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			// JDBC 수행 4단계 : SQL문 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				me_num = rs.getInt("me_num");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return me_num;
	}

	// 도서 대출 여부 확인
	public int reserveCheck(int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int check = 0; // 대출 가능 0, 대출 불가능 1, 잘못된 입력 2
		try {
			conn = DBUtil.getConnection();

			sql = SqlQuery.SELECT_RESERVE_CHECK_JOIN_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bk_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("re_status") == 1)
					check = 1;
				else {
					check = 0;
				}
			} 
			else {
				return check = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}

	// 반납 가능 여부
	// 대출번호(re_num)와 회원번호(me_num)를 함께 조회해서 re_status가 1인 것은 반납 가능
	public int checkReturnBook(int me_num, int re_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int re_status = 0;
		try {
			conn = DBUtil.getConnection();
			sql = SqlQuery.SELECT_CHECK_RETURN_BOOK_SQL;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, me_num);
			pstmt.setInt(2, re_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("re_status"));
				if (rs.getInt("re_status") == 1)
					re_status = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return re_status;
	}

}

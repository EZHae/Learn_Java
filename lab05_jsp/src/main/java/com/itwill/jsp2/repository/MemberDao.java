package com.itwill.jsp2.repository;

import static com.itwill.jsp2.datasourceutil.DataSourceUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasourceutil.DataSourceUtil;
import com.itwill.jsp2.domain.Member;
import com.zaxxer.hikari.HikariDataSource;

public enum MemberDao {
	INSTANCE; // 싱글톤
	
	private static final Logger log = LoggerFactory.getLogger(MemberDao.class);
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();
	
	private Member getMemberFromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		String username = rs.getString("USERNAME");
		String password = rs.getString("PASSWORD");
		String email = rs.getString("EMAIL");
		Integer points = rs.getInt("POINTS");
		Timestamp createdTime = rs.getTimestamp("CREATED_TIME");
		Timestamp modifiedTime = rs.getTimestamp("MODIFIED_TIME");
		return Member.builder()
				.id(id)
				.userName(username)
				.password(password)
				.email(email)
				.points(points)
				.createdTime(createdTime)
				.modifiedTime(modifiedTime)
				.build();
	}
	
	// 회원 가입에 필요한 SQL, 메서드
	private static final String SQL_INSERT = 
			"insert into members (username, password, email, created_time, modified_time)"
			+ " values(?, ?, ?, systimestamp, systimestamp)";
	public int insert(Member member) {
		log.info("insert({})", member);
		log.debug(SQL_INSERT);
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	// 로그인할 때 필요한 SQL, 메서드
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD =
			"select * from members"
			+ " where username = ? and password = ?";
	public Member select(String username, String password) {
		log.debug("select(username={}, password={}", username, password);
		log.debug(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
		
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = getMemberFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return member;
	}
	
	// members 테이블의 points 컬럼을 업데이트하는 SQL, 메서드
	private static final String SQL_UPDATE_POINTS = 
			"update members set points = points + ?, modified_time = systimestamp"
			+ " where username = ?";
	public int updatePoint(Integer points, String username) {
		log.debug("updatePoint(points={}, username={}", points, username);
		log.debug(SQL_UPDATE_POINTS);
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_UPDATE_POINTS);
			
			pstmt.setInt(1, points);
			pstmt.setString(2, username);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} close(conn, pstmt);
		
		return result;
	}
}

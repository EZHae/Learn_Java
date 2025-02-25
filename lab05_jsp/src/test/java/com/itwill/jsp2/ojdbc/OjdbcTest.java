package com.itwill.jsp2.ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleDriver;

public class OjdbcTest {
	private static final Logger log = LoggerFactory.getLogger(OjdbcTest.class);
	
	@Test
	public void testSelect() throws SQLException {
		// TODO: Oracle DB에 jspstudy 계정으로 접속, POSTS 테이블 내용을 검색/출력.
		// 1. Oracle JDBC 라이브러리를 등록.
		DriverManager.registerDriver(new OracleDriver());
		log.debug("오라클 드라이브 등록 성공");
		
		// 2. Oracle 접속 - Connection 객체 생성
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jspstudy", "jspstudy");
		Assertions.assertNotNull(conn);
		// -> conn 객체가 null이 아니면 단위 테스트를 성공(success) 처리. null이면 실패(failure) 처리.
		// -> Assertions.assertNull(conn); 이면, 결과가 그 반대가 된다.
		log.debug("conn = " + conn);
		
		// 3. prepareStatement 객체 생성
		PreparedStatement pstmt = conn.prepareStatement("select * from posts");
		Assertions.assertNotNull(pstmt);
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery();
		Assertions.assertNotNull(rs);
		
		// 5. 결과 처리
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String author = rs.getString("AUTHOR");
			LocalDateTime createdTime = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
//			Timestamp createdTime = rs.getTimestamp("CREATED_TIME");
			LocalDateTime modifiedTime = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
//			Timestamp modifiedTime = rs.getTimestamp("MODIFIED_TIME");
			
			log.debug("{} | {} | {} | {} | {} | {}", 
					id, title, content, author, createdTime, modifiedTime);
		}
		
		// 6. 사용했었던 리소스(들)을 해제.
		rs.close();
		pstmt.close();
		conn.close();
		log.debug("오라클 접속 해제.");
	}
}
package com.itwill.jsp2.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbcpTest {
	private static final Logger log = LoggerFactory.getLogger(DbcpTest.class);

	@Test
	public void testHikariCP() throws SQLException {
		// HikariCP의 환경 설정을 할 수 있는 객체.
		HikariConfig config = new HikariConfig();

		// DB에 접속(커넥션, connection)할 수 있는 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver"); // DB 드라이버 이름
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe"); // DB 주소
		config.setUsername("jspstudy"); // DB 사용자 이름
		config.setPassword("jspstudy"); // DB 사용자 비밀번호
//		config.setIdleTimeout(0); // 대기중인 connection이 유지하는 시간 (ms)
//		config.setMinimumIdle(0); // 대기중인 connection을 최소 몇 개까지 유지할 것인가 
									// 최적의 성능과 응답성을 요구한다면 이 값은 설정하지 않는게 좋음.
									// default값을 보면 이해할 수있음. (default: same as maximumPoolSize)
//		config.setMaximumPoolSize(0); // pool에 유지시킬 수 있는 최대 커넥션 수
		
		// DataSource(데이터 소스 - 커넥션 풀) 객체 생성
		// -> DB 서버와 물리적인 연결(커넥션)이 맺어짐.
		HikariDataSource ds = new HikariDataSource(config);
		log.debug("ds = {}", ds);
		
		// 데이터 소스(커넥션 풀)에서 이미 생성된 커넥션 객체를 빌려옴.
		Connection conn = ds.getConnection();
		Assertions.assertNotNull(conn);
		log.debug("Conn = {}", conn);
		
		// PreparedStatement 생성, SQL을 실행(exeQuery, executeUpdate) 결과 처리
		PreparedStatement pstmt = conn.prepareStatement("select * from posts");
		
		ResultSet rs = pstmt.executeQuery();
		
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
		
		// 사용했던 커넥션 객체를 데이터 소스(커넥션 풀)에 반환.
		conn.close();
		log.debug("커넥션 반환 성공");
	}
}

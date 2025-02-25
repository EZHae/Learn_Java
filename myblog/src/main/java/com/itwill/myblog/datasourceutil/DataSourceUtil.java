package com.itwill.myblog.datasourceutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum DataSourceUtil {
	// 싱글톤 객체
	INSTANCE; 
	
	// 필드
	private HikariConfig config;
	private HikariDataSource ds;
	
	// 생성자 - enum 생성자는 항상 private. private은 생략 가능.
	DataSourceUtil() {
		// HikariCP의 환경 설정(configuration)을 담당하는 HikariConfig 객체 생성.
		config = new HikariConfig();
		
		// 커넥션 풀(데이터 소스)의 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("jspstudy");
		config.setPassword("jspstudy");
		
		ds = new HikariDataSource(config);
		
	}
	
	// private 필드로 선언된 ds를 외부에서도 접근할 수 있도록 public 메서드 선언
	public HikariDataSource getDataSource() {
		return ds;
	}
	
	// 편의(유틸리티) 메서드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
}

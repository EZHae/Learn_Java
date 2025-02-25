package com.itwill.myblog.repository;

import static com.itwill.myblog.datasourceutil.DataSourceUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.datasourceutil.DataSourceUtil;
import com.itwill.myblog.domain.File;
import com.zaxxer.hikari.HikariDataSource;

public enum FileDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(FileDao.class);
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();

	private File getFileFromResultSet (ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		Integer postId = rs.getInt("POST_ID");
		String fileName = rs.getString("FILE_NAME");
		String filePath = rs.getString("FILE_PATH");
		return File.builder()
				.id(id)
				.postId(postId)
				.fileName(fileName)
				.filePath(filePath)
				.build();
	}
	
	private static final String SQL_INSERT =
			"insert into files (post_id, file_name, file_path)"
			+ " values (?, ?, ?)";
	public int insertFile(File file) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			pstmt.setInt(1, file.getPostId());
			pstmt.setString(2, file.getFileName());
			pstmt.setString(3, file.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	private static final String SQL_SELECT_ALL =
			"select * from files";
	public List<File> selectFileAll() {
		List<File> files = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				File file = getFileFromResultSet(rs);
				files.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return files;
	}
	
	private static final String SQL_SELECT_BY_POST_ID =
			"select * from files"
			+ " where post_id = ?";
	public File selectFileByPostId(Integer postId) {
		File file = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_BY_POST_ID);
			
			pstmt.setInt(1, postId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				file = getFileFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return file;
	}
}

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
import com.itwill.myblog.domain.Image;
import com.zaxxer.hikari.HikariDataSource;

public enum ImageDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(ImageDao.class);
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();

	private Image getImageFromResultSet (ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		Integer postId = rs.getInt("POST_ID");
		String imageName = rs.getString("IMAGE_NAME");
		String imagePath = rs.getString("IMAGE_PATH");
		return Image.builder()
				.id(id)
				.postId(postId)
				.imageName(imageName)
				.imagePath(imagePath)
				.build();
	}
	
	private static final String SQL_INSERT =
			"insert into images (post_id, image_name, image_path)"
			+ " values (?, ?, ?)";
	public int insertImage(Image image) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			pstmt.setInt(1, image.getPostId());
			pstmt.setString(2, image.getImageName());
			pstmt.setString(3, image.getImagePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	private static final String SQL_SELECT_ALL =
			"select * from images order by id";
	public List<Image> selectImageAll() {
		List<Image> images = new ArrayList<Image>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Image image = getImageFromResultSet(rs);
				images.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return images;
	}
	
	private static final String SQL_SELECT_BY_POST_ID =
			"select * from images"
			+ " where post_id = ? order by id";
	public List<Image> selectImageByPostId(Integer postId) {
		List<Image> images = new ArrayList<Image>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_BY_POST_ID);
			
			pstmt.setInt(1, postId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Image image = getImageFromResultSet(rs);
				images.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return images;
	}
}

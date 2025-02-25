package com.itwill.jsp2.repository;

import static com.itwill.jsp2.datasourceutil.DataSourceUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasourceutil.DataSourceUtil;
import com.itwill.jsp2.domain.Post;
import com.zaxxer.hikari.HikariDataSource;

// Web MVC 아키텍처에서 영속성/저장소 계층(persistence/repository Layer)을 담당하는 객체.
// DB CRUD(create/read/update/delete) 기능을 갖고 있는 객체.
// DAO(Data Access Object)
public enum PostDao {
	// 싱글톤 객체
	INSTANCE;
	
	// 필드
	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();
	
	// ResultSet 선언 시 DB의 데이터들을 Post 객체로 변환하는 메서드
	private Post getPostFromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		String title = rs.getString("TITLE");
		String content = rs.getString("CONTENT");
		String author = rs.getString("AUTHOR");
		Timestamp createdTime = rs.getTimestamp("CREATED_TIME");
		Timestamp modifiedTime = rs.getTimestamp("MODIFIED_TIME");
		return Post.builder()
				.id(id)
				.title(title)
				.content(content)
				.author(author)
				.createdTime(createdTime)
				.modifiedTime(modifiedTime)
				.build();
	}
	
	// 포스트 목록 검색에서 사용할 SQL
	private static final String SQL_SELECT_ALL = 
			"select * from posts order by id desc";
	public List<Post> select() {
		log.debug(SQL_SELECT_ALL);
		List<Post> list = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Post post = getPostFromResultSet(rs);
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	private static final String SQL_SELECT_BY_ID = 
			"select * from posts where id = ?";
	public Post select(int id) {
		log.debug("select(id={})", id);
		log.debug(SQL_SELECT_BY_ID);
		Post post = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				post = getPostFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return post;
	}
	
	private static final String SQL_SELECT_BY_TITLE =
			"select * from posts"
			+ " where upper(title) like upper(?)"
			+ " order by id desc";
	private static final String SQL_SELECT_BY_CONTENT = 
			"select * from posts"
			+ " where upper(content) like upper(?)"
			+ " order by id desc";
	private static final String SQL_SELECT_BY_TITLE_OR_CONTENT = 
			"select * from posts"
			+ " where upper(title) like upper(?) or upper(content) like upper(?)"
			+ " order by id desc";
	private static final String SQL_SELECT_BY_AUTHOR = 
			"select * from posts"
			+ " where upper(author) like upper(?)"
			+ " order by id desc";
	public List<Post> select(String category, String keyword) {
		List<Post> result = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String cate = category;
		String searchKeyword = "%" + keyword + "%";
		log.debug("cate={}, word={}", cate, searchKeyword);
		
		try {
			conn = ds.getConnection();
			
			switch(cate) {
			case "t" :
				log.debug(SQL_SELECT_BY_TITLE);
				pstmt = conn.prepareStatement(SQL_SELECT_BY_TITLE);
				pstmt.setString(1, searchKeyword);
				break;
			case "c" :
				log.debug(SQL_SELECT_BY_CONTENT);
				pstmt = conn.prepareStatement(SQL_SELECT_BY_CONTENT);
				pstmt.setString(1, searchKeyword);
				break;
			case "tc" :
				log.debug(SQL_SELECT_BY_TITLE_OR_CONTENT);
				pstmt = conn.prepareStatement(SQL_SELECT_BY_TITLE_OR_CONTENT);
				pstmt.setString(1, searchKeyword);
				pstmt.setString(2, searchKeyword);
				break;
			case "a" :
				log.debug(SQL_SELECT_BY_AUTHOR);
				pstmt = conn.prepareStatement(SQL_SELECT_BY_AUTHOR);
				pstmt.setString(1, searchKeyword);
				break;
			default :
				break;
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Post post = getPostFromResultSet(rs);
				result.add(post);
			}
			log.debug("result.size = {}", result.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 포스트 저장(새 글 작성)에서 사용할 SQL
	private static final String SQL_INSERT = 
			"insert into posts (title, content, author, created_time, modified_time)"
			+ " values(?, ?, ?, systimestamp, systimestamp)";
	public int insert(Post post) {
		log.info("insert({})", post);
		log.debug(SQL_INSERT);
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getAuthor());
						
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	//
	private static final String SQL_UPDATE = 
			"update posts"
			+ " set title = ?, content = ?, modified_time = systimestamp"
			+ " where id = ?";
	public int update(Post post) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setInt(3, post.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	private static final String SQL_DELETE_BY_ID = 
			"delete from posts where id = ?";
	public int delete(int id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_DELETE_BY_ID);
			
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
}

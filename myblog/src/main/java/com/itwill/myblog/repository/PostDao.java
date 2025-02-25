package com.itwill.myblog.repository;

import static com.itwill.myblog.datasourceutil.DataSourceUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.datasourceutil.DataSourceUtil;
import com.itwill.myblog.domain.Post;
import com.zaxxer.hikari.HikariDataSource;

public enum PostDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PostDao.class); 
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();
	
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
	
	private static final String SQL_SELECT_ALL = 
			"select * from posts order by id desc";
	public List<Post> select() {
		log.debug(SQL_SELECT_ALL);
		
		List<Post> posts = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Post post = getPostFromResultSet(rs);
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return posts;
	}
	
	private static final String SQL_SELECT_BY_ID =
			"select * from posts"
			+ " where id = ?";
	public Post select(Integer id) {
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
			+ " order by id desc"
			+ " offset 10 * ? rows fetch first 10 rows only";
	private static final String SQL_SELECT_BY_CONTENT = 
			"select * from posts"
			+ " where upper(content) like upper(?)"
			+ " order by id desc"
			+ " offset 10 * ? rows fetch first 10 rows only";
	private static final String SQL_SELECT_BY_TITLE_OR_CONTENT = 
			"select * from posts"
			+ " where upper(title) like upper(?) or upper(content) like upper(?)"
			+ " order by id desc"
			+ " offset 10 * ? rows fetch first 10 rows only";
	private static final String SQL_SELECT_BY_AUTHOR = 
			"select * from posts"
			+ " where upper(author) like upper(?)"
			+ " order by id desc"
			+ " offset 10 * ? rows fetch first 10 rows only";
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
	
	private static final String SQL_SELECT_RECENT = 
			"select * from posts order by id desc fetch first 5 rows only";
	public List<Post> selectRecent() {
		log.debug(SQL_SELECT_RECENT);
		
		List<Post> posts = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_RECENT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Post post = getPostFromResultSet(rs);
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return posts;
	}
	
	private static final String SQL_INSERT =
			"insert into posts (title, content, author, created_time, modified_time)"
			+ " values (?, ?, ?, systimestamp, systimestamp)";
	public int insert(Post post) {
		log.debug(SQL_INSERT);
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT, new String[] {"id"});
			
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getAuthor());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
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
	
	private static final String SQL_SELECT_PAGECOUNT =
			"SELECT ceil(count(*)/10) as totalPage"
			+ " from posts";
	public int selectTotalPage() {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_PAGECOUNT);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt("totalPage");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	private static final String SQL_SELECT_POST_BY_PAGE =
			"select * from posts"
			+ " order by id desc"
			+ " offset 10 * ? rows fetch first 10 rows only";
	public List<Post> selectPostsByPage(int page) {
		List<Post> postsByPage = new ArrayList<Post>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_POST_BY_PAGE);
			
			pstmt.setInt(1, page);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Post post = getPostFromResultSet(rs);
				postsByPage.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return postsByPage;
	}
}

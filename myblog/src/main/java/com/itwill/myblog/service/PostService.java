package com.itwill.myblog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.domain.File;
import com.itwill.myblog.domain.Image;
import com.itwill.myblog.domain.Post;
import com.itwill.myblog.repository.FileDao;
import com.itwill.myblog.repository.ImageDao;
import com.itwill.myblog.repository.PostDao;

public enum PostService {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	private final PostDao postDao = PostDao.INSTANCE;
	private final FileDao fileDao = FileDao.INSTANCE;
	private final ImageDao imageDao = ImageDao.INSTANCE;
	
	public List<Post> readAll() {
		log.debug("read()");
		
		List<Post> posts = postDao.select();
		log.debug("# of posts={}", posts.size());
		
		return posts;
	}
	
	public List<Post> readRecent() {
		log.debug("readRecent()");
		
		List<Post> posts = postDao.selectRecent();
		log.debug("# of posts={}", posts.size());
		
		return posts;
	}
	
	public int create(Post post) {
		log.debug("create()");
		
		int result = postDao.insert(post);
		log.debug("create result={}", result);
		
		return result;
	}
	
	public Post read(Integer id) {
		log.debug("read()");
		return postDao.select(id);
	}
	
	public int saveImage(Image image) {
		int result = imageDao.insertImage(image);
		return result;
	}
	
	public int saveFile(File file) {
		int result = fileDao.insertFile(file);
		return result;
	}
	
	public List<Image> readImage(Integer postId) {
		log.debug("readImage(postId={})", postId);
		
		List<Image> images = imageDao.selectImageByPostId(postId);
		log.debug("# of images={}", images.size());

		return images;
	}
	
	public File readFile(Integer postId) {
		log.debug("readFile(postId={}", postId);
		
		File file = fileDao.selectFileByPostId(postId);
		log.debug("file={}", file);
		
		return file;
	}
	
	public int update(Post post) {		
		int result = postDao.update(post);
		log.debug("{}개 행이 업데이트됨", result);
		
		return result;
	}
	
	public int delete(int id) {
		log.debug("delete({})", id);
		
		int result = postDao.delete(id);
		log.debug("{}개 행이 삭제됨.", result);
		
		return result;
	}
	
	public int totalPage() {
		int totalPage = postDao.selectTotalPage();
		log.debug("totalPage={}", totalPage);
		
		return totalPage;
	}
	
	public List<Post> postsByPage(int page) {
		List<Post> postsByPage = postDao.selectPostsByPage(page);
		
		return postsByPage;
	}
}

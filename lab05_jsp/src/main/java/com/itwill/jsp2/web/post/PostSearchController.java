package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostSearchController
 */
@WebServlet(name = "postSearchController", urlPatterns = { "/post/search" })
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostSearchController.class);
	private final PostService postService = PostService.INSTANCE; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSearchController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		log.debug("category = {}, keyword = {}", category, keyword);
		
		List<Post> posts = postService.search(category, keyword);
		log.debug("posts.size = {}", posts.size());
		
		request.setAttribute("posts", posts);
		
		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);;
	}

}

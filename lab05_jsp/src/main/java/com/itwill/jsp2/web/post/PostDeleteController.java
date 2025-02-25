package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
	private final PostService postService = PostService.INSTANCE; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		log.debug("doGet(id = {})", id);
		
		postService.delete(id);
		
		String url = request.getContextPath() + "/post/list";
		log.debug("redirect to {}", url);
		response.sendRedirect(url);
	}

}

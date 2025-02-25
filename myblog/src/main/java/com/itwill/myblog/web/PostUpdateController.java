package com.itwill.myblog.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.domain.Post;
import com.itwill.myblog.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet(name = "postUpdateController", urlPatterns = { "/post/update" })
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostUpdateController.class);
	private final PostService postService = PostService.INSTANCE; 

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post post = Post.builder().id(id).title(title).content(content).build();
		
		postService.update(post);
		
		String url = String.format(request.getContextPath() + "/post/details?id=%d", id);
		log.debug("redirect to {}", url);
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

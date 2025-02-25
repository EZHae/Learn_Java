package com.itwill.jsp2.web.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostModifyController
 */
@WebServlet(name = "postModifyController", urlPatterns = { "/post/modify" })
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostModifyController.class); 
	private final PostService postService = PostService.INSTANCE; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		log.debug("doGet(id = {})", id);
		
		Post post = postService.read(id);
		
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/WEB-INF/views/post/modify.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		log.debug("id = {}", id);
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
////		
//		Post post = Post.builder().id(id).title(title).content(content).build();
////		
//		postService.update(post);
////		
//		String url = String.format(request.getContextPath() + "/post/details?id=%d", id);
//		log.debug("redirect to {}", url);
//		response.sendRedirect(url);
//		// -> PRG(Post-Redirect-Get) 패턴
//	}

}

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
 * Servlet implementation class PostDetailsController
 */
@WebServlet(name = "postDetailsController", urlPatterns = { "/post/details" })
public class PostDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDetailsController.class);
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailsController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 파라미터 id(글 번호) 읽음.
		int id = Integer.parseInt(request.getParameter("id"));
		log.debug("doGet(id = {})", id);
		
		Post post = postService.read(id);
		
		request.setAttribute("post", post);
		
		// 뷰로 이동
		request.getRequestDispatcher("/WEB-INF/views/post/details.jsp").forward(request, response);;
	}

}

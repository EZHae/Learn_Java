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
 * Servlet implementation class PostListController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post/list" })
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostListController.class);
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListController() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("doGet()");
    	
    	// 서비스 계층의 메서드를 호출해서 뷰(테이블)를 그릴 수 있는 데이터를 읽어옴.
    	List<Post> list = postService.read();
    	log.debug("# of list = {}", list.size());
    	
    	// 읽은 데이터를 뷰에 전달
    	request.setAttribute("posts", list);
    	
    	// 뷰로 이동.
    	request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}

}

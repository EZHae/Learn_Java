package com.itwill.myblog.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.domain.Post;
import com.itwill.myblog.service.PostService;

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
    public PostListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("postListPage::doGet");
		
		int page = 1;
	    if (request.getParameter("page") != null) {
	        page = Integer.parseInt(request.getParameter("page"));
	    }
		
	    int startPage = (page-1);
		List<Post> posts = postService.postsByPage(startPage);
		int totalPage = postService.totalPage();
		
		request.setAttribute("currentPage", page);
		request.setAttribute("posts", posts);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}

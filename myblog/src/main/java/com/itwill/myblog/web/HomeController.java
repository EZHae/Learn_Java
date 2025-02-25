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

import com.itwill.myblog.domain.Image;
import com.itwill.myblog.domain.Post;
import com.itwill.myblog.service.PostService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "homeController", urlPatterns = { "" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final PostService postService = PostService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("homePage::doGet");
		
		List<Post> posts = postService.readRecent();
		for (Post post : posts) {
			List<Image> images = postService.readImage(post.getId());
			if (!images.isEmpty()) {
				post.setThumbnail(images.get(0).getImageName());
			}
		}
		
		request.setAttribute("posts", posts);
		
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}

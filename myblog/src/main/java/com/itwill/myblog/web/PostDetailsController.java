package com.itwill.myblog.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.myblog.domain.File;
import com.itwill.myblog.domain.Image;
import com.itwill.myblog.domain.Post;
import com.itwill.myblog.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public PostDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("postDetailsPage::doGet");
		
		int id = Integer.parseInt(request.getParameter("id"));
		Post post = postService.read(id);
		List<Image> images = postService.readImage(id);
		File file = postService.readFile(id);
		
		if (file != null) {
			String storedFileName = file.getFileName();

		    // UUID를 제외한 파일 이름 추출
		    String originalFileName = storedFileName.substring(storedFileName.indexOf("_") + 1);
		    request.setAttribute("originalFileName", originalFileName);
		}
		
		
		request.setAttribute("post", post);
		request.setAttribute("images", images);
		request.setAttribute("file", file);
		
		
		request.getRequestDispatcher("/WEB-INF/views/post/details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

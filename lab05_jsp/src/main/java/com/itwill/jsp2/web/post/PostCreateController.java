package com.itwill.jsp2.web.post;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostCreateController
 */
@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	private final PostService postService = PostService.INSTANCE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostCreateController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet()");

		request.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse resp)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");

		// 클라이언트가 보낸 양식 데이터에 포함된 요청 파라미터 값들(title, content, author)을 읽음
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		Post post = Post.builder().title(title).content(content).author(author).build();

		// 서비스 계층의 메서드를 호출 -> DB에 저장.
		postService.create(post);

		// 포스트 목록 페이지(post/list)로 이동(redirect)
		String url = req.getContextPath() + "/post/list";
		log.debug("redirect to {}", url);
		resp.sendRedirect(url);
		// -> PRG(Post-Redirect-Get) 패턴
	}

}

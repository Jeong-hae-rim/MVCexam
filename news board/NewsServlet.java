package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import model.vo.MeetingVO;
import model.vo.NewsVO;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		NewsDAO dao = new NewsDAO();
		if(action != null) {
		if (action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);
		} else if (action.equals("read")) {
			int id = Integer.parseInt(request.getParameter("id"));
			NewsVO vo =  dao.listOne(id);
	
				System.out.println(vo.getWriter());
				System.out.println(vo.getTitle());
				System.out.println(vo.getContent());
			
			request.setAttribute("read", vo);
		}
		}
			List<NewsVO> list = dao.listAll();
			for (NewsVO vo : list) {
				System.out.println(vo.getWriter());
				System.out.println(vo.getTitle());
				System.out.println(vo.getContent());
				System.out.println(vo.getWritedate());
				System.out.println(vo.getCnt());
			}
			request.setAttribute("list", list);
		
		request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		NewsDAO dao = new NewsDAO();
		NewsVO vo = new NewsVO();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		if (action.equals("insert")) {
			boolean result = dao.insert(vo);
		} else {
			vo.setId(Integer.parseInt(request.getParameter("id")));
			boolean result = dao.update(vo);
		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);
	}

}

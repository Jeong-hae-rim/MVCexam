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
		String key = request.getParameter("key");
		String id = request.getParameter("id");

		NewsDAO dao = new NewsDAO();
		if (action != null) {
			if (action.equals("delete")) {
				dao.delete(Integer.parseInt(id));
			} else if (action.equals("read")) {
				NewsVO vo = dao.listOne(Integer.parseInt(id));

				System.out.println(vo.getWriter());
				System.out.println(vo.getTitle());
				System.out.println(vo.getContent());

				request.setAttribute("read", vo);
			}
		}
		if (key == null) {
			List<NewsVO> list = dao.listAll();
			for (NewsVO vo : list) {
				System.out.println(vo.getId());
				System.out.println(vo.getWriter());
				System.out.println(vo.getTitle());
				System.out.println(vo.getContent());
				System.out.println(vo.getWritedate());
				System.out.println(vo.getCnt());
			}
			request.setAttribute("list", list);
		} else if (key != null) {
			String searchtype = request.getParameter("searchtype");
			List<NewsVO> list = null;
			if(searchtype.equals("writer")) { 
				list = dao.listWriter(key);
			}else if(searchtype.equals("title")) {
				list = dao.search(key,searchtype);
			} else if (searchtype.equals("content")) {
				list = dao.search(key,searchtype);
			}
			
			if (list.size() == 0) {
				list = dao.listAll();
				for (NewsVO vo : list) {
					System.out.println(vo.getId());
					System.out.println(vo.getWriter());
					System.out.println(vo.getTitle());
					System.out.println(vo.getContent());
					System.out.println(vo.getWritedate());
					System.out.println(vo.getCnt());
				}
				request.setAttribute("list", list);
				request.setAttribute("msg", key + "를 담고있는 글이 없습니다.");
			} else {
				request.setAttribute("list", list);
			}
			
		}
		
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

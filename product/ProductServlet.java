package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.ProductVO;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		if(session.getAttribute("list") == null) {
			session.setAttribute("list", new ProductVO());
		}
		ProductVO vo = (ProductVO)session.getAttribute("list");
		if(pid.equals("p001")) {
			vo.setApple(1);
		}else if(pid.equals("p002")) {
			vo.setBanana(1);
		}else{
			vo.setHanra(1);
		}
		
		request.getRequestDispatcher("/jspexam/productView.jsp").
        forward(request, response);

	}

}

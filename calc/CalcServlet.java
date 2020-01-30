package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcservlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		switch(request.getParameter("calc")) {
		case "+" :
			result = num1 + num2 ;
			break;
		case "-" :
			result = num1 - num2 ;
			break;
		case "*" :
			result = num1 * num2 ;
			break;
		case "/" :
			if(num2 != 0) {
			result = num1 / num2 ;
			break;
			} else {
				System.out.println("오류의 원인 : 나눗셈 연산 시 두 번째 숫자는 0일 수 없습니다.");
				request.getRequestDispatcher("/jspexam/errorResult.jsp").
		        forward(request, response);
				break;
			}
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("/jspexam/calcResult.jsp").
        forward(request, response);
	
		
	}

}

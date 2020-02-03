package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/news")
public class HangeulFilter implements Filter {
    public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		//System.out.println("HangeulFilter 수행 - BEFORE");
		if(((HttpServletRequest)request).getMethod().equals("POST")) //자손형으로 강제 형변환을 해줘야 함
           request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		//System.out.println("HangeulFilter 수행 - ATFER");
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

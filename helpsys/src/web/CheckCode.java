package web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCode
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		try {
			//使用反射定义方法
			Method method=getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			//调用方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//这里接受ajax提交的请求
		//从请求中获得用户输入的验证码
		String userCode = request.getParameter("code");
		//从session中获得生成时保存下来的验证码
		String sessionCode = (String) request.getSession().getAttribute("code");
		//比较两个验证码，返回比较结果
		int result = 0;
		if(userCode.equals(sessionCode)){
			result=1;
			response.getWriter().print("{\"valid\":"+result+"}");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

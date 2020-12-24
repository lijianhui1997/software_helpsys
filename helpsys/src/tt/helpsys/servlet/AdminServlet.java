package tt.helpsys.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tt.helpsys.entity.Admin;
import tt.helpsys.service.IAdminService;
import tt.helpsys.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AdminServlet
 * @author TRY
 */
@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
	private IAdminService adminService = new AdminServiceImpl();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String action= request.getParameter("action");
			//which  if else  或者反射都可以,现在用反射
		try {
			Method method=getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			//调用方法
			method.invoke(this, request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username=request.getParameter("username");
		 String password=request.getParameter("password");
		 Admin admin=adminService.login(username, password);
		 if(admin == null){
			 //用户名或者密码错误  json方式输出
			 response.getWriter().print("{\"res\":-1,\"info\":\"用户名或密码错误\"}");
		 }else{
			 //登录成功
			 //用session保存数据
			request.getSession().setAttribute("admin", admin);
			 response.getWriter().print("{\"res\":1,\"info\":\"登录成功\"}");
				
		 }
		 
	}

	//退出
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session=request.getSession();
		  session.removeAttribute("admin");
		response.getWriter().println("退出成功！");
	}

	//修改密码
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		 //获取session中的admin
		Admin admin= (Admin) request.getSession().getAttribute("admin");
		String name=admin.getName();
		String pwd=admin.getPwd();
		String oldpassword=request.getParameter("oldpassword");
		//验证session中的密码是否与输入的旧密码相同
		if(pwd.equals(oldpassword)){
			String newpassword=request.getParameter("newpassword");
			admin.setName(name);
			admin.setPwd(newpassword);
			int res=adminService.updatepwd(admin);
			//将session中的pwd更换掉
			admin.setName(name);
			admin.setPwd(newpassword);
			request.getSession().setAttribute("admin", admin);
		}
		response.getWriter().print("{\"res\": 1, \"info\":\"修改成功！\"}");
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

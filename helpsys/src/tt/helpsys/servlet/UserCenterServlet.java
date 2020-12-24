package tt.helpsys.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tt.helpsys.entity.Admin;
import tt.helpsys.entity.User;
import tt.helpsys.service.IUserService;
import tt.helpsys.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserCenterServlet
 * @author TRY
 */
@WebServlet("/userCenterServlet")
public class UserCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService=new UserServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");	
		try {
			//使用反射定义方法
			Method method=getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);			
			//调用方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 登录成功
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Gson gson = new Gson();
		String dataJSON = gson.toJson(user);
		response.getWriter().print("{\"res\": 1, \"data\":" + dataJSON + "}");
	}

	
	private void updatePw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取session中的user用户名
		User user = (User) request.getSession().getAttribute("user");
		String username=user.getUsername();
		String password=user.getPassword();
		String oldpassword=request.getParameter("oldpassword");
		//验证session中的密码是否与输入的旧密码相同
		if(password.equals(oldpassword)){
			String newpassword=request.getParameter("newpassword");
			user.setUsername(username);
			user.setPassword(newpassword);
			userService.updatePw(user);
			user.setUsername(username);
			user.setPassword(newpassword);
			request.getSession().setAttribute("user", user);
		}
		response.getWriter().print("{\"res\": 1, \"info\":\"修改成功！\"}");
	}

	
	//修改信息
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 获取参数
    			String realname = request.getParameter("realname");
    			String sex = request.getParameter("sex");
    			//String hobbys = request.getParameter("hobbys");
    			String birthday = request.getParameter("birthday");
    			String city = request.getParameter("city");
    			String email = request.getParameter("email");
    			String qq = request.getParameter("qq");

    			// 获取当前用户对象
    			HttpSession session = request.getSession();
    			User sessionUser = (User) session.getAttribute("user");
    			User user = sessionUser.clone();
    			
    			// 重设其值
    			if (realname != null && realname.trim().length()>0){
    				user.setRealname(realname);
    			}
    			if (sex != null && sex.trim().length()>0){
    				user.setSex(sex);
    			}
    			/*if (hobbys != null && hobbys.trim().length()>0){
    				user.setHobbys(hobbys);
    			}*/
    			if (birthday != null && birthday.trim().length()>0){
    				user.setBirthday(birthday);
    			}
    			if (city != null && city.trim().length()>0){
    				user.setCity(city);
    			}
    			if (email != null && email.trim().length()>0){
    				user.setEmail(email);
    			}
    			if (qq != null && qq.trim().length()>0){
    				user.setQq(qq);
    			}
    			
    			// 更新
    			int res = userService.update(user);
    			
    			if (res == 1){	// 更新成功
    				// 获取更新后的数据
    				user = userService.userLogin(user.getUsername(), user.getPassword());

    				// 更新Session
    				session.setAttribute("user", user);
    				
    				Gson gson = new Gson();
    				String dataJSON = gson.toJson(user);
    				
    				response.getWriter().print("{\"res\": 1, \"data\":" + dataJSON + "}");
    			}
    			else {
    				response.getWriter().print("{\"res\": "+res+", \"info\":\"修改失败!\"}");
    			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

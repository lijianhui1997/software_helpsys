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

import tt.helpsys.entity.User;
import tt.helpsys.service.IUserService;
import tt.helpsys.service.impl.UserServiceImpl;
/**
 * Servlet implementation class MessageServlet
 * @author TRY
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 4122206234828080374L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	//验证用户名是否存在
	private void isExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		boolean result=userService.isExist(username);
		response.getWriter().print("{\"valid\":"+result+"}");
	}


	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();//使session无效
		response.getWriter().print("{\"res\": 1, \"info\":\"欢迎下次登录！\"}");
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		//String hobbys = request.getParameter("hobbys");
		String birthday = request.getParameter("birthday");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//添加验证码功能
		String usercode=request.getParameter("usercode");
		HttpSession session = request.getSession();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRealname(realname);
		user.setSex(sex);
		//user.setHobbys(hobbys);
		user.setBirthday(birthday);
		user.setCity(city);
		user.setEmail(email);
		user.setQq(qq);
		
		int res=0;
		//int res = userService.userRegister(user);
		
		if(!usercode.equalsIgnoreCase(session.getAttribute("code").toString())){
			response.getWriter().print("{\"res\": " + res + ", \"info\":\"注册失败\"}");		
			//response.sendRedirect("register.jsp");
		}
		
		else if (( res = userService.userRegister(user)) == 1&&usercode.equalsIgnoreCase(session.getAttribute("code").toString())){
			// 自动登录
			user = userService.userLogin(username, password);
			// 登录成功
			HttpSession session2 = request.getSession();
			session2.setAttribute("user", user);

			Gson gson = new Gson();
			String dataJSON = gson.toJson(user);
			response.getWriter().print("{\"res\": 1, \"data\":" + dataJSON + "}");
		}
		else {
			response.getWriter().print("{\"res\": " + res + ", \"info\":\"注册失败\"}");
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null || username.trim().length() < 6 || username.trim().length() > 16 || password == null
				|| password.trim().length() < 6 || password.trim().length() > 16) {
			// 信息有问题重新登录
			response.getWriter().print("{\"res\": -1, \"info\":\"登录信息填写有误，请不要带有非法字符！\"}");
		}
		
		User user = userService.userLogin(username, password);
		
		if (user == null){
			// 登录失败 用户名或密码错误
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名或密码错误，请重新输入！\"}");
		}
		else if(user.getState() == -1){
			// 登录失败 帐号被封
			response.getWriter().print("{\"res\": -1, \"info\":\"你的账号已被禁用！\"}");
		}
		else{
			// 用户登录重复判断
			
			// 登录成功
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			Gson gson = new Gson();
			String dataJSON = gson.toJson(user);
			response.getWriter().print("{\"res\": 1, \"data\":" + dataJSON + "}");
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

package tt.helpsys.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tt.helpsys.entity.Theme;
import tt.helpsys.service.IThemeService;
import tt.helpsys.service.impl.ThemeServiceImpl;
import tt.helpsys.util.Page;

/**
 * Servlet implementation class UserThemeServlet
 * @author TRY
 */
@WebServlet("/userThemeServlet")
public class UserThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Theme theme=new Theme();
	private IThemeService themeService=new ThemeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	//获取全部主题信息
		private void getAllTheme(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			List<Theme> list=themeService.getAll();
			Gson gson=new Gson();
			String json=gson.toJson(list);
			response.getWriter().print("{\"theme\":"+json+"}");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

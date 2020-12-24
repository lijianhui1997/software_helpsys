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
import com.google.gson.GsonBuilder;

import tt.helpsys.entity.Message;
import tt.helpsys.entity.MessageCriteria;
import tt.helpsys.entity.Reply;
import tt.helpsys.entity.User;
import tt.helpsys.entity.MessageCriteria.OrderRuleEnum;
import tt.helpsys.service.IMessageService;
import tt.helpsys.service.impl.MessageServiceImpl;
import tt.helpsys.util.HTMLReplace;
import tt.helpsys.util.Page;

/**
 * Servlet implementation class UserMessageServlet
 * @author TRY
 */
@WebServlet("/userMessageServlet")
public class UserMessageServlet extends HttpServlet {
	private IMessageService messageservice=new MessageServiceImpl();
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//发布帖子  恢复帖子  查看我的帖子
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
//发帖
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String msgtopic=request.getParameter("msgtopic");//帖子标题
			String theid=request.getParameter("theid");//主题ID
			String msgcontents=request.getParameter("msgcontents");//主题内容
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			int userid=user.getUserid();//用户ID
			//String msgip=IPUtil.getIP(request);//发帖人的IP
			msgcontents=HTMLReplace.replace(msgcontents);//帖子内容转换为HTML格式
			Message message=new Message();
			message.setMsgtopic(msgtopic);
			message.setTheid(Integer.parseInt(theid));
			message.setMsgcontents(msgcontents);
			message.setUserid(userid);
			//message.setMsgip(msgip);
		int rs=messageservice.addMsg(message);
		if(rs>0){
			response.getWriter().print("{\"res\":1,\"info\":\"发布成功\"}");
		}else{
			response.getWriter().print("{\"res\":-1,\"info\":\"发布失败\"}");
		}
	}
	
	//查询自己所提出的问题
	private void getMyMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String pageNum=request.getParameter("pageNum");
	if(pageNum == null || pageNum.equals("")){
		pageNum="1";
	}
	Page page=new Page();
	page.setCurPage(Integer.parseInt(pageNum));
	HttpSession session=request.getSession();
	User user=(User) session.getAttribute("user");
	int  userid=user.getUserid();
	//创建封装查询条件对象
	MessageCriteria messageCriteria=new MessageCriteria();
	messageCriteria.setUserid(userid);
	messageCriteria.setOrderRule(OrderRuleEnum.ORDER_BY_MSG_TIME);//排序条件
	messageCriteria.setState(0);//查询非禁用状态
	page=messageservice.search(messageCriteria,page);
	Gson gson=new GsonBuilder().setDateFormat("yy-MM-dd").create();
	String json=gson.toJson(page);
	response.getWriter().print("{\"res\":1,\"message\":" + json + "}");
	
	}
	
	//回帖
	private void replyMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msgId=request.getParameter("msgId");
		String replycontent=request.getParameter("replycontent");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		int userid=user.getUserid();//用户ID
		//String replyip=IPUtil.getIP(request);//获取IP
		replycontent=HTMLReplace.replace(replycontent);
		Reply reply=new Reply();
				reply.setUserid(userid);
				reply.setMsgid(Integer.parseInt(msgId));
				reply.setReplycontents(replycontent);
				//reply.setReplyip(replyip);
				int rs=messageservice.replyMsg(reply);
				if(rs>0){
					response.getWriter().print("{\"res\":1,\"info\":\"回复成功\"}");
				}else{
					response.getWriter().print("{\"res\":-1,\"info\":\"回复失败\"}");
				}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

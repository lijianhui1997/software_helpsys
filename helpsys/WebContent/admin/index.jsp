<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<title>学生互助学习系统</title>
<script type="text/javascript">
	$(function (){
		getMsgCount();
		getReplyCount();
	});
	
	function getMsgCount(){
		$.ajax({
			url : "adminMessageServlet",
			type : "post",
			async : "true",
			data : {"action" : "getMsgCount"},
			dataType : "json",
			success : function(data){
				if (data.res == 1){
					$("#todayMsg").text(data.data.today);
					$("#weekMsg").text(data.data.week);
					$("#monthMsg").text(data.data.month);
				}
				else {
					alert(data.info);
				}
			}
		});
	}
	function getReplyCount(){
		$.ajax({
			url : "adminMessageServlet",
			type : "post",
			async : "true",
			data : {"action" : "getReplyCount"},
			dataType : "json",
			success : function(data){
				if (data.res == 1){
					$("#todayReply").text(data.data.today);
					$("#weekReply").text(data.data.week);
					$("#monthReply").text(data.data.month);
				}
				else {
					alert(data.info);
				}
			}
		});
	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<h3>数据统计</h3>
				<table class="table table-striped">
				  <caption>发布问题统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日发布问题</td>
				      <td id="todayMsg">0</td>
				    </tr>
				    <tr>
				      <td>本周发布问题</td>
				      <td id="weekMsg">0</td>
				    </tr>
				    <tr>
				      <td>本月发布问题</td>
				      <td id="monthMsg">0</td>
				    </tr>
				  </tbody>
				</table>
				<table class="table table-striped">
				  <caption>回复统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日回复</td>
				      <td id="todayReply">0</td>
				    </tr>
				    <tr>
				      <td>本周回复</td>
				      <td id="weekReply">0</td>
				    </tr>
				    <tr>
				      <td>本月回复</td>
				      <td id="monthReply">0</td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>
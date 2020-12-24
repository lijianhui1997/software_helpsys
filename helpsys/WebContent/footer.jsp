<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>尾巴</title>
</head>
<body>
<div class="footer">
		<div class="container">
			<div class="row footer-top">
				<div class="col-sm-6 col-lg-6">
					<h3><strong>学生互助学习系统</strong></h3>
					<p><strong>本项目为JavaWeb项目(学生互助学习系统)</strong></p>
				</div>
				<div class="col-sm-6  col-lg-5 col-lg-offset-1">
					<div class="row about">
						<div class="col-xs-3">
							<h4><strong>关于</strong></h4>
							<ul class="list-unstyled">
								<li><a href="AboutUs.jsp">关于我们</a></li>
								<li><a href="https://www.baidu.com">友情链接</a></li>
							</ul>
						</div>
						<div class="col-xs-3">
							<h4><strong>联系方式</strong></h4>
							<ul class="list-unstyled">
								<li><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=&site=qq&menu=yes" 
								    title="QQ联系" target="_blank">QQ联系</a></li>
								<li><a href=""
									title="官方微博" target="_blank">新浪微博</a></li>
								<!--注释掉  <li><a href="mailto:1029012080@qq.com">电子邮件</a></li> -->
							</ul>
						</div>
						
					</div>

				</div>

			</div>
			<hr/>
			<div class="row footer-bottom">
				<ul class="list-inline text-center">
					<li><strong>MADE FROM TRY</strong></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
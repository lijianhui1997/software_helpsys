<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%  
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

	var pageNum = 1;
	
	function getMsg(){
		// Ajax 获取最新的问题
		$.ajax({
			url : "messageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "topNew", "pageNum": pageNum},
			dataType : "json",
			success : function(data) {
				if (data.res==1){
					$.each(data.newMsg.data, function(index, msgItem) {
						var msg = $(".template").clone();
						msg.show();
						msg.removeClass("template");
						msg.find(".msg-title").text(msgItem.msgtopic);
						msg.find(".msg-title").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".author").text(msgItem.realname+" •  "+msgItem.msgtime);
						msg.find(".msgcontent").html(msgItem.msgcontents);
						msg.find(".count").text(msgItem.accessCount+"次浏览 • "+msgItem.replyCount+"个回复 • ");
						msg.find(".msglink").attr("href", "message.jsp?msgid="+msgItem.msgid);
						
						$("#msgList").append(msg);
					});
	
					pageNum++;
					// 加载更多
					if (parseInt(data.newMsg.totalPage) >= parseInt(pageNum)){
						$("#loadmore").html("加载更多...");
						$("#loadmore").removeAttr("disabled");
					}
					else{
						$("#loadmore").html("没有更多数据了！");
						$("#loadmore").attr("disabled","disabled");
					}
				} 
			}

		});
	}
	$(function(){
		// 加载数据
		getMsg();
	});

</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row" id="msgList">
			<div class="col-sm-12 msgitem template">
				<h3><a class="msg-title"></a></h3>
				<p class="author"></p>
				<p class="msgcontent"></p>
				<div class="rightinfo">
					<span class="count"></span>
					<a class="msglink">详细&gt;&gt;</a>
				</div>
			</div>
			<!-- <div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">不错哦221133</a></h3>
				<p class="author">哈哈 •  8/29</p>
				<p class="msgcontent">六个字六个字</p>
				<div class="rightinfo">
					<span class="count">11次浏览 • 0个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div>
			<div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">网站Bug请在此留下</a></h3>
				<p class="author">冰城小象 •  8/28</p>
				<p class="msgcontent">网站Bug请在此留下</p>
				<div class="rightinfo">
					<span class="count">4次浏览 • 0个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div>
			<div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">如何完全卸载MySQL数据库</a></h3>
				<p class="author">冰城小象 •  8/28</p>
				<p class="msgcontent">1.&nbsp;控制面板——》所有控制面板项——》程序和功能，卸载mysql&nbsp;server!</p>
				<div class="rightinfo">
					<span class="count">9次浏览 • 5个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div>
			<div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">盒子模型是怎么回事?</a></h3>
				<p class="author">冰城小象 •  8/28</p>
				<p class="msgcontent">盒子模型的四要素分别是：<br>content(内容)、<br>border(边框)、<br>padding(内边距)、<br>margin(外边距)，</p>
				<div class="rightinfo">
					<span class="count">3次浏览 • 1个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div>
			<div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">JDK配置环境变量</a></h3>
				<p class="author">冰城小象 •  8/28</p>
				<p class="msgcontent">如题:</p>
				<div class="rightinfo">
					<span class="count">8次浏览 • 5个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div> -->
		</div>
		<div class="row">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block" 
				onclick="javascript:getMsg();">加载更多...</button>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>
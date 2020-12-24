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
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<link rel="stylesheet" href="bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>学生互助学习系统</title>
<script type="text/javascript">
	$(function(){
		 $(".form_datetime").datetimepicker({
		        format: 'yyyy-mm-dd',
		        minView:'month',
		        language: 'zh-CN',
		        autoclose: true,//选中自动关闭
		        startDate:'1900-01-01',
		        todayBtn: true//显示今日按钮
		    });
		 
		 getUser();
		 
		 validateForm();
	});
	
	function getUser(){
		// ajax 异步请求 获取个人信息
		$.get("userCenterServlet",{"action":"getUser"},function(data){
			if(data.res==1){
				var user=data.user;
				$("input[name='realname']").val(data.realname);
				if(user.sex=="女"){
				$(":radio=[value='女']").prop("checked",true);
				}
				var hobbys=user.hobys.split(";");
				/*for(var i = 0;i < hobbys.length;i++){
					$(":checkbox=[value='"+hobbys[i]+"']").prop("checked",true);
				}*/
				
				$("input[name='birthday']").val(user.birthday);
				$("input[name='city']").val(data.user.city);
				$("input[name='email']").val(user.email);
				$("input[name='qq']").val(user.qq);	
			}else{
				$(".text-warning").text(data.user);
			}
		},"json");
	}
	
	function validateForm(){
		// 验证表单
		$("#updateform").bootstrapValidator({
		 	message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                
                realname:{
                 	messaage: 'The realname is not valid',
                	validators : {
                		notEmpty: {
                       		message: '真实姓名不能为空'
                    	}
                	}
                },
                /*hobbys:{
                 	messaage: 'The hobbys is not valid',
                	validators : {
                		notEmpty: {
                       		message: '爱好不能为空'
                    	}
                	}
                },*/
                city:{
                 	messaage: 'The city is not valid',
                	validators : {
                		notEmpty: {
                       		message: '城市不能为空'
                    	}
                	}
                },
                email:{
                 	messaage: 'The email is not valid',
                	validators : {
                		notEmpty: {
                       		message: '邮箱不能为空'
                    	}
                     
                	}
                },
                qq:{
                 	messaage: 'The QQ is not valid',
                	validators : {
                		notEmpty: {
                       		message: 'QQ不能为空'
                    	}
                	}
                }
            }
        });
	}
	function update(){
		// ajax 异步请求Servlet 更新个人信息
		var formData=$("#updateform").serialize();
		$.ajax({
			url:"userCenterServlet?action=update",// 请求地址
			type:"POST",    // 请求类型
			async:"true",   // 是否异步方式
			data:formData,  // 表单的序列化
			dataType : "json",
			success:function(data){
				if(data.res==1){
					alert("更新成功！");
					window.location.replace("index.jsp");
				}
				else {
					$(".text-warning").text("更新失败！");
			 		$("input[name='realname']").val("");
					//$("input[name='hobbys']").val("");
					$("input[name='birthday']").val("");
					$("input[name='city']").val("");
					$("input[name='email']").val("");
					$("input[name='qq']").val(""); 
				}
			}
		});
		return false;
	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="../header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>修改资料</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="updateform" method="post">
			<div class="form-group">
				<label for="realname" class="col-sm-2 control-label">真实姓名：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="realname" placeholder="请输入真实姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-4">
					<label class="radio-inline">
				        <input type="radio" name="sex" value="男" checked> 男
					</label>
				    <label class="radio-inline">
				        <input type="radio" name="sex" value="女"> 女
				    </label>
				</div>
			</div>
			<!--  <div class="form-group">
				<label for="hobbys" class="col-sm-2 control-label">爱好：</label>
				<div class="col-sm-4">
					<label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="吃饭">吃饭
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="睡觉">睡觉
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="打豆豆">打豆豆
				    </label>
				</div>
			</div>-->
			
			<div class="form-group">
				<label for="birthday" class="col-sm-2 control-label">生日：</label>
				<div class="col-sm-4">
					<div class="input-group date form_datetime" data-date-format="dd-MM-yyyy" data-link-field="dtp_input1">
	                    <input class="form-control" size="16" type="text" name="birthday" readonly>
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	                </div>
                </div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-2 control-label">城市：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="city" placeholder="请输入所在城市">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱：</label>
				<div class="col-sm-4">
					<input type="email" class="form-control" name="email" placeholder="请输入邮箱">
				</div>
			</div>
			<div class="form-group">
				<label for="qq" class="col-sm-2 control-label">QQ：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="qq" placeholder="请输入QQ">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<button class="btn btn-success btn-block" onclick="update();">提交</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include flush="fasle" page="../footer.jsp" />
</body>
</html>
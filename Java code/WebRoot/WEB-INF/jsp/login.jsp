<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>智慧仓库管理系统-欢迎界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common2.css" />
	
	<style type="text/css">
		.Div {
			width: 500px;
			height: 300px;
			margin: -150px 0px 0px -250px;
			position: absolute;
			top: 50%;
			left: 50%;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			
		
		
		});
	</script>
</head>
  
  <body>
	<div class="div">
		<div class="easyui-panel" title="登陆系统" style="width:400px;padding:30px 70px 20px 70px">
			<form id="loginForm" name="loginForm" action="userAction_login" method="post">
				<div style="margin-bottom:10px">
					<input name="loginname" class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input name="password" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input type="checkbox" checked="checked">
					<span>记住密码</span>
				</div>
			
				<div>
					<a id="loginButton" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
						<span style="font-size:14px;">登陆</span>
					</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("base", request.getContextPath()); //获得站点根目录
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>	
<script>
		function register() {
			//取得名字和密码
			var name = document.getElementById("name").value;
			var passwd = document.getElementById("passwd").value;
			//ajax post传送数据

			$.ajax({
				type : 'post',
				url : "${base}/register",
				data : "name=" + name + "&passwd=" + passwd,
				success : successInfo,
				dataType : "json"
			});
		}

		function successInfo(json) {
			var success = json.success;// tipDiv
			if (success == "success") {
				alert("注册成功");
			}

		}
	</script>

</head>
<body>

	用户名
	<input id="name" name="name" type="text">
	<br>
	<br> 密&nbsp;&nbsp;&nbsp; 码
	<input id="passwd" name="passwd" type="text" />
	<br>
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="register()">提交</button>
</body>
</html>
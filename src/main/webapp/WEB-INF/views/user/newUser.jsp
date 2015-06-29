<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script src="<c:url value="/resources/js/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
<link  href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="Bookmark" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript">
	function checkPassword() {

		if ($("#password").val() == "") {

			return;
		}
		if ($("#password").val() != $("#password1").val()) {

			$("#passwordCheck").html("<font color='red'>两次密码不相同。</fond>");
		} else {

			$("#passwordCheck").html("<font color='#999999'></fond>");
		}
	}
	function checkUserName() {

		var userName = $("#userName").val();
		$.ajax({ url: "<c:url value='/checkUserName' />", data:{'userName':userName}, context: document.body, success: function(){
	        alert(1);
	      }});
	}

	function callBackCheckUserName(exist) {

		if (exist) {
			$("#checkUserName").html("<font color='red'>用户名已存在。</fond>");
			$("#userName").select();
		} else {
			$("#checkUserName").html("<font color='#999999'>用户名可以使用。</fond>");
		}
	}

	function checkSubmit() {

		if ($("#userName").val() == "") {
			alert("用户名不能为空。请填写后保存。");
			return false;
		}
		if ($("#password").val() == "") {
			alert("密码不能为空。请填写后保存。");
			return false;
		}
		if ($("#password1").val() == "") {
			alert("确认密码不能为空。请填写后保存。");
			return false;
		}
		if ($("#password1").val() != $("#password").val()) {
			alert("两次密码不相同，请修改后保存。");
			return false;
		}
	}
</script>
</head>
<body style="align:center">

	<div id="bodyDiv" class="container" style="width:400px;top:200px;position:relative">
		<form action="<%=request.getContextPath()%>/user.do?method=saveUser" method="post" onsubmit="return checkSubmit()">
		
		<div class="form-group">
			<label for="usernameLabel">*登录名</label>
   			<input type="text" class="form-control" id="usernameLabel" placeholder="username" onblur="checkUserName()" >
		</div>
		<div class="form-group">
			<label for="passwordLabel">*密码</label>
   			<input type="text" class="form-control" id="passwordLabel" placeholder="password">
		</div>
		<div class="form-group">
			<label for="password1Label">*密码确认</label>
   			<input type="text" class="form-control" id="password1Label" placeholder="password1">
		</div>
		<button type="submit" class="btn btn-primary btn-lg btn-block">提交</button>
		
		<!-- 
		
		
			<table width="950px" align="center">
				<tr>
					<td width="100%" align="center">
						<table width="100%" align="center">
							<tr>
								<td width="30%">*登录名</td>
								<td width="70%"><input type="text" id="userName" name="username" onblur="checkUserName()" /><span id="checkUserName"></span></td>
							</tr>
							<tr>
								<td>*密码</td>
								<td><input type="password" name="password" id="password" onblur="checkPassword()" /></td>
							</tr>
							<tr>
								<td>*密码确认</td>
								<td><input type="password" name="password1" id="password1" onblur="checkPassword()" /> <span id="passwordCheck"></span></td>
							</tr>
							<tr id="submitTR">
								<td colspan="2"><input type="submit" id="submit" value="保存" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			 -->
		</form>
	</div>
</body>
</html>
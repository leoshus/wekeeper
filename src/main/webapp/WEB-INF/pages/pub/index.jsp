<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome wekeeper</title>
<script type="text/javascript">
	<!--
	function reloadVerifyCode(){
		document.getElementById('verifyCode').setAttribute('src','${pageContext.request.contextPath}/landing/fetchVerifyCode');
	}
	//-->
</script>
</head>
<body>
	<div align="center">
		<form action="<%= request.getContextPath() %>/landing/check">
		<table>
			<tr>
				<td>username:</td>
				<td><input type="text" name="username" id="username"/></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="password" name="password" id="password"/></td>
			</tr>
			<tr>
				<td>verifyCode:</td>
				<td><img id="verifyCode" alt="verifyCode" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/landing/fetchVerifyCode"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"></td>
			</tr>
			
		</table>
			
			
			
			
		</form>
	</div>
</body>
</html>
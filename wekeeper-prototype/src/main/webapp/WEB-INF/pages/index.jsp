<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<c:if test="${not empty message }">
			<div style="color:red;font-size:22px;">${message }</div>
		</c:if>
		<form action="<%= request.getContextPath() %>/landing/check" method="post">
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
				<td><img id="verifyCode" alt="verifyCode" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/landing/fetchVerifyCode"></td>
				<td><input type="text" name="verifyCode" id="verifyCode"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"></td>
			</tr>
			
		</table>
			
			
			
			
		</form>
	</div>
</body>
</html>
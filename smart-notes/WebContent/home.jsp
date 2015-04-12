<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.sn.models.UserInfo"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<h3>Hi ${userInfo.fullName}</h3>
<strong>Your Email</strong>: ${userInfo.email}<br>
<strong>Your Type</strong>: ${userInfo.userType}<br>
<strong>Your Status</strong>: ${userInfo.status}<br>
<strong>Your C.Date</strong>: <fmt:formatDate value="${userInfo.createDate}" pattern="dd MMMMM yyyy" /><br>
<strong>Your C.DateTime</strong>: <fmt:formatDate value="${userInfo.createDate}" pattern="dd MMMMM yyyy hh:mm:ss aaa" /><br>
<br/>
<a href="${pageContext.request.contextPath}/note/list">Note List</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/note/create">Create Note</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/note/edit?id=20&uid=21">Edit Note</a>&nbsp;&nbsp;&nbsp;
<br/><br/>
<form action="logoutApp" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>
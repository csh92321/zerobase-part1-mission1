<%@page import="dto.HistoryDTO"%>
<%@page import="main.wifi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	wifi wifi = new wifi();
	request.setCharacterEncoding("UTF-8");
	int id = Integer.parseInt(request.getParameter("id"));
	wifi.historyDelete(id);
	%>
	<script type="text/javascript">
		window.location.replace("history.jsp")
	</script>
	
</body>
</html>
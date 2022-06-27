<%@page import="dto.HistoryDTO"%>
<%@page import="main.wifi"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#historyTable {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#historyTable td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#historyTable tr:nth-child(even) {
	background-color: #f2f2f2;
}

#historyTable tr:hover {
	background-color: #ddd;
}

#historyTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #87CEED;
	color: white;
}
</style>
</head>

<h1>위치 히스토리 목록</h1>

<a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="wifiLoad.jsp">Open API 와이파이 정보 가져오기</a>
<p></p>

<body>
	<%
	wifi wifi = new wifi();
	List<HistoryDTO> dto = wifi.historySelect();
	%>

	<table id="historyTable">
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>삭제</th>
		</tr>
		<%
		for (HistoryDTO historyItems : dto) {
		%>
		<tr>
			<td> <%=historyItems.getId()%> </td>
			<td> <%=historyItems.getLnt()%> </td>
			<td> <%=historyItems.getLat()%> </td>
			<td> <%=historyItems.getCheckDttm()%> </td>
			<td>
			<form name="location" action="deleteHistory.jsp" method="post">
			<input type="hidden" name="id" value=<%=historyItems.getId()%>>
			 <input type="submit" name="id" value="삭제">
			 </form>
		</td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>
<%@page import="dto.wifiDTO"%>
<%@page import="main.*"%>
<%@page import="java.sql.*"%> 
<%@page import="java.io.*"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
text-align : center;
}
</style>
<title>Insert title here</title>
</head>
<body>
<%
wifi wifi = new wifi();
wifi.apiDownload();
%>
<h1>14493개의 WIFI 정보를 불러왔습니다.</h1>
<a href="index.jsp">홈으로 가기</a>

</body>
</html>
<%@page import="java.util.List"%>
<%@page import="main.wifi"%>
<%@page import="main.main"%>
<%@page import="dto.wifiDTO"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#wifiTable {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#wifiTable td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#wifiTable tr:nth-child(even){background-color: #f2f2f2;}

#wifiTable tr:hover {background-color: #ddd;}

#wifiTable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #87CEED;
  color: white;
}
</style>
<script>
let url;
function locationGet() {
(function location(){
navigator.geolocation.getCurrentPosition(
function(position) {
console.log("위도 : " + position.coords.latitude);
var lat = position.coords.latitude;
console.log("경도 : " + position.coords.longitude);
var lnt = position.coords.longitude
url = "index.jsp?lat="+lat+"&lnt="+lnt;
window.location.assign(url);   
}, 
);         
})();

}
</script>   

</head>
<body>

<h1>WIFI 정보 구하기</h1>

<a href="index.jsp">홈</a>
|
<a href="history.jsp">위치 히스토리 목록</a>
|
<a href="wifiLoad.jsp">Open API 와이파이 정보 가져오기</a>
<p></p>
<%
		  	String lat = request.getParameter("lat");
			String lnt = request.getParameter("lnt");
			
			if (lat == null || lnt == null) {
				lat = "0.0";
				lnt = "0.0";
			}
%>
<dev style="display:flex">
<form name="location" action="result.jsp" method="post">
X좌표 : 
 <input type="text" name="LAT" value="<%=lat%>" >
 Y좌표 :  
 <input type="text" name="LNT" value="<%=lnt%>">
 <input type="submit" name="search" value="근처 WIPI 정보 보기">
 </form>
   <button name="getPoint" onclick="locationGet()" value="현재 위치 가져오기" style="margin-left:5px">
  현재 위치 가져오기
 </button>
 </dev>
 <p></p>


<table id="wifiTable">
  <tr>
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>
</table>

</body>
</html>



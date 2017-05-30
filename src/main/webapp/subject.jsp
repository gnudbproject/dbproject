<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>
<% 


	int subjectCount=((Integer)request.getAttribute("subjectCount")).intValue();
	String[] subjectName = new String[subjectCount];
	subjectName = (String[])request.getAttribute("subjectName");
	
	int allSubjectCount=((Integer)request.getAttribute("allSubjectCount")).intValue();
	String[] allSubjectName = new String[allSubjectCount];
	allSubjectName = (String[])request.getAttribute("allSubjectName");
	
	String[] stamp = new String[subjectCount];
	stamp = (String[])request.getAttribute("stamp");
	
	String[] maxDay = new String[allSubjectCount];
	maxDay = (String[])request.getAttribute("maxDay");
	
	String[] requestyn = new String[allSubjectCount];
	requestyn = (String[])request.getAttribute("requestyn");
%>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="./commons/top.jspf"%>
<title>Main.jsp</title>
</head>
<body>
	<%@ include file="./commons/left_sidemenu.jspf"%>
	<div id=main_content>

	
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;수강신청 가능한 과목&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;수강 신청하기&nbsp;&nbsp;</td>
	</tr>
	<% 
	for(int i = 0; i < allSubjectCount; i++) {
		if(requestyn[i].equals("y")) {
	%>
	<form action="/subject/SubjectRequest" method="post">
	<tr>
	<td> <%=allSubjectName[i] %></td>
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="maxDay" value="<%=maxDay[i] %>"> 
	<input type="submit" value="수강 신청"> 
	</td>
	</tr>
	</form>
	<%
		}
	}
	%>
	</table>
	
	
	<br>
	
	
	</div>
	

</body>
</html>
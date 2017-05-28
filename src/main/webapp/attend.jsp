<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 


	int subjectCount=((Integer)request.getAttribute("subjectCount")).intValue();
	String[] subjectName = new String[subjectCount];
	subjectName = (String[])request.getAttribute("subjectName");
	
	int userCheckynCount=((Integer)request.getAttribute("userCheckynCount")).intValue();
	String[] userCheckyn = new String[userCheckynCount];
	userCheckyn = (String[])request.getAttribute("userCheckyn");
	String[] userStamp = new String[userCheckynCount];
	userStamp = (String[])request.getAttribute("userStamp");
	
	String[] suserCday = new String[userCheckynCount];
	suserCday = (String[])request.getAttribute("suserCday");
	
	String[] stamp = new String[subjectCount];
	stamp = (String[])request.getAttribute("stamp");
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

	<script type="text/javascript">
	
	var stamp = new Array(<%=subjectCount%>);
	
	<%for(int i = 0; i < subjectCount; i++) {%>
	stamp[<%=i %>] = "<%=stamp[i] %>";
	<%}%>
	
	function change() {;
	var frm = document.frm;
	
	var subjectNameList = frm.subjectNameList;
	var txt = frm.combo_nm;
	
	var subjectNameListVal = subjectNameList.value;
	
	for( var i=0; i<subjectNameList.options.length; i++ ) {
		if( subjectNameList.options[i].value == subjectNameListVal ) {
			subjectNameList.options[i].checked = true;
		txt.value= subjectNameListVal;
		}
	}
	
	

	}
	
	function choice() {
		var frm = document.frm;
		var subjectNameList = frm.subjectNameList;
		var txt = frm.combo_nm;
		var subjectNameListVal = subjectNameList.value;
		
		frm2.txt.value = "";
		var cStamp = stamp[subjectNameListVal].split("");
		
		for(var i = 0; i < cStamp.length; i++) {
			if(cStamp[i] == "0") {
				cStamp[i] = "미확인";
			}
			else if(cStamp[i] == "1") {
				cStamp[i] = "출석확인";
			}
			else if(cStamp[i] == "2") {
				cStamp[i] = "결석";
			}
			
			frm2.txt.value = frm2.txt.value + "\n" + (i+1) + " : " + cStamp[i] + "\n";
		}
		
	}
	</script>

	
	
	
	
	<br>
	
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;출석 체크 가능한 과목&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;출석 체크하기&nbsp;&nbsp;</td>
	</tr>
	<% 
	for(int i = 0; i < userCheckynCount; i++) {
	%>
	<form action="/attend/AttendCheck" method="post">
	<tr>
	<td> <%=userCheckyn[i] %></td>
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=userCheckyn[i] %>"> 
	<Input Type="hidden" name="stamp" value="<%=userStamp[i] %>"> 
	<Input Type="hidden" name="suserCday" value="<%=suserCday[i] %>">
	<input type="submit" value="출석 체크"> 
	</td>
	</tr>
	</form>
	<%
	}
	%>
	</table>
	
	
	<br>
	
	
	
	
	
	


	<form name="frm">
	출석 조회 : 
	<SELECT name="subjectNameList" onChange="change();">
	<OPTION value="-1" name="none">과목을 선택하세요.</OPTION>
	<%
		for(int i = 0; i < subjectCount; i++) { 
		%>
			<OPTION value="<%=i %>"><%=subjectName[i] %></OPTION>
			<% 
		}
	%>
	<%-- 
	<OPTION value="1" name="일">일</OPTION>
	<OPTION value="2" name="이">이</OPTION>
	<OPTION value="3" name="삼">삼</OPTION>
	
	--%>
	</SELECT>

	<INPUT type="text" name="combo_nm">
	<input type="button" value="조회" onclick="choice()">
	</form>
	
	<br>
	
	<form name = "frm2">
	<textarea name = "txt" rows="40" cols="40">hello, world!</textarea>
	</form>
	
	</div>
	
</body>
</html>
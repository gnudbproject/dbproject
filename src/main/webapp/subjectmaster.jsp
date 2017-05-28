<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 


	int allSubjectCount=((Integer)request.getAttribute("allSubjectCount")).intValue();
	String[] allSubjectName = new String[allSubjectCount];
	allSubjectName = (String[])request.getAttribute("allSubjectName");
	String[] checkyn = new String[allSubjectCount];
	checkyn = (String[])request.getAttribute("checkyn");
	String[] requestyn = new String[allSubjectCount];
	requestyn = (String[])request.getAttribute("requestyn");
	
	//String[] stamp = new String[subjectCount];
	//stamp = (String[])request.getAttribute("stamp");
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
	
	var stamp = new Array(<%=allSubjectCount%>);
	
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

	<form id="create-subject" action="/subject/AddSubject" method="post">
	과목명을 입력하세요 : <INPUT type="text" name="subjectName"> <br>
	출석일 수를 입력하세요 : <INPUT type="number" name = "day" maxlength="2"> <br>
	<input type="submit" value="과목 생성">
	</form>
	
	<br><br>

	<form name="frm" action="/subject/removeSubject" method="post">
	과목 삭제 : 
	<SELECT name="subjectNameList" onChange="change();">
	<OPTION value="-1" name="none">삭제할 과목을 선택하세요.</OPTION>
	<%
		for(int i = 0; i < allSubjectCount; i++) { 
		%>
			<OPTION value="<%=allSubjectName[i] %>"><%=allSubjectName[i] %></OPTION>
			<% 
		}
	%>
	<%-- 
	<OPTION value="1" name="일">일</OPTION>
	<OPTION value="2" name="이">이</OPTION>
	<OPTION value="3" name="삼">삼</OPTION>
	
	--%>
	</SELECT>

	<INPUT type="text" name="combo_nm" value="<%=checkyn[0]%>">
	<input type="submit" value="삭제">
	</form>
	
	<br>
	
	
	
	과목 신청 허용<br>
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;신청 허용중이지 않은 과목&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;허용 여부 바꾸기&nbsp;&nbsp;</td>
	</tr>
	
	<% 
	for(int i = 0; i < allSubjectCount; i++) {
		if(requestyn[i].equals("n")) {
	%>
	<tr>
	<td> <%=allSubjectName[i] %></td>
	<form action="/subject/ChangeRequestyn" method="post">
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="requestyn" value="<%=requestyn[i] %>"> 
	<input type="submit" value="변경"> 
	</td>
	</form>
	</tr>
	
	<%
		}
	}
	%>
	</table>
	
	<br>
	
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;신청 허용중인 과목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;허용 여부 바꾸기&nbsp;&nbsp;</td>
	</tr>
	
	<% 
	for(int i = 0; i < allSubjectCount; i++) {
		if(requestyn[i].equals("y")) {
	%>
	<tr>
	<td> <%=allSubjectName[i] %></td>
	<form action="/subject/ChangeRequestyn" method="post">
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="requestyn" value="<%=requestyn[i] %>"> 
	<input type="submit" value="변경"> 
	</td>
	</form>
	</tr>
	
	<%
		}
	}
	%>
	</table>
	
	
	
	
	
	
	
	
	
	
	</div>
	

</body>
</html>
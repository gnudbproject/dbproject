<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 


	int allSubjectCount=((Integer)request.getAttribute("allSubjectCount")).intValue();
	String[] allSubjectName = new String[allSubjectCount];
	allSubjectName = (String[])request.getAttribute("allSubjectName");
	String[] checkyn = new String[allSubjectCount];
	checkyn = (String[])request.getAttribute("checkyn");
	
	String[] scday = new String[allSubjectCount];
	scday = (String[])request.getAttribute("scday");
	int[] cday = new int[allSubjectCount];
	
	for(int i = 0; i < allSubjectCount; i++) {
		cday[i] = Integer.parseInt(scday[i]);
	}
	
	String[] maxDay = new String[allSubjectCount];
	maxDay = (String[])request.getAttribute("maxDay");
	
	int allAttendCount=((Integer)request.getAttribute("allAttendCount")).intValue();
	String[] allAttenduserId = new String[allAttendCount];
	allAttenduserId = (String[])request.getAttribute("allAttenduserId");
	String[] allAttendSubjectName = new String[allAttendCount];
	allAttendSubjectName = (String[])request.getAttribute("allAttendSubjectName");
	String[] allAttendStamp = new String[allAttendCount];
	allAttendStamp = (String[])request.getAttribute("allAttendStamp");
	
	//String[] stamp = new String[subjectCount];
	//stamp = (String[])request.getAttribute("stamp");
%>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Main.jsp</title>
</head>
<body>
  
	<%@ include file="./commons/top.jspf"%>
	<div id="cover_container">
		<%@ include file="./commons/left_sidemenu.jspf"%>


		<div class="wrap ac">
			<div id="container_wrap">
	<div id=main_content>

	<script type="text/javascript">
	
	var stamp = new Array(<%=allSubjectCount%>);
	
	<%for(int i = 0; i < allSubjectCount; i++) {%>
	stamp[<%=i %>] = "aaaa";
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
	
	출석체크 허용하기<br>
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;출석 체크중이지 않은 과목&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;허용 여부 바꾸기&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;출석 부른 횟수&nbsp;&nbsp;</td>
	</tr>
	
	<% 
	for(int i = 0; i < allSubjectCount; i++) {
		if(checkyn[i].equals("n")) {
	%>
	<tr>
	<td> <%=allSubjectName[i] %></td>
	<form action="/attend/ChangeCheckyn" method="post">
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="checkyn" value="<%=checkyn[i] %>"> 
	<Input Type="hidden" name="cday" value="<%=cday[i] + 1 %>"> 
	<Input Type="hidden" name="maxDay" value="<%=maxDay[i] %>">
	<input type="submit" value="변경"> 
	</td>
	</form>
	<td><%=cday[i] %></td>
	</tr>
	
	<%
		}
	}
	%>
	</table>
	
	<br>
	
	<table border="1">
	<tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;출석 체크중인 과목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;허용 여부 바꾸기&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;현재 출석 순서&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;취소하기&nbsp;&nbsp;</td>
	</tr>
	<% 
	for(int i = 0; i < allSubjectCount; i++) {
		if(checkyn[i].equals("y")) {
	%>
	<tr>
	<td> <%=allSubjectName[i] %></td>
	<form action="/attend/ChangeCheckyn" method="post">
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="checkyn" value="<%=checkyn[i] %>"> 
	<Input Type="hidden" name="cday" value="<%=cday[i] %>"> 
	<Input Type="hidden" name="maxDay" value="<%=maxDay[i] %>">
	<input type="submit" value="변경"> 
	</td>
	</form>
	<td><%=cday[i] %></td>
	<form action="/attend/ChangeCheckyn" method="post">
	<td> 
	<Input Type="hidden" name="subjectName" value="<%=allSubjectName[i] %>"> 
	<Input Type="hidden" name="checkyn" value="<%=checkyn[i] %>"> 
	<Input Type="hidden" name="cday" value="<%=cday[i] - 1 %>"> 
	<Input Type="hidden" name="maxDay" value="<%=maxDay[i] %>">
	<input type="submit" value="취소"> 
	</td>
	</form>
	</tr>
	
	<%
		}
	}
	%>
	</table>
	
	<br><br>
	
	
	
		
	</div>
	
			</div>
		</div>
	</div>
	

</body>
</html>
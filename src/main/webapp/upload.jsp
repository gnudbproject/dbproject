<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>
	
	<h1>颇老梅何</h1>
	<form action="/Upload?homeworkNum=${param.homeworkNum }" method="post" enctype="multipart/form-data">
		
		梅何颇老:<input type="file" name="s_file"/><br/>
		<input type="submit" value="苞力 力免"/>

	</form>

</body>

</html>

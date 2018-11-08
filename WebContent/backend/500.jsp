<%@ page language="java" contentType="text/html; charset=utf-8" isErrorPage="true"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta  charset="utf-8"/>
		<title>添加失败</title>
	</head>
	<body>
		<h1 style="color:blue;margin:10px 15px">
			服务器内部错误
			<%=exception.getMessage()%>
		</h1>
	</body>
</html>
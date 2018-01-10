<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="jspf/head_config.jspf"%>
<title>teamon.pl</title>
<link href="/manager/resources/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div align="center">
		<div id="container">
			<%@ include file="jspf/header.jspf"%>
			<%@ include file="jspf/main_menu.jspf"%>
			<div class="form-style-8">
				<h2>Logowanie</h2>
				<form:form method="post" modelAttribute="loginData">
					<p>
						<!--Email:-->
						<form:input path="email" type="email" placeholder="E-mail" />
						<form:errors path="email" />
					</p>
					<p>
						<!--Password:-->
						<form:password path="password" placeholder="Hasło" />
						<form:errors path="password" />
					</p>
					<p class="error">${msg}</p>
					<input type="submit" value="Zatwierdź" />
				</form:form>
			</div>
			<%@ include file="jspf/footer.jspf"%>
		</div>
	</div>
</body>
</html>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/manager/resources/css/style.css" rel="stylesheet" type="text/css"/>
<title>teamon.pl</title>
</head>
<body>
	<div align="center">
		<div id="container">
			<%@ include file="../jspf/header.jspf"%>
			<%@ include file="../jspf/main_menu.jspf"%>
			<div class="form-style-8">
				<h2>Zarządzaj grupą ${userGroup.groupName}</h2>
				<form:form method="post" modelAttribute="userGroup">
					<p>
						<!--User Name:-->
						<form:input path="groupName" type="text" placeholder="Nazwa grupy" />
						<form:errors path="groupName" />
						<!--Users input:-->
						Skład:
						<form:select type="text" path="user" items="${allUsers}" multiple="true" itemValue="id" itemLabel="fullName" style='height: 200px;'></form:select>
						<form:errors path="user" />
						
					</p>
					<p class="error">${msg}</p>
					<input type="submit" value="Zatwierdź" />
				</form:form>
			</div>
			<%@ include file="../jspf/footer.jspf"%>
		</div>
	</div>
</body>
</html>
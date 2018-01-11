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
				<h2>Dodaj nowe wydarzenie</h2>
				<form:form method="post" modelAttribute="trening">
					<p>
						<form:input path="date" type="datetime" value="${date}" />
						<form:errors path="date" />
					</p>
					<p>
						Dyscyplina:
						<form:select type="text" path="topic" items="${sportTypes}" placeholder="Dyscyplina"></form:select>
						<form:errors path="topic" />
					</p>
					<p>
						Zespół:
						<form:select type="text" path="usergroup" items="${allGroups}" placeholder="Zespół" itemValue="id" itemLabel="groupName"></form:select>
						<form:errors path="usergroup" />
					</p>
					<p>
						Skład:
						<form:select type="text" path="user" items="${allUsers}" placeholder="Zawodnicy" itemValue="id" itemLabel="fullName" style="height: 200px;"></form:select>
						<form:errors path="user" />
					</p>
					<p>
						<form:textarea path="description" type="text" placeholder="Tematyka zajęć" />
						<form:errors path="description" />
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
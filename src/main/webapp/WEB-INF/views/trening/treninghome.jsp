<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>teamon.pl</title>
<link href="/manager/resources/css/style.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div align="center">
		<div id="container">
			<%@ include file="../jspf/header.jspf"%>
			<%@ include file="../jspf/main_menu.jspf"%>

			<c:choose>
				<c:when test="${sessionScope.user == null}">
					Musisz być zalogowany aby mieć dostęp do tej treści 
				</c:when>
				<c:otherwise>
					<div class="form-style-8">
						<h2>Najbliższe zaplanowane spotkania:</h2>
						<a class="box" href="trening/add" >Dodaj</a>
						<c:forEach items="${allTrenings}" var="trening">
							<div class="left">${trening.name}</div>
							<div class="left">
								<a href="./group/${trening.id}/detail">szczegóły</a> 
								| <a href="./group/${trening.id}/manage">zarządzaj</a>
								| <a href="./group/${trening.id}/delete">usuń</a>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
			<%@ include file="../jspf/footer.jspf"%>
		</div>
	</div>
</body>
</html>
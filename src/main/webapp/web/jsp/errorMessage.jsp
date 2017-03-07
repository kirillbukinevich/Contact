<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath}/web/js/error.js"></script>
<link href="${pageContext.request.contextPath}/web/css/error.css"
	rel="stylesheet" type="text/css">

<form id="errors">
    <c:forEach var="error" items="${errors}">
        <input type="hidden" value="${error}">
    </c:forEach>
</form>
<form id="infoMessages">
    <c:forEach var="messag" items="${infoMessages}">
        <input type="hidden" value="${messag}">
    </c:forEach>
</form>

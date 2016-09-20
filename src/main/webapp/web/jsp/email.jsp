<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/email.css"
      rel="stylesheet">
<html>
<head>
    <title>Email</title>

</head>
<body>
<div class="container">
    <h3 style="color: #f9ff3a">Contact Form</h3>
    <form name="loginForm" method="POST" action="controller">
        <fieldset>
            <input type="text" class="form-control" name="list_mail" value="${lst_mail}">
        </fieldset>
        <fieldset>
            <input type="text" class="form-control" name="theme" placeholder="Enter the theme">
        </fieldset>
        <fieldset>
            <label>
                <input type="text" class="form-control" name="templates">
            </label>
        </fieldset>
        <fieldset>
            <textarea class="form-control" name="message" placeholder="Enter the message" required></textarea>
        </fieldset>
        <fieldset>
            <button class="btn btn-success" name="command" value="sendemail">Send</button>
        </fieldset>
    </FORM>
</div>


</body>
</html>
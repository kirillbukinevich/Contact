<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/email.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/btntrans.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popBackgroundImage.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/navbar.css"
      rel="stylesheet">
<script src = "${pageContext.request.contextPath}/web/js/email.js"></script>
<html>
<head>
    <title>Email</title>

</head>
<body class="email-img">
<nav class="navbar navbar-default navbar-fixed-top">
    <a href="controller?command=contact" class="btn btn-trans btn-trans-success">На главную</a>
</nav>
<div class="container">
    <%--<c:set var="template" value="${template}" scope="page"/>--%>
    <div class="panel">
        <div class="panel-heading">

            <h3>Отправить email ${template}</h3>
            <%--<h3>Отправить email ${subject}</h3>--%>
        </div>
        <form id="emailForm" method="POST" action="controller">
            <input type="hidden" id="command" name="command" value="">
            <div class="panel-body">
                <div class="form">
                <input type="text" class="form-control" name="list_mail" value="${lst_mail}">
                <input type="text" class="form-control" name="theme" placeholder="Enter the theme"
                       value="${subject}">
                <label>Выберите шаблон
                    <br>
                    <select class="select-style" name="template_type" id="template_type"
                            onchange="setCommand('apply_template_email_command'); this.form.submit()">
                        <OPTION selected>
                        <OPTION value="man_birthday" ${template_type == 'man_birthday' ? 'selected' : ''}>С Днём рождения
                            мужчинам
                        <OPTION value="woman_birthday" ${template_type == 'woman_birthday' ? 'selected' : ''}>С Днём рождения
                            женщинам
                            <%--<OPTION ${template_type == 'email' ? 'selected' : ''}> email--%>
                    </SELECT><br><br>
                </label>
            <textarea name="message" placeholder="Enter the message"
                      style="display: ${editable_area == 'hide' ? 'none' : 'block'}">${text}</textarea>
                </div>
                ${includePage}
            </div>
            <div class="panel-footer">
                <button class="btn btn-trans btn-trans-success" onclick="setCommand('sendemail')">Отправить</button>
            </div>
        </FORM>
    </div>
</div>
</body>
</html>

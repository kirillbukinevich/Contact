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
<img class="img-responsive" src="C:\Contact\src\main\webapp\web\image\birthday\birthday_men.jpg">
<c:set var="template" value="${template}" scope="page"/>
<div class="container">
    <h3 style="color: #f9ff3a">Contact Form</h3>

    <form id="emailForm" method="POST" action="controller">
        <fieldset>
            <input type="text" class="form-control" name="list_mail" value="${lst_mail}">
        </fieldset>
        <fieldset>
            <input type="text" class="form-control" name="theme" placeholder="Enter the theme"
                   value="${template.subject}">
        </fieldset>
        <fieldset>
            <label>Choose template
                <select class="select-style" name="template_type" id="template_type">
                    <OPTION selected>
                    <OPTION ${template_type == 'birthday_man' ? 'selected' : ''}> birthday_man
                    <OPTION ${template_type == 'birthday_woman' ? 'selected' : ''}> birthday_woman
                    <%--<OPTION ${template_type == 'email' ? 'selected' : ''}> email--%>
                </SELECT><br><br>
            </label>
            <button class="btn btn-success" name="command" value="ApplyTemplateEmailCommand">apply</button>
        </fieldset>
        <fieldset>
            <textarea class="form-control" name="message" placeholder="Enter the message"
                      style="display: ${editable_area == 'hide' ? 'none' : 'block'}">${template.text}</textarea>
        </fieldset>
        <fieldset>
            ${template.includePage}
        </fieldset>
        <fieldset>
            <button class="btn btn-success" name="command" value="sendemail">Send</button>
        </fieldset>
    </FORM>
</div>


</body>
</html>
<script type="text/javascript">


</script>
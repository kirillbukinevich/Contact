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
<c:set var="template" value="${birthday_template}" scope="page"/>
<div class="container">
    <h3 style="color: #f9ff3a">Contact Form</h3>

    <form id="emailForm" method="POST" action="controller">
        <fieldset>
            <input type="text" class="form-control" name="list_mail" value="${lst_mail}">
        </fieldset>
        <fieldset>
            <input type="text" class="form-control" name="theme" placeholder="Enter the theme" value="${template.subject}" >
        </fieldset>
        <fieldset>
            <label>Choose template
                <select class="select-style" name="template" id="template" >
                    <OPTION selected>
                    <OPTION> birthday
                    <OPTION> email
                </SELECT><br><br>
            </label>
            <button class="btn btn-success" name="command" value="ApplyTemplateEmailCommand">apply</button>
        </fieldset>
        <fieldset>
            <textarea class="form-control" name="message" placeholder="Enter the message" >${template.body}</textarea>
        </fieldset>
        <fieldset>
            <button class="btn btn-success" name="command" value="sendemail">Send</button>
        </fieldset>
    </FORM>
    ${template.includePage}
</div>


</body>
</html>
<script type="text/javascript">


</script>
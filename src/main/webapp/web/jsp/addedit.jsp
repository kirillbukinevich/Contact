<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popwin.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/addedit.css"
      rel="stylesheet">

<html>
<head>
    <title>Employees</title>

</head>
<body>
<div class="container-fluid">
    <div class="col-xs-1">
        <jsp:include page="edit/popPhoto.jsp"/>
    </div>
    <div class="col-xs-5">

        <form name="edit_form" method="POST" action="controller" id="edit_form">
            <div class="form">

                <jsp:include page="edit/main_table.jsp"/>
                <jsp:include page="edit/popSaveSuccess.html"/>

            </div>
        </FORM>
    </div>
    <div class="col-xs-6">
        <div class="row">
            <%--show contact phone--%>
            <div class="col-xs-11">


                <jsp:include page="edit/phones.jsp"/>
            </div>

            <div class="col-xs-1">
                <div class="btn-group-vertical">

                    <%--handling edit remove and new operations--%>
                    <button form="edit_form" class="btn btn-info" id="myBtn" name="command" value="update_phone">New</button>
                    <jsp:include page="edit/popPhone.html"/>
                    <button form="phones" class="btn btn-info" name="command" value="editPhone">Edit</button>
                    <button form="phones" class="btn btn-info" name="command" value="deletePhone">Delete</button>

                </div>

            </div>
        </div>
        <%--show attach files--%>
        <div class="row">
            <div class="col-xs-11">
                <jsp:include page="edit/attachments.jsp"/>
            </div>
            <div class="col-xs-1">

                <div class="btn-group-vertical">
                    <button form="edit_form" class="btn btn-info" id="myBtn2" name="command" value="update_attachment">New</button>
                    <jsp:include page="edit/popattachfile.html"/>
                    <button form="attachments" class="btn btn-info" name="command" value="editAttachFile">Edit</button>
                    <button form="attachments" class="btn btn-info" name="command" value="deleteAttachFile">Delete
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    var popDialog = "${pageContext.request.getAttribute("popDialog")}"
    if (popDialog === "myModal") {
        modal.style.display = "block";
    }
    if (popDialog === "attachModal") {
        attachModal.style.display = "block";
    }
</script>
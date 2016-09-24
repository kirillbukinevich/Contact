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
<form name="edit_form" method="POST" action="controller" id="edit_form">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-1">
                <button class="btn btn-default" id="myBtn4" name="command" value="update_photo">
                    <img class="img-responsive" src="data:image/png;base64, <c:out value='${photo}'/>"/>
                </button>
            </div>
            <div class="col-md-5">


                <div class="form">

                    <jsp:include page="edit/main_table.jsp"/>
                    <jsp:include page="edit/popSaveSuccess.html"/>

                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <%--show contact phone--%>
                    <div class="col-sm-11">


                        <jsp:include page="edit/phones.jsp"/>
                    </div>

                    <div class="col-sm-1">
                        <div class="btn-group-vertical">

                            <%--handling edit remove and new operations--%>
                            <button form="edit_form" class="btn btn-info" id="myBtn" name="command"
                                    value="update_phone"><span class="glyphicon glyphicon-plus"></span>
                                New
                            </button>
                            <button form="edit_form" class="btn btn-info" id="editPhone" name="command"
                                    value="update_edit_phone" disabled="true">
                                <span class="glyphicon glyphicon-cog"></span>
                                Edit
                            </button>
                            <button form="edit_form" class="btn btn-info" name="command" value="deletePhone">
                                <span class="glyphicon glyphicon-remove"></span>
                                Delete
                            </button>

                        </div>

                    </div>
                </div>

                <%--show attach files--%>
                <div class="row">
                    <div class="col-sm-11">
                        <jsp:include page="edit/attachments.jsp"/>
                    </div>
                    <div class="col-sm-1">

                        <div class="btn-group-vertical">
                            <button form="edit_form" class="btn btn-info" id="myBtn2" name="command"
                                    value="update_attachment"><span class="glyphicon glyphicon-plus"></span>
                                New
                            </button>
                            <button form="edit_form" class="btn btn-info" name="command" id="editAttachFile"
                                    value="update_edit_attachment" disabled='true'>
                                <span class="glyphicon glyphicon-cog"></span>
                                Edit
                            </button>
                            <button form="edit_form" class="btn btn-info" name="command"
                                    value="deleteAttachFile">
                                <span class="glyphicon glyphicon-remove"></span>
                                Delete
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
<jsp:include page="edit/popattachfile.jsp"/>
<jsp:include page="edit/popPhone.jsp"/>
<jsp:include page="edit/popPhoto.jsp"/>

<script>
    var popDialog = "${pageContext.request.getAttribute("popDialog")}";
    if (popDialog === "myModal") {
        modal.style.display = "block";
    }
    if (popDialog === "attachModal") {
        attachModal.style.display = "block";
    }
    if (popDialog === "myModal4") {
        modal4.style.display = "block";
    }
    function checkboxes(buttonId) {
        var inputElems = document.getElementsByTagName("input"),
                count = 0;
        for (var i = 0; i < inputElems.length; i++) {
            if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
                count++;
            }
        }
        var editbuttonPhone = document.getElementById(buttonId);
        if (count > 1 || count == 0) {
            editbuttonPhone.disabled = true;

        } else {
            editbuttonPhone.disabled = false;
        }
    }
</script>
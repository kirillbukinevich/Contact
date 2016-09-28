<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popwin.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/addedit.css"
      rel="stylesheet">
<script src="${pageContext.request.contextPath}/web/scripts/validedit.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/editScript.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/popDialog.js"></script>
<html>
<head>
    <title>Редактируем</title>

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
                    <jsp:include page="edit/popSaveSuccess.jsp"/>

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
                                Добавить
                            </button>
                            <button form="edit_form" class="btn btn-info" id="editPhone" name="command"
                                    value="update_edit_phone" disabled="true">
                                <span class="glyphicon glyphicon-cog"></span>
                                Редактировать
                            </button>
                            <button form="edit_form" class="btn btn-info" id="deletePhone" name="command"
                                    value="deletePhone" disabled="true">
                                <span class="glyphicon glyphicon-remove"></span>
                                Удалить
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
                                Добавить
                            </button>
                            <button form="edit_form" class="btn btn-info" name="command" id="editAttachFile"
                                    value="update_edit_attachment" disabled='true'>
                                <span class="glyphicon glyphicon-cog"></span>
                                Редактировать
                            </button>
                            <button form="edit_form" class="btn btn-info" id="deleteAttachFile" name="command"
                                    value="deleteAttachFile" disabled="true">
                                <span class="glyphicon glyphicon-remove"></span>
                                Удалить
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

<script >
    var popDialogID = "${pageContext.request.getAttribute("popDialog")}";
    popDialog(popDialogID);
</script>
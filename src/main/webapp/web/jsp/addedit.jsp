<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popwin.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/addedit.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/btntrans.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/navbar.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popBackgroundImage.css"
      rel="stylesheet">

<script src="${pageContext.request.contextPath}/web/js/editScript.js"></script>
<script src="${pageContext.request.contextPath}/web/js/popDialog.js"></script>
<script src="${pageContext.request.contextPath}/web/js/valid.js"></script>
<script src="${pageContext.request.contextPath}/web/js/entity/contactPhone.js"></script>
<script src="${pageContext.request.contextPath}/web/js/deletePhone.js"></script>
<jsp:include page="edit/popPhone.jsp"/>
<jsp:include page="edit/popattachfile.jsp"/>
<jsp:include page="edit/popPhoto.jsp"/>

<html>
<head>
    <title>Редактируем</title>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <a href="controller?command=contact" class="btn btn-trans btn-trans-success">На главную</a>
</nav>
<form name="edit_form" onsubmit="return submitForm();" method="POST" action="controller" id="edit_form">
    <div class="container-fluid">
        <div class="col-md-6 col-sm-12 col-xs-12" >
            <div class="form">
                <button class="btn btn-default" id="photo_button" name="command" value="update_photo">
                    <img src="data:image/png;base64, <c:out value='${photo}'/>"
                         class="img-responsive"  />
                </button>
                <jsp:include page="edit/main_form.jsp"/>
                <button class="btn btn-trans btn-trans-success" id="btnSave" name="command" value="save">
                    <span class="glyphicon glyphicon-floppy-saved"></span>
                    Сохранить
                </button>
                <jsp:include page="edit/popSaveSuccess.jsp"/>
                <button class="btn btn-trans btn-trans-success" id="cancel_edit" name="command"
                        value="cancel_edit">Cancel
                </button>
            </div>
        </div>
        <div class="col-md-6 col-sm-12 col-xs-12">
            <div class="row">
                <%--show contact phone--%>
                <div class="col-sm-11">
                    <jsp:include page="edit/phones.jsp"/>
                </div>
                <div class="col-sm-1">
                    <div class="btn-group-vertical">
                        <%--handling edit remove and new operations--%>
                        <button class="btn btn-trans btn-trans-success" id="addPhone" name="command"
                                value="update_phone"  onclick="popDialog('phoneModal','Add_Phone')"><span class="glyphicon glyphicon-plus"></span>
                        </button>
                        <button class="btn btn-trans btn-trans-success" id="editPhone" name="command"
                                value="update_edit_phone" disabled onclick="popDialog('phoneModal','Edit_Phone')">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>
                        <button class="btn btn-trans btn-trans-success" id="deletePhone" name="command"
                                value="deletePhone" disabled onclick = "deletePhones()">
                            <span class="glyphicon glyphicon-remove"></span>
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
                        <button class="btn btn-trans btn-trans-success" id="myBtn2" name="command"
                                value="update_attachment"><span class="glyphicon glyphicon-plus"></span>

                        </button>
                        <button class="btn btn-trans btn-trans-success" name="command" id="editAttachFile"
                                value="update_edit_attachment" disabled='true'>
                            <span class="glyphicon glyphicon-cog"></span>

                        </button>
                        <button class="btn btn-trans btn-trans-success" id="deleteAttachFile" name="command"
                                value="deleteAttachFile" disabled="true">
                            <span class="glyphicon glyphicon-remove"></span>

                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form>
</body>
</html>

<script>
    popDialog(${pageContext.request.getAttribute("popDialog")});
</script>
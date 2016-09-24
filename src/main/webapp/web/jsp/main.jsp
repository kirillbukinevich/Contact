<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popwin.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/style.css"
      rel="stylesheet">

<html>
<head>
    <title>Employees</title>
</head>
<body class="back-color">
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="row">
            <div class="panel-heading">
                <div class="col-xs-4">
                    <div class="btn-group">
                        <button form="contact_form" class="btn btn-trans btn-trans-success " name="command" value="new">
                            New
                        </button>

                        <button form="contact_form" class="btn btn-trans btn-trans-success" name="command" value="edit"
                                id="editbutton" disabled=true>Edit
                        </button>

                        <button form="contact_form" class="btn btn-trans btn-trans-success" name="command"
                                value="delete">
                            Delete
                        </button>

                        <%--<button class="btn btn-info " name="command" value="find">Find</button>--%>
                        <jsp:include page="search.jsp"/>

                        <button form="contact_form" class="btn btn-trans btn-trans-success" name="command" value="email"
                                id="sendmailbutton" disabled = "true">Send email
                        </button>
                    </div>
                </div>
                <div class="col-xs-5"></div>
                <div class="col-xs-3">
                    <h2 style="color: #fffdfb; font-family: Monaco,serif; position: absolute;top: 0px;left: 0px;">
                        Happy Employees And Corporate Culture Quotes</h2>
                </div>

            </div>
        </div>

        <div class="panel-body">

            <%--employee table--%>
            <form method="POST" action="controller" id="contact_form">
                <div class="row">
                    <div class="tbl-header">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <thead>
                            <tr>
                                <th width="5%"></th>
                                <th>Name</th>
                                <th>Date of birth</th>
                                <th>Address</th>
                                <th>Work company</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="tbl-content">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tbody>
                            <c:forEach var="employee" items="${employeeList}" varStatus="status">
                            <tr>
                                <td width="5%"><input type="checkbox" name="check_selected" value="${employee.id}"
                                           onchange="checkboxes(),checkboxesForMail()">
                                </td>
                                <td>
                                    <a href="<c:url value="controller?command=edit">
                            <c:param name="employee_id" value="${employee.id}"/>
                        </c:url>"
                                       class="btn btn-link">${employee.firstName} ${employee.lastName} ${employee.patronymic}</a>
                                </td>
                                <td>${employee.dateOfBirth}</td>
                                <td>${employee.address}</td>
                                <td>${employee.workPlace}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <%----%>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <div class="row">
                    <div class="pager">
                        <table border="1" cellpadding="5" cellspacing="5">
                            <tr>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <td>
                                                <button class="btn btn-danger">${i}</button>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="controller?command=contact&page=${i}"
                                                   class="btn btn-trans btn-trans-success">${i}</a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <td><a href="controller?command=contact&page=${currentPage - 1}"
                               class="btn btn-trans btn-trans-success">Previous</a>
                        </td>
                    </c:if>
                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <td><a href="controller?command=contact&page=${currentPage + 1}" class="btn btn-trans btn-trans-success">Next</a>
                        </td>
                    </c:if>
                </div>
            </form>


        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    function checkboxes() {
        var inputElems = document.getElementsByTagName("input"),
                count = 0;
        for (var i = 0; i < inputElems.length; i++) {
            if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
                count++;
            }
        }
        var editbutton = document.getElementById("editbutton");
        if (count > 1 || count == 0) {
            editbutton.disabled = true;

        } else {
            editbutton.disabled = false;
        }
    }
    function checkboxesForMail() {
        var inputElems = document.getElementsByTagName("input"),
                count = 0;
        for (var i = 0; i < inputElems.length; i++) {
            if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
                count++;
            }
        }
        var sendmailbutton = document.getElementById("sendmailbutton");
        if (count == 0) {
            sendmailbutton.disabled = true;

        } else {
            sendmailbutton.disabled = false;
        }
    }

</script>

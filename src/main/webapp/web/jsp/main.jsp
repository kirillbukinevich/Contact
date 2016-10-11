<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/web/css/bootstrap.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popwin.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/style.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/navbar.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/btntrans.css"
      rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/popBackgroundImage.css"
      rel="stylesheet">
<script src="${pageContext.request.contextPath}/web/scripts/mainScript.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/popDialog.js"></script>

<html>
<head>
    <title>Сотрудники</title>
</head>
<body class="back-img">
<div class="container-fluid">
    <nav class="navbar navbar-default navbar-fixed-top">
                <div class="btn-group">
                    <li><button form="contact_form" class="btn btn-trans btn-trans-success " name="command"
                            value="new">
                        Новый
                    </button></li>

                    <li><button form="contact_form" class="btn btn-trans btn-trans-success" name="command"
                            value="edit"
                            id="editbutton" disabled=true>Редактировать
                    </button></li>

                    <li><button form="contact_form" class="btn btn-trans btn-trans-success" id="deletebutton"
                            name="command"
                            value="delete" disabled="true"><span class="glyphicon glyphicon-remove"></span>
                        Удалить
                    </button></li>

                    <%--<button class="btn btn-info " name="command" value="find">Find</button>--%>
                    <jsp:include page="search.jsp"/>

                    <li><button form="contact_form" class="btn btn-trans btn-trans-success" name="command"
                            value="email"
                            id="sendmailbutton" disabled="true"><span
                            class="glyphicon glyphicon-envelope"></span>
                        Отправить email
                    </button></li>
                </div>
        </nav>
    <div class="panel">
        <div class="panel-heading">
            <div id="search_bar" style="display: ${search_bar == "show" ? 'block':'none'}">
                <div class="row">
                        <p style="color: #f3ffff">${search_info}</p>
                        <button form="contact_form" class="btn btn-trans btn-trans-success" id="close_searchModal"
                                name="command" value="contact">Сбросить
                        </button>
                </div>
            </div>
        </div>
    <div class="panel-body">
            <form method="POST" action="controller" id="contact_form">

                <%--employee table--%>

                <div class="row table-responsive" style="height: 77%">
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="5%"></th>
                            <th>Имя</th>
                            <th>Дата рождения</th>
                            <th>Адрес</th>
                            <th>Компания</th>
                        </tr>
                        </thead>
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
    <div class="panel-footer">
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
                                                <button class="btn btn-danger" disabled="true">${i}</button>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${(currentPage-5<=i and currentPage>=i)  or
                                                           currentPage+5>=i and currentPage<=i}">
                                                <td><a href="controller?command=contact&page=${i}"
                                                       class="btn btn-trans btn-trans-info">${i}</a>
                                                </td>
                                            </c:if>
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
                               class="btn btn-trans btn-trans-success">Предыдущая</a>
                        </td>
                    </c:if>
                    <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <td><a href="controller?command=contact&page=${currentPage + 1}"
                               class="btn btn-trans btn-trans-success">Следующая</a>
                        </td>
                    </c:if>
                </div>
            </form>
        </div>

            <h2 style="color: #fffdfb; font-size: 1.8em; font-family: Monaco,serif; text-align: center;">
                Только счастливые сотрудники!</h2>
    </div>
</div>

</body>
</html>

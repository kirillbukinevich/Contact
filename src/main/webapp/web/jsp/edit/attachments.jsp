<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<table class="table table-responsive" id = 'attachment_table'>
    <thead>
    <tr>
        <th></th>
        <th>имя файла</th>
        <th>дата загрузки</th>
        <th>комментарий</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="file" items="${attachList}" varStatus="status">
        <c:if test="${file.deleted == false}">
            <tr>
                <td><input type="checkbox" name="check_selected_file" class="check_selected_file" value="${file.id}"
                           onchange="checkboxesEditDelete('check_selected_file','editAttachFile','deleteAttachFile')"></td>
                <td>
                    <a href="<c:url value="download">
                            <c:param name="file_path" value="${file.employeeID}/${file.id}"/>
                            <c:param name="file_name" value="${file.fileName}"/>
                        </c:url>"
                       class="btn btn-link"><p>${file.fileName}</p></a>
                </td>
                <td><p>${file.loadDate}</p></td>
                <td><p>${file.comment}</p></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>


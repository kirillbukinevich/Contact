<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered">

    <thead>
    <tr>
        <th></th>
        <th>номер</th>
        <th>домашний/мобильный</th>
        <th>комментарий</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="phone" items="${phoneList}" varStatus="status">
        <tr>
            <td><input type="checkbox" name="check_selected_phone" value="${phone.id}"
                       onchange="checkboxes('editPhone','deletePhone')"></td>
            <td>
                <button name = "command" value = "update_edit_phone" class="btn-link">
                    <input type="hidden" name="phone_id" value="${phone.id}">
                        ${phone.codeCountry}${phone.codeOperator}${phone.number}
                </button>
            </td>
            <td>${phone.type}</td>
            <td>${phone.comment}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

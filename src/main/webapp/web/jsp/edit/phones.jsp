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
                        <a href=" <c:url value="controller?command=update_edit_phone">
                            <c:param name="phone_id" value="${phone.id}"/>
                        </c:url>"
                           class="btn btn-link">${phone.codeCountry}${phone.codeOperator}${phone.number}</a>
                    </td>
                    <td>${phone.type}</td>
                    <td>${phone.comment}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>


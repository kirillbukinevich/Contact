<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-responsive">

    <thead>
    <tr>
        <th></th>
        <th>номер</th>
        <th>домашний/мобильный</th>
        <th>комментарий</th>
    </tr>
    </thead>
    <tbody>
    <input type="hidden" id="phone_id" name="phone_id" value="">
    <c:forEach var="phone" items="${phoneList}" varStatus="status">
        <c:if test="${!phone.deleted}">
            <tr>
                <td><input type="checkbox" name="check_selected_phone" value="${phone.id}"
                           onchange="checkboxes('editPhone','deletePhone')"></td>
                <td>
                    <button name="command" value="update_edit_phone" class="btn-link"
                            onclick="checkIDPhone(${phone.id})">
                            ${phone.codeCountry}${phone.codeOperator}${phone.number}
                    </button>
                </td>
                <td>${phone.type}</td>
                <td>${phone.comment}</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>

</table>
<script>
    function checkIDPhone(ID) {
        var phoneID = document.getElementById("phone_id");
            phoneID.value = ID;
    }
</script>
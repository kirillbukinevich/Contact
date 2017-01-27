<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-responsive" id = 'phone_table'>

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
            <tr>
                <td><input type="checkbox" name="check_selected_phone" class="check_selected_phone" value="${phone.id}"
                           onchange="checkboxesEditDelete('check_selected_phone','editPhone','deletePhone')"></td>
                <td>
                    <button name="command" value="update_edit_phone" class="btn-link"
                            onclick="popDialog('phoneModal','Edit_Phone')">
                            <p>+${phone.codeCountry}-${phone.codeOperator}-${phone.number}</p>
                    </button>
                </td>
                <td><p>${phone.type}</p></td>
                <td><p>${phone.comment}</p></td>
            </tr>
     </c:forEach>
    </tbody>

</table>

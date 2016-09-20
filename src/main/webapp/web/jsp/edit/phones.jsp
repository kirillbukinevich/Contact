<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form name="loginForm" method="POST" action="controller" id="phones">

        <table class="table table-bordered">

            <thead>
            <tr>
                <th></th>
                <th>number</th>
                <th>home/mobile</th>
                <th>comment</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="phone" items="${phoneList}" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="check_selected_phone" value="${phone.id}"></td>
                    <td>
                        <a href="<c:url value="controller?command=editPhone">
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

</form>
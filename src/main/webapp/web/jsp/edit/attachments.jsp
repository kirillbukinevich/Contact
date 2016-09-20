<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form name="loginForm" method="POST" action="controller" id="attachments">
    <table class="table table-bordered">
            <thead>
            <tr>
                <th></th>
                <th>file name</th>
                <th>date of load</th>
                <th>comment</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="file" items="${attachList}" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="check_selected_file" value="${file.id}"></td>
                    <td>
                        <a href="<c:url value="controller?command=editFile">
                            <c:param name="file_id" value="${file.id}"/>
                        </c:url>"
                           class="btn btn-link">${file.fileName}</a>
                    </td>
                    <td>${file.loadDate}</td>
                    <td>${file.comment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

</form>
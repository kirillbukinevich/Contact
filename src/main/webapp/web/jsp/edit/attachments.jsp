<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-responsive">
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
        <c:if test="${file.deleted == false}">
            <tr>
                <td><input type="checkbox" name="check_selected_file" value="${file.id}"
                           onchange="checkboxes('editAttachFile','deleteAttachFile')"></td>
                <td>
                    <a href="<c:url value="download">
                            <c:param name="file_path" value="${file.employeeID}/${file.id}"/>
                            <c:param name="file_name" value="${file.fileName}"/>
                        </c:url>"
                       class="btn btn-link" onclick="return ${file.saved}">${file.fileName}</a>
                </td>
                <td>${file.loadDate}</td>
                <td>${file.comment}</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
<script>
    //    window.alert(file.saved);
</script>


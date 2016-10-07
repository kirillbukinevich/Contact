<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Trigger/Open The Modal -->
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">

    <!-- The Modal -->
    <div id="attachModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <h2>${type_operation} :${file_name}</h2>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <!-- write here-->
                    <input type="file" class="btn-file" name="file_name" value="${file_name}" required>
                    <label>Комментарий</label>
                    <input type="text" class="form-control" name="comment" value="${comment_file}">
                </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-info" name="command"
                       value="${type_operation}">Сохранить</button>

                <button class="btn btn-info" id="close_attachModal" >Отменить
                </button>

            </div>
        </div>

    </div>
</form>
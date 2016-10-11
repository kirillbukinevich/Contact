<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Trigger/Open The Modal -->
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">

    <!-- The Modal -->
    <div id="attachModal" class="modal attach-img">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <h2>${type_operation} :${file_name}</h2>
            </div>
            <div class="modal-body">
                <div class="form">
                    <div class="form-phone">
                        <!-- write here-->
                        <input type="file" class="btn-file" name="file_name" value="${file_name}" required>
                        <label>Комментарий</label>
                        <input type="text" class="form-control" name="comment" value="${comment_file}">
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-trans btn-trans-success" name="command"
                        value="${type_operation}" ${type_operation=='Edit_file' ? 'formnovalidate' : ''}>Сохранить
                </button>

                <button class="btn btn-trans btn-trans-success" id="close_attachModal">Отменить
                </button>

            </div>
        </div>

    </div>
</form>
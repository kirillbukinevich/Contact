<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">
    <div id="photoModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">


                <h2>ФОТО</h2>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <!-- write here-->
                    <div class="input-group">
                        <label class="btn btn-default btn-file">
                            <input type="file" id="myFile" name="photo_name" style="display: block;">
                        </label>
                        <label for="fileName"></label><input type="text" class="form-control" id="fileName">
                    </div>
                </div>
                <button class="btn btn-info" name="command" value="savePhoto">Сохранить</button>
                <button class="btn btn-danger" name="command" value="delete_photo">Удалить моё фото</button>
                <button class="btn btn-info" id="close_photoModal">Отменить</button>


            </div>
            <div class="modal-footer">

            </div>
        </div>

    </div>
</form>
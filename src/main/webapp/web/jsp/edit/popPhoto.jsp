<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">
    <div id="photoModal" class="modal photo-img">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">


                <h2>ФОТО</h2>
            </div>
            <div class="modal-body">

                <div class="form">
                    <!-- write here-->
                    <div class="input-group">
                            <input type="file" class="btn-file" id="myFile" name="photo_name" style="display: block;" required>
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button class="btn btn-trans btn-trans-success" name="command" value="savePhoto">Сохранить</button>
                <button class="btn btn-trans btn-trans-success" name="command" value="delete_photo" formnovalidate>Удалить моё фото
                </button>
                <button class="btn btn-trans btn-trans-success" id="close_photoModal">Отменить</button>

            </div>
        </div>

    </div>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="photoModal" class="modal photo-img">
    <form name="popPhotoForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm" onsubmit="return false">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <h2>ФОТО</h2>
            </div>
            <div class="modal-body">

                <div class="form">
                    <!-- write here-->
                    <div class="input-group">
                        <input type="file" class="btn-file" id="photo_name" name="photo_name" required>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-trans btn-trans-success" name="command" value="savePhoto"
                onclick="savePhotoChange()">Сохранить</button>
                <button class="btn btn-trans btn-trans-success" name="command" value="delete_photo" onclick="deletePhoto()" formnovalidate>
                    Удалить моё фото
                </button>
                <button class="btn btn-trans btn-trans-success" id="close_photoModal">Отменить</button>
            </div>
        </div>
    </form>
</div>

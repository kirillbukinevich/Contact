<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Trigger/Open The Modal -->
<div id="attachModal" class="modal attach-img">
    <form name="popAttachmentForm" method="POST" action="upload"
          enctype="multipart/form-data" id="popAttachmentForm" onsubmit="return false">

        <!-- The Modal -->

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <h2></h2>
            </div>
            <div class="modal-body">
                <div class="form">
                    <div class="form-phone">
                        <input type="hidden" name="attachment_id">
                        <input type="hidden" name="tableRowIndex">
                        <!-- write here-->
                        <input type="file" class="btn-file" name="file_name" id="file_name" value="${file_name}"
                               required>
                        <label>Комментарий</label>
                        <input type="text" class="form-control" name="comment_attachment" id="comment_attachment"
                               value="${comment_file}">
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-trans btn-trans-success" id="save_attachment_button" name="command"
                        value="New_File" onclick="saveAttachmentChange()">Сохранить
                </button>

                <button class="btn btn-trans btn-trans-success" id="close_attachModal">Отменить
                </button>

            </div>
        </div>

    </form>
</div>
<script type="text/javascript">

</script>
<!-- Trigger/Open The Modal -->
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">

    <!-- The Modal -->
    <div id="attachModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">X</span>

                <h2>${type_operation}</h2>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <!-- write here-->
                    <input type="file" class="btn-file" name="file_name" value="${file_name}" required>
                    <input type="text" class="form-control" name="comment" value="${comment_file}">
                </div>

            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-info" name="command"
                       value="addFile"/>

                <button class="btn btn-info" id="close_attachModal" >Close
                </button>

            </div>
        </div>

    </div>
</form>
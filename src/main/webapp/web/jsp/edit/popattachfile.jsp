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

                <button class="btn btn-info" id="close_popAttach" >Close
                </button>

            </div>
        </div>

    </div>
    <script>
        // Get the modal
        var attachModal = document.getElementById('attachModal');



        var close_button = document.getElementById("close_popAttach");
        close_button.onclick = function () {
            attachModal.style.display = "none"
            return false;
        }




    </script>
</form>
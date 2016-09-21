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

                <input type="submit" class="btn btn-info" name="command"
                       value="addFile"/>
            </div>
            <div class="modal-footer">

            </div>
        </div>

    </div>
    <script>
        // Get the modal
        var attachModal = document.getElementById('attachModal');

        var span = document.getElementsByClassName("close")[1];

        span.onclick = function () {
            attachModal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target == attachModal) {
                attachModal.style.display = "none";
            }
        }

    </script>
</form>
<form name="loginForm" method="POST" action="upload" enctype="multipart/form-data" id="myForm">
    <div id="myModal4" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">


                <h2>New Photo</h2>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <!-- write here-->
                    <div class="input-group">
                        <label class="btn btn-default btn-file">
                            Browse <input type="file" id="myFile" name="photo_name" style="display: block;">
                        </label>
                        <label for="fileName"></label><input type="text" class="form-control" id="fileName">
                    </div>
                </div>
                <button class="btn btn-info" name="command" value="savePhoto">Save</button>
                <button class="btn btn-danger" name="command" value="delete_photo">Delete cuurrent photo</button>
                <button class="btn btn-info" id="close_button2">Close</button>


            </div>
            <div class="modal-footer">

            </div>
        </div>

    </div>


    <script>
        // Get the modal
        var modal4 = document.getElementById('myModal4');

        var close_button2 = document.getElementById("close_button2");
        // When the user clicks on <span> (x), close the modal
        close_button2.onclick = function () {
            modal4.style.display = "none";
            return false;
        };


    </script>
</form>
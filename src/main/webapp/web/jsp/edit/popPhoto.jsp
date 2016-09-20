<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Trigger/Open The Modal -->

<button class="btn btn-default" id="myBtn4">
    <img class="img-responsive"  src="data:image/png;base64, <c:out value='${photo}'/>" />
</button>


<!-- The Modal -->
<div id="myModal4" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">


            <h2>New Photo</h2>
        </div>
        <div class="modal-body">
            <form name="loginForm" method="POST" action="upload" enctype="multipart/form-data">
                <div class="form-group">
                    <!-- write here-->
                    <div class="input-group">
                        <label class="btn btn-default btn-file">
                            Browse <input type="file" id="myFile" name="photo_name" style="display: block;">
                        </label>
                        <input type="text" class="form-control"  id="fileName">
                    </div>
                </div>
                <button class="btn btn-success" name="command"
                        value="savePhoto">Save</button>
            </form>
            <button class="btn btn-info" id="close_button2" >Close
            </button>


        </div>
        <div class="modal-footer">

        </div>
    </div>

</div>

<script>
    // Get the modal
    var modal4 = document.getElementById('myModal4');

    // Get the button that opens the modal
    var btn4 = document.getElementById("myBtn4");

    // Get the <span> element that closes the modal

    // When the user clicks the button, open the modal
    btn4.onclick = function () {
        modal4.style.display = "block";
    }


    var close_button2 = document.getElementById("close_button2");
    // When the user clicks on <span> (x), close the modal
    close_button2.onclick = function () {
        modal4.style.display = "none"
    }
    function myFunction(){
        var x = document.getElementById("myFile");
        var txt = "";
        if ('files' in x) {
            if (x.files.length == 0) {
                txt = "Select one or more files.";
            } else {
                for (var i = 0; i < x.files.length; i++) {
                    txt += "<br><strong>" + (i+1) + ". file</strong><br>";

                }
            }
        }
        else {
            if (x.value == "") {
                txt += "Select one or more files.";
            } else {
                txt += "The files property is not supported by your browser!";
                txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead.
            }
        }
        document.getElementById("fileName").innerHTML = txt;
        }
    // When the user clicks anywhere outside of the modal, close it

</script>
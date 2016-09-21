<!-- Trigger/Open The Modal -->
<html>


<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">X</span>

            <h2>Add phone</h2>
        </div>
        <div class="modal-body">
            <form name="loginForm" method="post" action="controller" id="edit_phone">
                <div class="form-group">
                    <label for="code_country">Enter code country</label>
                    <input type="number" class="form-control" name="code_country" value="${code_country}" id="code_country" min="0" max="999" required>

                    <label for="code_operator">Enter code operator</label>
                    <input type="number" class="form-control" name="code_operator" value="${code_operator}" id="code_operator" min="0" max="99" required>

                    <label for="phone_number">Enter phone number</label>
                    <input type="number" class="form-control" name="phone_number" value="${phone_number}" id="phone_number" min="1000000" max="9999999" required>

                    <select class="selectpicker" name="phone_type">
                        <OPTION> home
                        <OPTION> mobile
                        <OPTION selected>${phone_type}
                    </SELECT>

                    <label>Comment</label>
                    <input type="text" class="form-control" name="comment">

                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-info" form="edit_phone" name="command" value="addPhone">
                Add</button>

        </div>
    </div>

</div>
</html>
<script>
    // Get the modal


    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    var code_country = document.getElementById("code_country");
    code_country.oninvalid = function(event) {
        event.target.setCustomValidity('enter correct code country for e.g. 375');
    }
    var code_operator = document.getElementById("code_operator");
    code_operator.oninvalid = function(event) {
        event.target.setCustomValidity('enter correct code operator for e.g. 33');
    }
    var phone_number = document.getElementById("phone_number");
    phone_number.oninvalid = function(event) {
        event.target.setCustomValidity('enter correct phone number for e.g. 1234567');
    }
</script>
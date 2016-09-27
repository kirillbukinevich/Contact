<!-- Trigger/Open The Modal -->
<html>


<!-- The Modal -->
<div id="phoneModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <h2>${type_operation}</h2>
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
                    <input type="text" class="form-control" name="comment" value="${comment_phone}">

                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-info" form="edit_phone" name="command" value="addPhone">
                Add</button>
            <button class="btn btn-info" id="close_phoneModal" >Close
            </button>


        </div>
    </div>

</div>
</html>

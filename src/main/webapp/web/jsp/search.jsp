<!-- Trigger/Open The Modal -->
<button class="btn btn-trans btn-trans-success" id="myBtn5" name="command" value="empty">Search</button>

<!-- The Modal -->
<div id="myModal5" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="form">
            <form name="loginForm" method="post" action="controller">
                <div class="modal-header">
                    <span class="close">X</span>

                    <h2>Search</h2>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="find_last_name">last name</label>
                        <input type="text" class="form-control" name="find_last_name" id="find_last_name">
                        <label>first name</label>
                        <input type="text" class="form-control" name="find_first_name">
                        <label>patronymic</label>
                        <input type="text" class="form-control" name="find_patronymic">
                        <label>date</label>
                        <select class="selectpicker" name="find_date_direction">
                            <OPTION selected>
                            <OPTION> since
                            <OPTION> until
                        </SELECT>
                        <input type="date" name="find_date_of_birth">

                        <P>Choose your gender:
                            <select class="selectpicker" name="find_sex">
                                <OPTION> male
                                <OPTION> female
                                <OPTION selected>
                            </SELECT>

                            <label>nationality</label>
                            <input type="text" class="form-control" name="find_nationality">

                            <select class="selectpicker" name="find_family_status">
                                <OPTION> married
                                <OPTION> free
                                <OPTION selected>
                            </SELECT>
                            <label>country</label>
                            <input type="text" class="form-control" name="find_country">
                            <label>city</label>
                            <input type="text" class="form-control" name="find_city">
                            <label>street</label>
                            <input type="text" class="form-control" name="find_street">
                            <label>house</label>
                            <input type="text" class="form-control" name="find_house">
                            <label>flat</label>
                            <input type="text" class="form-control" name="find_flat">
                            <label>index</label>
                            <input type="text" class="form-control" name="find_index">

                    </div>


                </div>


                <div class="modal-footer">
                    <button class="btn btn-info" name="command"
                            value="search">Search
                    </button>
                    <button class="btn btn-info" id="close_button2">Close
                    </button>

                </div>

            </form>
        </div>
    </div>

</div>

<script>
    // Get the modal
    var modal5 = document.getElementById('myModal5');

    // Get the button that opens the modal
    var btn5 = document.getElementById("myBtn5");

    // Get the <span> element that closes the modal
    var span5 = document.getElementsByClassName("close")[4];

    var close_button5 = document.getElementById("close_button2");

    close_button5.onclick = function () {
        modal5.style.display = "none"
        return false;
    }
    // When the user clicks the button, open the modal
    btn5.onclick = function () {
        modal5.style.display = "block";
    }
    span5.onclick = function () {
        modal5.style.display = "none"
        return false;
    }
    // When the user clicks on <span> (x), close the modal

    // When the user clicks anywhere outside of the modal, close it
</script>
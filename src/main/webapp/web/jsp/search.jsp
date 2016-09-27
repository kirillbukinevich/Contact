<!-- Trigger/Open The Modal -->
<button class="btn btn-trans btn-trans-success" id="myBtn5" onclick="popDialog('searchModal')"><span class="glyphicon glyphicon-search" ></span>
    Search</button>

<!-- The Modal -->
<div id="searchModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="form">
            <form name="loginForm" method="post" action="controller">
                <div class="modal-header">
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
                    <button class="btn btn-info" id="close_searchModal">Close
                    </button>

                </div>

            </form>
        </div>
    </div>

</div>

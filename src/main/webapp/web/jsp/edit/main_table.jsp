<p class="contact"><label >Name</label></p>
<input type="text" class="form-control" name="first_name" id = "first_name"  placeholder="Enter your name"
       value="${first_name}" pattern="^[A-Za-z]+(-)?([A-Za-z]+)$"  required>
<input type="text" class="form-control" name="patronymic"  value="${patronymic}"
       placeholder="Enter your patronymic" pattern="^[A-Za-z]+(-)?([A-Za-z]+)$" required >
<input type="text" class="form-control" name="last_name"  value="${last_name}"
       placeholder="Enter your last name" pattern="^[A-Za-z]+(-)?([A-Za-z]+)$" required >

<label>Birthday</label>
<input type="date" class="form-control" name="date_of_birth" value="${date_of_birth}"
       placeholder="Your birthday yyyy-mm-dd" required>

<label>i am</label>
<select class="select-style" name="gender">
    <OPTION selected>
    <OPTION ${gender == 'male' ? 'selected' : ''}> male
    <OPTION ${gender == 'female' ? 'selected' : ''}> female
</SELECT><br><br>

<label>Nationality</label>
<input type="text" class="form-control" name="nationality" value="${nationality}"
       placeholder="Nationality">

<label>Family Status</label>
<select class="select-style" name="family_status" id="family_status" required>
    <OPTION selected>
    <OPTION ${family_status == 'married' ? 'selected' : ' '}> married
    <OPTION ${family_status == 'free' ? 'selected' : ' '}> free
</SELECT><br><br>

<label>Web site</label>
<input type="url" class="form-control" name="web_site" id = "web_site" value="${web_site}" placeholder="Web Site" required>
<label>Email</label>
<input type="email" class="form-control" name="email" id = "email" value="${email}" placeholder="Email" required>
<label>Work place</label>
<input type="text" class="form-control" name="work_place" value="${work_place}"
       placeholder="Work place" required>

<p><label>Address</label></p>
<input type="text" class="form-control" name="country" id="country" value="${country}"
       placeholder="country" pattern="^[A-Za-z]+(-)?[A-Za-z]+$" required>
<input type="text" class="form-control" name="city" id="city" value="${city}"
       placeholder="city" pattern="^[A-Za-z]+(-)?[A-Za-z]+$" required>
<input type="text" class="form-control" name="street" id="street" value="${street}"
       placeholder="street" pattern="^[A-Za-z]+(-)?[A-Za-z]+$" required>
<input type="number" class="form-control" name="house" id="house" value="${house}"
       placeholder="home" min="1" max="999"  required>
<input type="number" class="form-control" name="flat" id="flat" value="${flat}"
       placeholder="flat" min="1" max="9999" required>
<input type="number" class="form-control" name="index" id="index" value="${index}"
       placeholder="index" min="100000" max="999999" required>

<script>
    var fName = document.getElementById("first_name");
    fName.oninvalid = function(event) {
        event.target.setCustomValidity('first name should only contain letters. e.g. john,ol-lo');
    }
    var lName = document.getElementById("last_name");
    fName.oninvalid = function(event) {
        event.target.setCustomValidity('last name should only contain letters. e.g. john,ol-lo');
    }
    var patronymic = document.getElementById("patronymic");
    patronymic.oninvalid = function(event) {
        event.target.setCustomValidity('patronymic should only contain letters. e.g. john,ol-lo');
    }
    var country = document.getElementById("city");
    country.oninvalid = function(event) {
        event.target.setCustomValidity('please enter the real contry name');
    }
    var city = document.getElementById("city");
    city.oninvalid = function(event) {
        event.target.setCustomValidity('please enter the real city name');
    }
    var street = document.getElementById("street");
    street.oninvalid = function(event) {
        event.target.setCustomValidity('please enter real street name');
    }




</script>

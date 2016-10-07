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
//valid phone
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
var house = document.getElementById("house");
house.oninvalid = function(event) {
    event.target.setCustomValidity('enter correct house number range(1-999)');
}
var flat = document.getElementById("flat");
flat.oninvalid = function(event) {
    event.target.setCustomValidity('enter correct flat number range(1-999)');
}
var index = document.getElementById("index");
index.oninvalid = function(event) {
    event.target.setCustomValidity('enter correct index for e.g. 123456');
}
function checkDate() {
    var date_of_birth = document.getElementById("date_of_birth");
    var birth_date = new Date(date_of_birth.value);
    var current_date = Date.now();
    if(current_date<=birth_date){
        date_of_birth.setCustomValidity("Wrong date");
    }else{
        date_of_birth.setCustomValidity("");
    }
}
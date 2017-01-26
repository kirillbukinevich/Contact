function checkDate() {
    var date_of_birth = document.getElementById("date_of_birth");
    var birth_date = new Date(date_of_birth.value);
    var current_date = Date.now();
    if (current_date <= birth_date) {
        date_of_birth.parentNode.getElementsByTagName('p')[0].style.display = ''
    } else {
        date_of_birth.parentNode.getElementsByTagName('p')[0].style.display = 'none'
    }
}

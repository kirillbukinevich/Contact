function checkDate() {
    var date_of_birth = document.getElementById("date_of_birth"),
        birth_date = new Date(date_of_birth.valueAsNumber),
        current_date = Date.now();

    var p = date_of_birth.parentNode.getElementsByTagName('p')[1];
    if (current_date > birth_date) {
        p.style.display = 'none';
        date_of_birth.labels[0].className = '';
    } else {
        p.style.display = '';
        date_of_birth.labels[0].className = 'error';
    }
}

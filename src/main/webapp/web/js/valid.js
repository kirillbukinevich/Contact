(function () {
    var form = document.getElementById('edit_form');
    var inputS = form.getElementsByTagName("input");
    console.dir(inputS);
    for (var input in inputS) {
        if (!inputS.hasOwnProperty(input)) continue;
        if (inputS[input].nodeName === 'INPUT' &&
            (inputS[input].type === 'text' || inputS[input].type === 'email' || inputS[input].type === 'url')) {
            inputS[input].oninput = function (event) {
                checkForm(event);
            };
            checkForm(inputS[input]);
        }
    }
    form.gender.onchange = function (event) {
        checkForm(event);
    };
    form.family_status.onchange = function (event) {
        checkForm(event);
    };
    checkForm(form.gender);
    checkForm(form.family_status);


    function checkForm(event) {
        if (!event) {
            return;
        }

        var target;
        if (event.target) {
            target = event.target;
        } else {
            target = event;
        }
        var label = target.parentNode;
        if (!target.validity.valid) {
            label.className = 'error';
            label.getElementsByTagName('p')[1].style.display = '';
        } else {
            label.className = '';
            label.getElementsByTagName('p')[1].style.display = 'none';
        }
    }
}());





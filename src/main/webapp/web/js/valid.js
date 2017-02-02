(function () {
    var form = document.getElementById('edit_form');
    var inputs = form.getElementsByTagName("input");
    console.dir(inputs);
    for (var input in inputs) {
        if (!inputs.hasOwnProperty(input)) continue;
        if (inputs[input].nodeName === 'INPUT' &&
            (inputs[input].type === 'text' || inputs[input].type === 'email' || inputs[input].type === 'url')) {
            inputs[input].oninput = function (event) {
                checkForm(event);
            };
        }
    }
    form.gender.onchange = function (event) {
        checkForm(event);
    };
    form.family_status.onchange = function (event) {
        checkForm(event);
    };

    function checkForm(event) {
        var target = event.target;
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




document.getElementById('div-main-form').onclick = function (event) {
    var target = event.target;
    if (target.nodeName == 'INPUT') {
        var label = target.parentNode;
        if (label.className == 'error') {
            label.className = '';
            label.getElementsByTagName('p')[1].style.display = 'none';
            // target.onblur = null;
        }

        target.onblur = function () {
            if (!(target.validity.valid || label.className)) {
                label.className = 'error';
                label.getElementsByTagName('p')[1].style.display = '';
            }
        };
    }
};




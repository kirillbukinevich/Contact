(function() {
    document.getElementById('div-main-form').onclick = function (event) {
        var target = event.target;
        if (target.nodeName == 'INPUT' && target.type == 'text') {
            console.dir(target);
            var label = target.parentNode;
            target.oninput = function () {
                if (!target.validity.valid) {
                    label.className = 'error';
                    label.getElementsByTagName('p')[1].style.display = '';
                }else {
                    label.className = '';
                    label.getElementsByTagName('p')[1].style.display = 'none';
                }
            };
        }
    };
}());




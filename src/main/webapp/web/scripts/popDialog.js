function popDialog(modalID){
    var modal = document.getElementById(modalID);
    modal.style.display = "block";
    var close_button = document.getElementById("close_" + modalID);
    close_button.onclick = function () {
        modal.style.display = "none";
            return false;
    }
}
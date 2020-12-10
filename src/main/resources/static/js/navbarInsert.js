function insertnav() {
var nav = '<nav class="navbar navbar-default bg-light navbar-expand-lg ">' +
        '<div class="container-fluid .text-dark">' +
        '<div class="navbar-header">' +
        '<a class="navbar-brand" href="#">Antistress Dagbogen</a>' +
   ' </div>' +
    '<ul class="nav navbar-nav">' +
        '<li class="nav-item"><a class="nav-link" href="#">Home</a></li>'+
    '<li class="nav-item"><a class="nav-link" href="#">Guide</a></li>'+
    '<li class="nav-item"><a class="nav-link" href="#">Se Dagbog</a></li>'+
    '<li class="nav-item"><a class="nav-link" href="#">Page 3</a></li>'+
    '</ul>'+
    '<ul class="nav navbar-nav navbar-right">'+
    '<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>'+
    '</ul>'+
    '</div>'+
    '</nav>';
var demo = document.getElementById('navicom');
demo.insertAdjacentHTML('beforeend',nav)
}
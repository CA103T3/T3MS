$(document).ready(function () {
    $("#sidebar-wrapper ul").on("hide.bs.collapse", function(event) {
        if ($(this).is(event.target)) {
            $(this).prev("a").find("span").removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-right');
            //console.log(this.id);
        }
    });
    $("#sidebar-wrapper ul").on("show.bs.collapse", function(event) {
        if ($(this).is(event.target)) {
            $(this).prev("a").find("span").removeClass('glyphicon-chevron-right').addClass('glyphicon-chevron-down');
            //console.log(this.id);
        }
    });
    $(".menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    //for RWD
    $(window).on("resize", function () {
        $("#wrapper").css("margin-top", $("nav").height());
    });
});
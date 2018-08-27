$(document).ready(function(){
    $(window).on("resize", function () {
        $("body").css("padding-top", $("nav").height());
    });
});
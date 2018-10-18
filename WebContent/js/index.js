/**
 * display close icon and close div
 *
 * @param  string div - div #id selector (e.g. #float-div-left)
 * @param  string xitem - span .class selector (e.g. .close-item)
 * @return void
 */
/*
function close_div(div, xitem)
{
    // display close icon or not
    $(div).mouseover(function() {
        $(div + " " + xitem).css("display", "block");
        //console.log("mouseover");
    }).mouseout(function() {
        $(div + " " + xitem).css("display", "none");
        //console.log("mouseout");
    });

    // close div
    $(div + " " + xitem).click(function() {
        $(div).css("display", "none");
    });
}
*/

$(document).ready(function(){

    $("nav").addClass("slide");
    $("footer").addClass("slide");
    
    //for scroller
    $(window).on("resize", function () {
        $("body").css("padding-top", $("nav").height());
    });
    //http://www.dwuser.com/education/content/creating-a-jquery-image-scroller/
    $("#scroller").prop("width", $(window).width());
    var scroller = $('#scroller div.innerScrollArea');
    var scrollerContent = scroller.children('ul');
    scrollerContent.children().clone().appendTo(scrollerContent);
    var curX = 0;
    scrollerContent.children().each(function(){
        var $this = $(this);
        $this.css('left', curX);
        curX += $this.width();
    });
    var fullW = curX / 2;
    var viewportW = scroller.width();
    
    //First, we have a slightly non-traditional use of the jQuery .animate() method.  We use it to tween (animate) speed changes in our scroller, avoiding jerky stops and starts.
    // Scrolling speed management
    var controller = {curSpeed:0, fullSpeed:2}; //The fullSpeed:2 value can be changed to adjust the scrolling speed.
    //console.log(controller);
    var $controller = $(controller);
    //console.log($controller);
    //console.log($controller instanceof jQuery);
    var tweenToNewSpeed = function(newSpeed, duration)
    {
        if (duration === undefined)
            duration = 600; //This value can be changed to make the rotator stop and start more quickly or slowly.
        $controller.stop(true).animate({curSpeed:newSpeed}, duration);
        //console.log("tweenToNewSpeed : controller.curSpeed - " + newSpeed);
    };

    //We use the .hover() method to listen for mouse interaction, pausing and resuming the scroller on hover and hover-out respectively.
    // Pause on hover
    //comment it for stopping
    /*
    scroller.hover(function(){
        tweenToNewSpeed(0);
    }, function(){
        tweenToNewSpeed(controller.fullSpeed);
    });
    */
    
    //Here, we define the scrolling method that will be called ever 1/50th of a second.  It scrolls the content, looping back as needed - just as in the animation above.
    // Scrolling management; start the automatical scrolling
    var doScroll = function()
    {
        var curX = scroller.scrollLeft();
        //console.log("doScroll : controller.curSpeed - " + controller.curSpeed);
        var newX = curX + controller.curSpeed;
        if (newX > fullW*2 - viewportW) //Whenever we reach the end, we jump back.
            newX -= fullW;
        scroller.scrollLeft(newX);  //We use the jQuery .scrollLeft() method to get and apply the scroll position.
        //console.log(newX);
    };
    //Finally, we use setInterval to call the scrolling method every 20 milliseconds.  We also set the scrolling speed to "full steam ahead."
    setInterval(doScroll, 20);
    //comment it for stopping
    //tweenToNewSpeed(controller.fullSpeed);
    
    var close_div = function(div, xitem, tweenToNewSpeed, controller, scroller){
        // display close icon or not
        $(div).mouseover(function() {
            $(div + " " + xitem).css("display", "block");
            //console.log("mouseover");
        }).mouseout(function() {
            $(div + " " + xitem).css("display", "none");
            //console.log("mouseout");
        });
        
        // close div
        $(div + " " + xitem).click(function() {
            $(div).css("display", "none");
            
            //We use the .hover() method to listen for mouse interaction, pausing and resuming the scroller on hover and hover-out respectively.
            // Pause on hover
            scroller.hover(function(){
                tweenToNewSpeed(0);
            }, function(){
                tweenToNewSpeed(controller.fullSpeed);
            });
            
            //set the scrolling speed to "full steam ahead."
            tweenToNewSpeed(controller.fullSpeed);
        });
    }
    
    //close_div("#announce",".close-item", tweenToNewSpeed, controller, scroller);
    close_div("#lightBox",".close-item", tweenToNewSpeed, controller, scroller);
    
    // Slide in elements on scroll
    $(window).scroll(function() {
      $(".slideanim").each(function(){
        var pos = $(this).offset().top;
  
        var winTop = $(window).scrollTop();
          if (pos < winTop + 600) {
            $(this).addClass("slide");
          }
      });
    });

    $(".intro-content").each(function() {
        var maxwidth = 150;
        //console.log($(this).text().trim());
        if($(this).text().trim().length > maxwidth) {
            let content = $(this).text().trim().substring(0, maxwidth) + '...';
            //$(this).text($(this).text().trim().substring(0, maxwidth) + '...');
            $(this).empty();
            $(this).append("<p>" + content + "</p>");
            //console.log($(this).text());
        }
    });

    $(".act-desc").each(function() {
        var maxwidth = 15;
        //console.log($(this).text().trim());
        if($(this).text().trim().length > maxwidth) {
            let content = $(this).text().trim().substring(0, maxwidth) + '...';
            //$(this).text($(this).text().trim().substring(0, maxwidth) + '...');
            $(this).empty();
            $(this).append(content);
            //console.log($(this).text());
        }
    });

    $(".anc-con").each(function() {
        console.log($(this).text());
        let content = $(this).text();
        //https://stackoverflow.com/questions/27464393/how-to-automatically-replace-n-with-br-in-the-current-document-text-as-oppo
        content = content.replace(/(?:\n\r|\r\n|\r|\n)/g,"<br />");
        console.log(content);
        $(this).empty();
        $(this).append(content);
        /*
        var maxwidth = 15;
        //console.log($(this).text().trim());
        if($(this).text().trim().length > maxwidth) {
            let content = $(this).text();
            //$(this).text($(this).text().trim().substring(0, maxwidth) + '...');
            $(this).empty();
            $(this).append(content);
            //console.log($(this).text());
        }
        */
    });

});
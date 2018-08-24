//http://www.dwuser.com/education/content/creating-a-jquery-image-scroller/
$(function(){
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
    scroller.hover(function(){
        tweenToNewSpeed(0);
    }, function(){
        tweenToNewSpeed(controller.fullSpeed);
    });

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
    tweenToNewSpeed(controller.fullSpeed);
    
});
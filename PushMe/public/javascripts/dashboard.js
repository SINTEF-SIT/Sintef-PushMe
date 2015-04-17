/*Menu toggle script*/
$("#menu-toggle").click(function(e) {
	e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

/*Carousel speed*/
$('.carousel').carousel({
        interval: 10000
    })

/*Carousel script*/
setCarouselHeight('#tip-carousel');

function setCarouselHeight(id) {
	var slideHeight = [];
    $(id+' .item').each(function() {
		// add all slide heights to an array
        slideHeight.push($(this).height());
    });

    // find the tallest item
    max = Math.max.apply(null, slideHeight);

    // set the slide's height
    $(id+' .carousel-content').each(function() {
        $(this).css('height',max+'px');
    });
}

/*Donut chart*/
$.getScript('http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js',function(){
$.getScript('http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.0/morris.min.js',function(){
      Morris.Donut({
        element: 'user_activity-donut',
        data: [
         {label: "Jogging", value: 50},
         {label: "Sailing", value: 20},
         {label: "Walking", value: 15}
        ]
      });
      Morris.Donut({
        element: 'global_activity-donut',
        data: [
         {label: "Jogging", value: 30},
         {label: "Sailing", value: 5},
         {label: "Walking", value: 65}
        ]
      });
});
});
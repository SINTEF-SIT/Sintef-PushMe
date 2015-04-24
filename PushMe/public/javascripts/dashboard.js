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
$.getScript('https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.2/raphael-min.js',function(){
$.getScript('https://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js',function(){

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
      
	Morris.Line({
		element: 'step_count-line',
		  data: [
		    { y: '2006', a: 100, b: 90 },
		    { y: '2009', a: 4,  b: 65 },
		    { y: '2008', a: 50,  b: 40 },
		    { y: '2009', a: 75,  b: 65 },
		    { y: '2010', a: 50,  b: 40 },
		    { y: '2011', a: 75,  b: 65 },
		    { y: '2012', a: 100, b: 90 }
		  ],
		  xkey: 'y',
		  ykeys: ['a', 'b'],
		  labels: ['Series A', 'Series B']
	});
});
});
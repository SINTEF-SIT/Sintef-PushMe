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
	        data: [{label: "Running", value: 9000}]
	      });
     
		Morris.Line({
			element: 'week_step-line',
			  data: [
			    { y: '2006', a: 1, b: 7 },
			    { y: '2007', a: 2,  b: 6 },
			    { y: '2008', a: 3,  b: 5 },
			    { y: '2009', a: 4,  b: 4 },
			    { y: '2010', a: 5,  b: 3 },
			    { y: '2011', a: 6,  b: 2 },
			    { y: '2012', a: 7, b: 1 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Series A', 'Series B']
		});
 
		Morris.Line({
			element: 'month_step-line',
			  data: [
			    { y: '2006', a: 100, b: 1 },
			    { y: '2007', a: 4,  b: 2 },
			    { y: '2008', a: 50,  b: 3 },
			    { y: '2009', a: 75,  b: 65 },
			    { y: '2010', a: 50,  b: 32 },
			    { y: '2011', a: 75,  b: 12 },
			    { y: '2012', a: 100, b: 5 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Series A', 'Series B']
		});
 
		Morris.Line({
			element: 'year_step-line',
			  data: [
			    { y: '2006', a: 1, b: 90 },
			    { y: '2007', a: 3,  b: 65 },
			    { y: '2008', a: 8,  b: 40 },
			    { y: '2009', a: 16,  b: 65 },
			    { y: '2010', a: 24,  b: 40 },
			    { y: '2011', a: 38,  b: 65 },
			    { y: '2012', a: 51, b: 90 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Series A', 'Series B']
		});
});
});
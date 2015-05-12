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
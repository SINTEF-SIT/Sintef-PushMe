/*Display range slider values*/    
function updateTextInput(val, low, med, high, name) {		    	
	if(val==1){val = low}		    	
	else if(val==2){val =  med}		    	
	else if(val==3){val = high}		      
	document.getElementById(name).innerHTML = val;		    
}

/*Setting current date as default*/
$(document).ready(function(year, month, day) {	
	 var d = new Date();
	 var month = d.getMonth()+1;
	 var day = d.getDate();

	 var output = d.getFullYear() + '-' +
	     (month<10 ? '0' : '') + month + '-' +
	     (day<10 ? '0' : '') + day;
	 
		$('.date').val(output),
		$('tbody tr:has(div.daily-activity)').hide(),
		$('tbody tr:has(div.'+ output +')').show(),
		$('tbody tr:has(div.daily-pedo)').hide(),
		$('tbody tr:has(div.pedo-'+ output +')').show()
});	

/*Use date checked in the calendar*/
$(function () {	
	$("#datepicker").datepicker({
		inline: true,
		dateFormat: 'yy-mm-dd',
		onSelect: function () {
			date = this.value,
			$('.date').val(this.value),
			$('tbody tr:has(div.daily-activity)').hide(),
			$('tbody tr:has(div.'+ date +')').show(),
			$('tbody tr:has(div.daily-pedo)').hide(),
			$('tbody tr:has(div.pedo-'+ date +')').show();
			totalRow = $('#daily-activity-table tr:visible').length;
			if(totalRow == 1){
				//TODO: Hide table header when all rows are hidden
			}
			}
	});
});

/*Call on function to delete user-activity*/
function deleteUA(id){
	$.post("/useractivity/delete/"+id, function( data ) {
		});
	location.reload();
}

/*Call on function to delete pedometer recording*/
function deletePedo(id){
	$.post("/usersteps/delete/"+id, function( data ) {
		});
	location.reload();
}

/*Send password to email*/
function forgotPassword(){
	alert("Your password has been sent to your email.")
}

/*Click tracker*/
function clickTracker(module, email){
	$.post("/clickTracker/"+email+"/"+module, function( data ) {
	});
}

/*Table pagination*/
$.fn.pageMe = function(opts){
    var $this = this,
        defaults = {
            perPage: 7,
            showPrevNext: false,
            hidePageNumbers: false
        },
        settings = $.extend(defaults, opts);
    
    var listElement = $this;
    var perPage = settings.perPage; 
    var children = listElement.children();
    var pager = $('.pager');
    
    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }    
    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }    
    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
    }    
    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
        curr++;
    }    
    if (settings.showPrevNext){
        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
    }    
    pager.find('.page_link:first').addClass('active');
    pager.find('.prev_link').hide();
    if (numPages<=1) {
        pager.find('.next_link').hide();
    }
  	pager.children().eq(1).addClass("active");
    
    children.hide();
    children.slice(0, perPage).show();
    
    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage,perPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
        previous();
        return false;
    });
    pager.find('li .next_link').click(function(){
        next();
        return false;
    });    
    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }     
    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;        
        children.css('display','none').slice(startAt, endOn).show();        
        if (page>=1) {
            pager.find('.prev_link').show();
        }
        else {
            pager.find('.prev_link').hide();
        }        
        if (page<(numPages-1)) {
            pager.find('.next_link').show();
        }
        else {
            pager.find('.next_link').hide();
        }        
        pager.data("curr",page);
      	pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");    
    }
};
$(document).ready(function(){
  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:20});
});
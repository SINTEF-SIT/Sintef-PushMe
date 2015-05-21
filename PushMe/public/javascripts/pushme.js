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
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
		$('tbody tr:has(label.daily-activity)').hide(),
		$('tbody tr:has(label.'+ output +')').show()
});		
/*Use date checked in the calendar*/
$(function () {
	
	$("#datepicker").datepicker({
		inline: true,
		dateFormat: 'yy-mm-dd',
		onSelect: function () {
			date = 'label.' + this.value,
			$('.date').val(this.value),
			$('tbody tr:has(label.daily-activity)').hide(),
			$('tbody tr:has('+ date +')').show()
			}
	});
});

/*Call on function to delete user-activity*/
function deleteUA(id){
	$.post("/useractivity/delete/"+id, function( data ) {
		});
	location.reload();
}

/*Numbering table*/
function table(){
	var table = document.getElementsByTagName('table')[0],
	  rows = table.getElementsByTagName('tr'),
	  text = 'textContent' in document ? 'textContent' : 'innerText';

	for (var i = 0, len = rows.length; i < len; i++) {
	  rows[i].children[0][text] = i + ': ' + rows[i].children[0][text];
	}
}
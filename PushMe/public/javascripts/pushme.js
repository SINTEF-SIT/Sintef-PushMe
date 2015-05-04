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
	 
		$('.date').val(output)
	});		
/*Use date checked in the calendar*/
$(function () {
   $("#datepicker").datepicker({
       inline: true,
       dateFormat: 'yy-mm-dd',
       onSelect: function () {
           $('.date').val(this.value)
       }
   });
});
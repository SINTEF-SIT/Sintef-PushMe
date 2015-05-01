/*Display range slider values*/    
function updateTextInput(val, low, med, high, name) {		    	
	if(val==1){val = low}		    	
	else if(val==2){val =  med}		    	
	else if(val==3){val = high}		      
	document.getElementById(name).innerHTML = val;		    
}
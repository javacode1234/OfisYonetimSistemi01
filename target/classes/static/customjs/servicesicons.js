$(document).ready(function(){
	$('#addServiceModal #addServiceForm .dropdown-item').click(function(e){
		e.preventDefault();
		var text = $(this).text();
		var start = text.indexOf(':')+1;
		var end = text.lastIndexOf(':')-1;
		var icon = text.substring(start,end );
		//alert(icon2);
		$('#addServiceModal #addServiceForm #hpServiceIcon').val(icon);
		//$('#addServiceModal #addServiceForm #hpServiceIcon').attr('readonly', 'readonly');
		
	});
	
	$('#editServiceModal #editServiceForm .dropdown-item').click(function(e){
			e.preventDefault();
			var text = $(this).text();
			var start = text.indexOf(':')+1;
			var end = text.lastIndexOf(':')-1;
			var icon = text.substring(start,end );
			//alert(icon2);
			$('#editServiceModal #editServiceForm #hpServiceIcon').val(icon);
			//$('#addServiceModal #addServiceForm #hpServiceIcon').attr('readonly', 'readonly');
			
		});
});
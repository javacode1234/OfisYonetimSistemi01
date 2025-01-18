$(document).ready(function() {
	
	
	// updateProfile image to form //
	$("form#updateProfileForm #inputUserImage").on('change', function() {
		readURLUpdateProfileImage(this);
	});
	// /updateProfile image to form //

});


/* updateProfile image to form */
function readURLUpdateProfileImage(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("form#updateProfileForm #userImage").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* /updateProfile image to form */
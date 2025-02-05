$(document).ready(function() {
	
	// see pwd on form //
	$('#chanchePwdForm #toggleCurrentPassword').click(function(e) {
		// Şifreyi aç/kapa işlevi
		e.preventDefault();
		const passwordField = $('#chanchePwdForm #currentPassword');
		const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
		passwordField.attr('type', type);

		// İkonu değiştir
		$(this).find('i').toggleClass('bi-eye bi-eye-slash');
	});
	$('#chanchePwdForm #toggleNewPassword').click(function(e) {
		// Şifreyi aç/kapa işlevi
		e.preventDefault();
		const passwordField = $('#chanchePwdForm #newPassword');
		const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
		passwordField.attr('type', type);

		// İkonu değiştir
		$(this).find('i').toggleClass('bi-eye bi-eye-slash');
	});
	$('#chanchePwdForm #toggleConfirmPassword').click(function(e) {
		// Şifreyi aç/kapa işlevi
		e.preventDefault();
		const passwordField = $('#chanchePwdForm #confirmPassword');
		const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
		passwordField.attr('type', type);

		// İkonu değiştir
		$(this).find('i').toggleClass('bi-eye bi-eye-slash');
	});
	//  /see pwd  on form//
	
	/* password confirmation */
	$('#chanchePwdForm #newPassword, #confirmPassword').on('input', function() {
		const password1 = $('#newPassword').val();
		const password2 = $('#confirmPassword').val();

		// Parolaları kontrol et
		if(password1 && password2){
			if ( password1 === password2) {
				$('#submitBtn').prop('disabled', false);
				$('#errorMessage').addClass('d-none');
				//$('#errorMessage').hide(); // Hata mesajını gizle
			} else {
				$('#submitBtn').prop('disabled', true);
				if (password1 && password2) {
					//$('#errorMessage').show(); // Hata mesajını göster
					$('#errorMessage').removeClass('d-none');
				} else {
					//$('#errorMessage').hide(); // Eğer alanlardan biri boşsa hata mesajını gizle
					$('#errorMessage').addClass('d-none');
				}
			}
		}
	});

	/* /password confirmation */
	
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
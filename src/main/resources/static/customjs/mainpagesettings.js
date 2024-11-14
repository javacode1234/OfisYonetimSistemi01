$("document").ready(function(){
	
/* fullname doldur start*/
$("form#homePageSettingsPersonalInfoForm #firstName").on('keypress click focus',function(){
	var firstName = $("form#homePageSettingsPersonalInfoForm #firstName").val();
	var lastName = $("form#homePageSettingsPersonalInfoForm #lastName").val();
	$("form#homePageSettingsPersonalInfoForm #fullName").val(firstName+" "+lastName);
})
$("form#homePageSettingsPersonalInfoForm #lastName").on('keypress click focus',function(){
	var firstName = $("form#homePageSettingsPersonalInfoForm #firstName").val();
	var lastName = $("form#homePageSettingsPersonalInfoForm #lastName").val();
	$("form#homePageSettingsPersonalInfoForm #fullName").val(firstName+" "+lastName);
})
/* fullname doldur end*/


/* homepageSettings form change img satart */
	$("form#homePageSettingsPersonalInfoForm #formImage").on('change', function(){
		readURL(this);
	});
/* homepageSettings form change img end */

});


/* main user form change img satart */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {// #homePageSettingsForm #col1 #card #card-body 
            $("form#homePageSettingsPersonalInfoForm #smmmLogo").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }	
}
/* main user form change img satart */
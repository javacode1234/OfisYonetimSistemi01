$("document").ready(function() {

	$('#addReferansModalBtn').click(function(e) {
		e.preventDefault();
		$("#addReferansModal .modal-body #refaransFormImage").val('');
		$("#addReferansModal .modal-body #referansResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#addReferansModal .modal-body #referansId").val("");
		$("#addReferansModal .modal-body #referansName").val("");
		$("#addReferansModal .modal-body #referansMeslek").val("");
		$("#addReferansModal .modal-body #referansStar1").prop('checked', false);
		$("#addReferansModal .modal-body #referansStar1icon").removeClass().addClass('bi bi-star');

		$("#addReferansModal .modal-body #referansStar1").nextAll('i').removeClass().addClass('bi bi-star')
			.nextAll('input').attr('disabled', true).prop('checked', false);

		$("#addReferansModal .modal-body #referansGorus").val("");
		$("#addReferansModal .modal-body #referansActive").prop('checked', false);
	});
	/* /addReferansform clear */
	/* star1 when clicked */
	$("#addReferansModal .modal-body input[type='checkbox'], #editReferansModal .modal-body input[type='checkbox']").change(function(e) {
		e.preventDefault();
		if ($(this).is(':checked')) {
			$(this).next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
		} else {
			$(this).next('i').removeClass().addClass('bi bi-star');
			$(this).nextAll('input').attr('disabled', true).prop('checked', false).nextAll('i').removeClass().addClass('bi bi-star');

		}

	});
	/* star1 when clicked */
	/* addReferansform clearbtn */
	$('#addReferansModal .modal-body #addReferansFormClearBtn').click(function(e) {
		e.preventDefault();
		$("#addReferansModal .modal-body #refaransFormImage").val('');
		$("#addReferansModal .modal-body #referansResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#addReferansModal .modal-body #referansId").val("");
		$("#addReferansModal .modal-body #referansName").val("");
		$("#addReferansModal .modal-body #referansMeslek").val("");
		$("#addReferansModal .modal-body #referansStar1").prop('checked', false);
		$("#addReferansModal .modal-body #referansStar1icon").removeClass().addClass('bi bi-star');

		$("#addReferansModal .modal-body #referansStar1").nextAll('i').removeClass().addClass('bi bi-star')
			.nextAll('input').attr('disabled', true).prop('checked', false);

		$("#addReferansModal .modal-body #referansGorus").val("");
		$("#addReferansModal .modal-body #referansActive").prop('checked', false);
	});
	/* /addReferansform clear */
	/* editReferansform clear */
	$('#editReferansModal #editReferansForm #editReferansFormClearBtn').click(function(e) {
		e.preventDefault();
		editReferansModalClear();
	});
	/* /editReferansform clear */

	/* view referans modal start*/
	$("table#referansTable #referansTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewReferansModal .modal-title").text("Referans Bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(hpref, status) {
			$("#viewReferansModal .modal-body #refaransFormImage").val('');
			$("#viewReferansModal .modal-body #referansResim").attr('src', 'data:image/*;base64,' + hpref.stringResim);
			$("#viewReferansModal .modal-body #referansId").val(hpref.id);
			$("#viewReferansModal .modal-body #referansName").val(hpref.name);
			$("#viewReferansModal .modal-body #referansMeslek").val(hpref.meslek);

			$("#viewReferansModal .modal-body #referansStar1").prop('checked', hpref.star1);
			if (hpref.star1 == true) {
				$("#viewReferansModal .modal-body #referansStar1icon").removeClass().addClass('bi bi-star-fill');
			}
			$("#viewReferansModal .modal-body #referansStar2").prop('checked', hpref.star2);
			if (hpref.star2 == true) {
				$("#viewReferansModal .modal-body #referansStar2icon").removeClass().addClass('bi bi-star-fill');
			}
			$("#viewReferansModal .modal-body #referansStar3").prop('checked', hpref.star3);
			if (hpref.star3 == true) {
				$("#viewReferansModal .modal-body #referansStar3icon").removeClass().addClass('bi bi-star-fill');
			}
			$("#viewReferansModal .modal-body #referansStar4").prop('checked', hpref.star4);
			if (hpref.star4 == true) {
				$("#viewReferansModal .modal-body #referansStar4icon").removeClass().addClass('bi bi-star-fill');
			}
			$("#viewReferansModal .modal-body #referansStar5").prop('checked', hpref.star5);
			if (hpref.star5 == true) {
				$("#viewReferansModal .modal-body #referansStar5icon").removeClass().addClass('bi bi-star-fill');
			}

			$("#viewReferansModal .modal-body #referansGorus").val(hpref.gorus);
			$("#viewReferansModal .modal-body #referansActive").prop('checked', hpref.active);

		});

	});
	/* view referans modal end  */
	/* edit referans modal start*/
	$("table#referansTable #referansTableBody #editBtn").click(function(e) {
		e.preventDefault();
		editReferansModalClear();
		$("#editReferansModal .modal-title").text("Referans Bilgilerini Güncelle");
		var href = $(this).attr('href');
		$.get(href, function(hpref, status) {
			$('#editReferansModal .modal-body form').attr('action', '/api/v1/update-homepage-referans-settings/' + hpref.id);
			$("#editReferansModal .modal-body #refaransFormImage").val('');
			$("#editReferansModal .modal-body #referansResim").attr('src', 'data:image/*;base64,' + hpref.stringResim);
			$("#editReferansModal .modal-body #referansId").val(hpref.id);
			$("#editReferansModal .modal-body #referansName").val(hpref.name);
			$("#editReferansModal .modal-body #referansMeslek").val(hpref.meslek);

			if (hpref.star1 == true) {
				$("#editReferansModal .modal-body #referansStar1").prop('checked', hpref.star1)
				.next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
			} else if (hpref.astar1 == false) {
				$("#editReferansModal .modal-body #referansStar1").prop('checked', hpref.star1);
				$("#editReferansModal .modal-body #referansStar1icon").removeClass().addClass('bi bi-star');
				$("#addReferansModal .modal-body #referansStar1").nextAll('i').removeClass().addClass('bi bi-star')
					.nextAll('input').attr('disabled', true).prop('checked', false);
			}

			if (hpref.star2 == true) {
				$("#editReferansModal .modal-body #referansStar2").prop('checked', hpref.star2).attr('disabled', false)
				.next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
			} else if (hpref.astar2 == false) {
				$("#editReferansModal .modal-body #referansStar2").prop('checked', hpref.star2);
				$("#editReferansModal .modal-body #referansStar2icon").removeClass().addClass('bi bi-star');
				$("#addReferansModal .modal-body #referansStar2").nextAll('i').removeClass().addClass('bi bi-star')
					.nextAll('input').attr('disabled', true).prop('checked', false);
			}

			if (hpref.star3 == true) {
				$("#editReferansModal .modal-body #referansStar3").prop('checked', hpref.star3).attr('disabled', false)
				.next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
			} else if (hpref.astar3 == false) {
				$("#editReferansModal .modal-body #referansStar3").prop('checked', hpref.star3);
				$("#editReferansModal .modal-body #referansStar3icon").removeClass().addClass('bi bi-star');
				$("#addReferansModal .modal-body #referansStar3").nextAll('i').removeClass().addClass('bi bi-star')
					.nextAll('input').attr('disabled', true).prop('checked', false);
			}
			
			if (hpref.star4 == true) {
				$("#editReferansModal .modal-body #referansStar4").prop('checked', hpref.star4).attr('disabled', false)
				.next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
			} else if (hpref.astar4 == false) {
				$("#editReferansModal .modal-body #referansStar4").prop('checked', hpref.star4);
				$("#editReferansModal .modal-body #referansStar4icon").removeClass().addClass('bi bi-star');
				$("#addReferansModal .modal-body #referansStar4").nextAll('i').removeClass().addClass('bi bi-star')
					.nextAll('input').attr('disabled', true).prop('checked', false);
			}
			
			if (hpref.star5 == true) {
				$("#editReferansModal .modal-body #referansStar5").prop('checked', hpref.star5).attr('disabled', false)
				.next('i').removeClass().addClass('bi bi-star-fill').next('input').attr('disabled', false);
			} else if (hpref.astar5 == false) {
				$("#editReferansModal .modal-body #referansStar5").prop('checked', hpref.star5);
				$("#editReferansModal .modal-body #referansStar5icon").removeClass().addClass('bi bi-star');
				$("#addReferansModal .modal-body #referansStar5").nextAll('i').removeClass().addClass('bi bi-star')
					.nextAll('input').attr('disabled', true).prop('checked', false);
			}

			$("#editReferansModal .modal-body #referansGorus").val(hpref.gorus);
			$("#editReferansModal .modal-body #referansActive").prop('checked', hpref.active);
			$("#editReferansModal .modal-body #referansGorus").val(hpref.gorus);
		});

	});
	/* edit referans modal end  */

	/* delete referans modal sart */
	$('table#referansTable #referansTableBody #deleteBtn').click(function(e) {
		e.preventDefault();
		$("#deleteReferansModal .modal-title").text("Referans kaydı silinecek. Emin misin ?");
		var href = $(this).attr('href');
		//$('#deleteReferansModal #deleteConfirmBtn').attr('href', href);
		$.get(href, function(hpref, status) {
			$('#deleteReferansModal .modal-footer #deleteConfirmBtn').attr('href', '/api/v1/delete-homepage-referans-settings/' + hpref.id);
			$("#deleteReferansModal .modal-body #refaransFormImage").val('');
			$("#deleteReferansModal .modal-body #referansResim").attr('src', 'data:image/*;base64,' + hpref.stringResim);
			$("#deleteReferansModal .modal-body #referansId").val(hpref.id);
			$("#deleteReferansModal .modal-body #referansName").val(hpref.name);
			$("#deleteReferansModal .modal-body #referansMeslek").val(hpref.meslek);
			$("#deleteReferansModal .modal-body #referansStar1").prop('checked', hpref.star1);
			if (hpref.star1 == true) {
				$("#deleteReferansModal .modal-body #referansStar1icon").removeClass('bi bi-star').addClass('bi bi-star-fill');
			}
			$("#deleteReferansModal .modal-body #referansStar2").prop('checked', hpref.star2);
			if (hpref.star2 == true) {
				$("#deleteReferansModal .modal-body #referansStar2icon").removeClass('bi bi-star').addClass('bi bi-star-fill');
			}
			$("#deleteReferansModal .modal-body #referansStar3").prop('checked', hpref.star3);
			if (hpref.star3 == true) {
				$("#deleteReferansModal .modal-body #referansStar3icon").removeClass('bi bi-star').addClass('bi bi-star-fill');
			}
			$("#deleteReferansModal .modal-body #referansStar4").prop('checked', hpref.star4);
			if (hpref.star4 == true) {
				$("#deleteReferansModal .modal-body #referansStar4icon").removeClass('bi bi-star').addClass('bi bi-star-fill');
			}
			$("#deleteReferansModal .modal-body #referansStar5").prop('checked', hpref.star5);
			if (hpref.star5 == true) {
				$("#deleteReferansModal .modal-body #referansStar5icon").removeClass('bi bi-star').addClass('bi bi-star-fill');
			}
			$("#deleteReferansModal .modal-body #referansGorus").val(hpref.gorus);
			$("#deleteReferansModal .modal-body #referansActive").prop('checked', hpref.active);
			$("#deleteReferansModal .modal-body #referansGorus").val(hpref.gorus);
		});


	});
	/*  delete referans modal end */



	/* addReferansModal form change img satart */
	$("#addReferansModal .modal-body form#addReferansForm #refaransFormImage").on('change', function() {
		readURLAddReferans(this);
	});
	/* addReferansModal form change img end */
	/* editReferansModal form change img satart */
	$("#editReferansModal .modal-body #refaransFormImage").on('change', function() {
		readURLEditReferans(this);
	});
	/* editReferansModal form change img end */
});
/* addReferansModal form change img satart */
function readURLAddReferans(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#addReferansModal .modal-body form#addReferansForm img#referansResim").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* addReferansModal change img satart */

/* editReferansModal form change img satart */
function readURLEditReferans(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#editReferansModal .modal-body img#referansResim").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* editReferansModal change img satart */
/* edit refarans modal clear */
function editReferansModalClear() {
	$("#editReferansModal .modal-body #refaransFormImage").val('');
	$("#editReferansModal .modal-body #referansResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
	//$("#editReferansModal .modal-body #referansId").val("");
	$("#editReferansModal .modal-body #referansName").val("");
	$("#editReferansModal .modal-body #referansMeslek").val("");
	$("#editReferansModal .modal-body #referansStar1").prop('checked', false);
	$("#editReferansModal .modal-body #referansStar1icon").removeClass().addClass('bi bi-star');

	$("#editReferansModal .modal-body #referansStar1").nextAll('i').removeClass().addClass('bi bi-star')
		.nextAll('input').attr('disabled', true).prop('checked', false);

	$("#editReferansModal .modal-body #referansGorus").val("");
	$("#editReferansModal .modal-body #referansActive").prop('checked', false);
}
/* edit refarans modal clear */
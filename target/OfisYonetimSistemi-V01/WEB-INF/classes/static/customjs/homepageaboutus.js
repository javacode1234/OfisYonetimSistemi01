$("document").ready(function() {

	/* view modal start*/
	$("table#aboutUsTable #aboutUsTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewAboutUsModal .modal-title").text("Hakkımızda kısmı madde bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(about, status) {
			$("#viewAboutUsModal .modal-body #hpAboutUsId").val(about.id);
			$("#viewAboutUsModal .modal-body #hpAboutUsText").val(about.text);
			$("#viewAboutUsModal .modal-body #hpAboutUsSmmmOfisId").val(about.smmmofis_id);
			$("#viewAboutUsModal .modal-body #hpAboutUsActive").prop('checked', about.active);
		});

	});
	/* view modal end  */
	
	/* edit modal start*/
		$("table#aboutUsTable #aboutUsTableBody #editBtn").click(function(e) {
			e.preventDefault();
			$("#editAboutUsModal .modal-title").text("Hakkımızda kısmı madde bilgileri güncelle");
			var href = $(this).attr('href');
			$.get(href, function(about, status) {
				//$("#editAboutUsModal .modal-body #editAboutUsForm").attr('action','/api/v1/update-homepage-aboutus-col-one-item/'+about.id);
				$("#editAboutUsModal .modal-body #hpAboutUsId").val(about.id);
				$("#editAboutUsModal .modal-body #hpAboutUsText").val(about.text);
				$("#editAboutUsModal .modal-body #hpAboutUsSmmmOfisId").val(about.smmmofis_id);
				$("#editAboutUsModal .modal-body #hpAboutUsActive").prop('checked', about.active);
			});

		});
		/* edit modal end  */
		
		/* editAboutUsFormClearBtn clear btn */
		$("#editAboutUsModal .modal-body form #editAboutUsFormClearBtn").click(function(e) {
				e.preventDefault();
				$("#editAboutUsModal .modal-body #hpAboutUsText").val("");
				$("#editAboutUsModal .modal-body #hpAboutUsActive").prop('checked', false);
		});
		/* edit AboutUsFormClearBtn clear btn */
		
		/* delete aboutus */
		$("table#aboutUsTable #aboutUsTableBody #deleteBtn").click(function(e) {
					e.preventDefault();
					$("#deleteAboutUsModal .modal-title").text("Bu madde kaydı silinecek.Emin misin ?");
					var href = $(this).attr('href');
					$("#deleteAboutUsConfirmBtn").attr('href', href);
					
					var id = $(this).closest('tr').find('td').eq(0).text();
					$("#deleteAboutUsModal #deleteAboutUsForm #hpAboutUsId").val(id);
					
					var text = $(this).closest('tr').find('td').eq(1).text();
					$("#deleteAboutUsModal #deleteAboutUsForm #hpAboutUsText").val(text);
					
					var active = $(this).closest('tr').find('td:eq(2) span').text();
					if(active==="Aktif"){
						$('#deleteAboutUsModal #hpAboutUsActive').prop('checked', true);
					}else if(active==="Pasif"){
						$('#deleteAboutUsModal #hpAboutUsActive').prop('checked', false);
					}
					
					var smmmOfisId = $(this).closest('tr').find('td').eq(3).text();
					$("#deleteAboutUsModal #deleteAboutUsForm #hpAboutUsSmmmOfisId").val(smmmOfisId);
							
					
					
		});
		/* delete aboutus */
});
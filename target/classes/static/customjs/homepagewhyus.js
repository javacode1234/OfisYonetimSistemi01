$("document").ready(function() {

	/* view modal start*/
	$("table#whyUsTable #whyUsTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewWhyUsModal .modal-title").text("Neden Biz kısmı madde bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(w, status) {
			$("#viewWhyUsModal .modal-body #hpWhyUsId").val(w.id);
			$("#viewWhyUsModal .modal-body #hpWhyUsHeader").val(w.header);
			$("#viewWhyUsModal .modal-body #hpWhyUsText").val(w.text);
			$("#viewWhyUsModal .modal-body #hpWhyUsActive").prop('checked', w.active);
			$("#viewWhyUsModal .modal-body #hpWhyUsSmmmOfisId").val(w.smmmofis_id);
			
		});

	});
	/* view modal end  */
	
	/* edit modal start*/
		$("table#whyUsTable #whyUsTableBody #editBtn").click(function(e) {
			e.preventDefault();
			$("#editWhyUsModal .modal-title").text("Hakkımızda kısmı madde bilgileri güncelle");
			var href = $(this).attr('href');
			$.get(href, function(w, status) {
				$("#editWhyUsModal .modal-body #hpWhyUsId").val(w.id);
				$("#editWhyUsModal .modal-body #hpWhyUsHeader").val(w.header);
				$("#editWhyUsModal .modal-body #hpWhyUsText").val(w.text);
				$("#editWhyUsModal .modal-body #hpWhyUsActive").prop('checked', w.active);
				$("#editWhyUsModal .modal-body #hpWhyUsSmmmOfisId").val(w.smmmofis_id);
			});

		});
		/* edit modal end  */
		
		/* editWhyUsFormClearBtn clear btn */
		$("#editWhyUsModal .modal-body form #editWhyUsFormClearBtn").click(function(e) {
			e.preventDefault();
			$("#editWhyUsModal .modal-body #hpWhyUsHeader").val("");
			$("#editWhyUsModal .modal-body #hpWhyUsText").val("");
			$("#editWhyUsModal .modal-body #hpWhyUsActive").prop('checked', false);
		});
		/* edit WhyUsFormClearBtn clear btn */
		
		/* delete whyus */
		$("table#whyUsTable #whyUsTableBody #deleteBtn").click(function(e) {
			e.preventDefault();
			$("#deleteWhyUsModal .modal-title").text("Bu madde kaydı silinecek.Emin misin ?");
			var href = $(this).attr('href');
			$("#deleteWhyUsConfirmBtn").attr('href', href);
	
			var id = $(this).closest('tr').find('td').eq(0).text();
			$("#deleteWhyUsModal #deleteWhyUsForm #hpWhyUsId").val(id);
	
			var header = $(this).closest('tr').find('td').eq(1).text();
			$("#deleteWhyUsModal #deleteWhyUsForm #hpWhyUsHeader").val(header);
						
			var text = $(this).closest('tr').find('td').eq(2).text();
			$("#deleteWhyUsModal #deleteWhyUsForm #hpWhyUsText").val(text);
	
			var active = $(this).closest('tr').find('td:eq(3) span').text();
			if (active === "Aktif") {
				$('#deleteWhyUsModal #hpWhyUsActive').prop('checked', true);
			} else if (active === "Pasif") {
				$('#deleteWhyUsModal #hpWhyUsActive').prop('checked', false);
			}
	
			var smmmOfisId = $(this).closest('tr').find('td').eq(4).text();
			$("#deleteWhyUsModal #deleteWhyUsForm #hpWhyUsSmmmOfisId").val(smmmOfisId);	
	
		});
		/* delete whyus */
});
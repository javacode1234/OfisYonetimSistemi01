$("document").ready(function() {

	/* view modal start*/
	$("table#servicesTable #servicesTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewServiceModal .modal-title").text("Hizmetler kısmı kart bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(s, status) {
			$("#viewServiceModal .modal-body #hpServiceId").val(s.id);
			$("#viewServiceModal .modal-body #hpServiceIcon").val(s.icon);
			$("#viewServiceModal .modal-body #hpServiceHeader").val(s.header);
			$("#viewServiceModal .modal-body #hpServiceText").val(s.description);
			$("#viewServiceModal .modal-body #hpServiceActive").prop('checked', s.active);
			$("#viewServiceModal .modal-body #hpServiceSmmmOfisId").val(s.smmmofis_id);
			
		});

	});
	/* view modal end  */
	
	/* edit modal start*/
		$("table#servicesTable #servicesTableBody #editBtn").click(function(e) {
			e.preventDefault();
			$("#editServiceModal .modal-title").text("Hizmetler kısmı kart bilgileri güncelle");
			var href = $(this).attr('href');
			$.get(href, function(s, status) {
				$("#editServiceModal .modal-body #hpServiceId").val(s.id);
				$("#editServiceModal .modal-body #hpServiceIcon").val(s.icon);
				$("#editServiceModal .modal-body #hpServiceHeader").val(s.header);
				$("#editServiceModal .modal-body #hpServiceText").val(s.description);
				$("#editServiceModal .modal-body #hpServiceActive").prop('checked', s.active);
				$("#editServiceModal .modal-body #hpServiceSmmmOfisId").val(s.smmmofis_id);
			});

		});
		/* edit modal end  */
		
		/* editServiceFormClearBtn clear btn */
		$("#editServiceModal .modal-body form #editServiceFormClearBtn").click(function(e) {
			e.preventDefault();
			$("#editServiceModal .modal-body #hpServiceIcon").val("");
			$("#editServiceModal .modal-body #hpServiceHeader").val("");
			$("#editServiceModal .modal-body #hpServiceText").val("");
			$("#editServiceModal .modal-body #hpServiceActive").prop('checked', false);
		});
		/* edit ServiceFormClearBtn clear btn */
		
		/* delete service */
		$("table#servicesTable #servicesTableBody #deleteBtn").click(function(e) {
			e.preventDefault();
			$("#deleteServiceModal .modal-title").text("Bu kart silinecek.Emin misin ?");
			var href = $(this).attr('href');
			$("#deleteServiceModal .modal-footer #deleteServiceConfirmBtn").attr("href",href);
				
			var id = $(this).closest('tr').find('td:eq(0)').text();
			$("#deleteServiceModal .modal-body #hpServiceId").val(id);
			var icon = $(this).closest('tr').find('td:eq(1) p').text();
			$("#deleteServiceModal .modal-body #hpServiceIcon").val(icon);
			var header = $(this).closest('tr').find('td:eq(2)').text();
			$("#deleteServiceModal .modal-body #hpServiceHeader").val(header);
			var description = $(this).closest('tr').find('td:eq(3)').text();
			$("#deleteServiceModal .modal-body #hpServiceText").val(description);
			var active = $(this).closest('tr').find('td:eq(4) span').text();
			if (active === "Aktif") {
				$('#deleteServiceModal #hpServiceActive').prop('checked', true);
			} else if (active === "Pasif") {
				$('#deleteServiceModal #hpServiceActive').prop('checked', false);
			}
			
			var smmmOfisId = $(this).closest('tr').find('td:eq(5)').text();
			$("#deleteServiceModal .modal-body #hpServiceSmmmOfisId").val(smmmOfisId);
				
				
	
		});
		/* delete service */
});
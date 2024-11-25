$("document").ready(function() {
	
	/* add Modal clear form */
	$("#addServiceBtn ").click(function(e) {
		e.preventDefault();
		$("#addServiceModal .modal-body #hpServiceIcon").val("");
		$('#addServiceModal #addServiceForm #addServiceDisplayIcon').attr('class', '');
		$("#addServiceModal .modal-body #hpServiceName").val("");
		$("#addServiceModal .modal-body #hpServiceHeader").val("");
		$("#addServiceModal .modal-body #hpServiceText").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsMainHeader").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsMainParagraf").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsSubHeader").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsSubParagraf").val("");
		$("#addServiceModal .modal-body #hpServiceActive").prop('checked', false);
	});
	$("#addServiceFormClearBtn ").click(function(e) {
		e.preventDefault();
		clearForm(); $("#addServiceModal .modal-body #hpServiceIcon").val("");
		$('#addServiceModal #addServiceForm #addServiceDisplayIcon').attr('class', '');
		$("#addServiceModal .modal-body #hpServiceName").val("");
		$("#addServiceModal .modal-body #hpServiceHeader").val("");
		$("#addServiceModal .modal-body #hpServiceText").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsMainHeader").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsMainParagraf").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsSubHeader").val("");
		$("#addServiceModal .modal-body #hpServiceDetailsSubParagraf").val("");
		$("#addServiceModal .modal-body #hpServiceActive").prop('checked', false);
	});
	/* add Modal clear form */
	
	/* view modal start*/
	$("table#servicesTable #servicesTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewServiceModal .modal-title").text("Hizmetler kısmı kart bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(s, status) {
			$("#viewServiceModal .modal-body #hpServiceId").val(s.id);
			$("#viewServiceModal .modal-body #hpServiceIcon").val(s.icon);
			$('#viewServiceModal .modal-body #viewServiceDisplayIcon').attr('class', s.icon);
			$("#viewServiceModal .modal-body #hpServiceName").val(s.name);
			$("#viewServiceModal .modal-body #hpServiceHeader").val(s.header);
			$("#viewServiceModal .modal-body #hpServiceText").val(s.description);
			$("#viewServiceModal .modal-body #hpServiceDetailsMainHeader").val(s.detailsmainheader);
			$("#viewServiceModal .modal-body #hpServiceDetailsMainParagraf").val(s.detailsmainparagraf);
			$("#viewServiceModal .modal-body #hpServiceDetailsSubHeader").val(s.detailssubheader);
			$("#viewServiceModal .modal-body #hpServiceDetailsSubParagraf").val(s.detailssubparagraf);
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
				$('#editServiceModal .modal-body #editServiceDisplayIcon').attr('class', s.icon);
				$("#editServiceModal .modal-body #hpServiceName").val(s.name);
				$("#editServiceModal .modal-body #hpServiceHeader").val(s.header);
				$("#editServiceModal .modal-body #hpServiceText").val(s.description);
				$("#editServiceModal .modal-body #hpServiceDetailsMainHeader").val(s.detailsmainheader);
				$("#editServiceModal .modal-body #hpServiceDetailsMainParagraf").val(s.detailsmainparagraf);
				$("#editServiceModal .modal-body #hpServiceDetailsSubHeader").val(s.detailssubheader);
				$("#editServiceModal .modal-body #hpServiceDetailsSubParagraf").val(s.detailssubparagraf);
				$("#editServiceModal .modal-body #hpServiceActive").prop('checked', s.active);
				$("#editServiceModal .modal-body #hpServiceSmmmOfisId").val(s.smmmofis_id);
			});

		});
		/* edit modal end  */
		
		/* editServiceFormClearBtn clear btn */
		$("#editServiceModal .modal-body form #editServiceFormClearBtn").click(function(e) {
			e.preventDefault();
			$("#editServiceModal .modal-body #hpServiceIcon").val("");
			$('#editServiceModal #editServiceForm #editServiceDisplayIcon').attr('class', '');
			$("#editServiceModal .modal-body #hpServiceName").val("");
			$("#editServiceModal .modal-body #hpServiceHeader").val("");
			$("#editServiceModal .modal-body #hpServiceText").val("");
			$("#editServiceModal .modal-body #hpServiceDetailsMainHeader").val("");
			$("#editServiceModal .modal-body #hpServiceDetailsMainParagraf").val("");
			$("#editServiceModal .modal-body #hpServiceDetailsSubHeader").val("");
			$("#editServiceModal .modal-body #hpServiceDetailsSubParagraf").val("");
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
			$('#deleteServiceModal .modal-body #deleteServiceDisplayIcon').attr('class', icon);
			var name = $(this).closest('tr').find('td:eq(2)').text();
			$("#deleteServiceModal .modal-body #hpServiceName").val(name);
			var header = $(this).closest('tr').find('td:eq(3)').text();
			$("#deleteServiceModal .modal-body #hpServiceHeader").val(header);
			var description = $(this).closest('tr').find('td:eq(4)').text();
			$("#deleteServiceModal .modal-body #hpServiceText").val(description);
			var detailsMainHeader = $(this).closest('tr').find('td:eq(5)').text();
			$("#deleteServiceModal .modal-body #hpServiceDetailsMainHeader").val(detailsMainHeader);
			var detailsMainParagraf = $(this).closest('tr').find('td:eq(6)').text();
			$("#deleteServiceModal .modal-body #hpServiceDetailsMainParagraf").val(detailsMainParagraf);
			var detailsSubHeader = $(this).closest('tr').find('td:eq(7)').text();
			$("#deleteServiceModal .modal-body #hpServiceDetailsSubHeader").val(detailsSubHeader);
			var detailsSuParagraf = $(this).closest('tr').find('td:eq(8)').text();
			$("#deleteServiceModal .modal-body #hpServiceDetailsSubParagraf").val(detailsSuParagraf);
			var active = $(this).closest('tr').find('td:eq(9) span').text();
			if (active === "Aktif") {
				$('#deleteServiceModal #hpServiceActive').prop('checked', true);
			} else if (active === "Pasif") {
				$('#deleteServiceModal #hpServiceActive').prop('checked', false);
			}
			
			var smmmOfisId = $(this).closest('tr').find('td:eq(10)').text();
			$("#deleteServiceModal .modal-body #hpServiceSmmmOfisId").val(smmmOfisId);
				
				
	
		});
		/* delete service */
});

function clearForm(){
	$("#addServiceModal .modal-body #hpServiceIcon").val("");
	$('#addServiceModal #addServiceForm #addServiceDisplayIcon').attr('class', '');
	$("#addServiceModal .modal-body #hpServiceName").val("");
	$("#addServiceModal .modal-body #hpServiceHeader").val("");
	$("#addServiceModal .modal-body #hpServiceText").val("");
	$("#addServiceModal .modal-body #hpServiceDetailsMainHeader").val("");
	$("#addServiceModal .modal-body #hpServiceDetailsMainParagraf").val("");
	$("#addServiceModal .modal-body #hpServiceDetailsSubHeader").val("");
	$("#addServiceModal .modal-body #hpServiceDetailsSubParagraf").val("");
	$("#addServiceModal .modal-body #hpServiceActive").prop('checked', false);
}
$("document").ready(function() {

	/* view modal start*/
	$("table#sectorTable #sectorTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewSectorModal .modal-title").text("Sektör Bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(hpSector, status) {
			$("#viewSectorModal .modal-body #hpSectorId").val(hpSector.id);
			$("#viewSectorModal .modal-body #hpSectorName").val(hpSector.name);
			$("#viewSectorModal .modal-body #hpSectorDescription").val(hpSector.description);
			$("#viewSectorModal .modal-body #hpSectorSmmmOfisId").val(hpSector.smmmofis_id);
		});

	});
	/* view modal end  */
	/* edit modal start*/
	$("table#sectorTable #sectorTableBody #editBtn").click(function(e) {
		e.preventDefault();
		$("#editSectorModal .modal-title").text("Sektör Bilgileri Güncelle");
		var href = $(this).attr('href');
		$.get(href, function(hpSector, status) {
			$("#editSectorModal .modal-body #hpSectorId").val(hpSector.id);
			$("#editSectorModal .modal-body #hpSectorName").val(hpSector.name);
			$("#editSectorModal .modal-body #hpSectorDescription").val(hpSector.description);
			$("#editSectorModal .modal-body #hpSectorSmmmOfisId").val(hpSector.smmmofis_id);
		});

	});
	/* edit modal end  */

	/* editSectorform clear */
	$('#editSectorModal #editSectorForm #editSectorFormClearBtn').click(function(e) {
		e.preventDefault();
		$("#editSectorModal .modal-body #hpSectorName").val("");
		$("#editSectorModal .modal-body #hpSectorDescription").val("");
	});
	/* editSectorform clear */

	/* sector delete modal sart */
	$('table#sectorTable #sectorTableBody #deleteBtn').click(function(e) {
		e.preventDefault();
		$("#deleteSectorModal .modal-title").text("Sektör Sil");
		var href = $(this).attr('href');
		$('#deleteSectorModal #deleteSectorConfirmBtn').attr('href', href);
			var id = $(this).closest('tr').find('td:eq(0)').text();
			$("#deleteSectorModal .modal-body #hpSectorId").val(id);
			var name = $(this).closest('tr').find('td:eq(1)').text();
			$("#deleteSectorModal .modal-body #hpSectorName").val(name);
			var description = $(this).closest('tr').find('td:eq(2)').text();
			$("#deleteSectorModal .modal-body #hpSectorDescription").val(description);
			var smmmofis_id = $(this).closest('tr').find('td:eq(3)').text();
			$("#deleteSectorModal .modal-body #hpSectorSmmmOfisId").val(smmmofis_id);
			
	})
	/* clieant delete modal end */




});



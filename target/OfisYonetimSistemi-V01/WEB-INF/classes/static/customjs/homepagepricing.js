$("document").ready(function() {

	/* add Modal clear form */
	$("#addPricingBtn ").click(function(e) {
		e.preventDefault();
		$("#addPricingModal .modal-body #hpPricingId").val("");
		$("#addPricingModal .modal-body #hpPricingName").val("");
		$("#addPricingModal .modal-body #hpPrice").val("");
		$("#addPricingModal .modal-body #hpPricingDescription").val("");
		$("#addPricingModal .modal-body #hpPricingActive").prop('checked', false);
	});
	$("#addPricingFormClearBtn ").click(function(e) {
		e.preventDefault();		
		$("#addPricingModal .modal-body #hpPricingName").val("");
		$("#addPricingModal .modal-body #hpPrice").val("");
		$("#addPricingModal .modal-body #hpPricingDescription").val("");
		$("#addPricingModal .modal-body #hpPricingActive").prop('checked', false);
	});
	/* add Modal clear form */
	/* edit Modal clear form */
	$("#editPricingFormClearBtn ").click(function(e) {
		e.preventDefault();
		$("#editPricingModal .modal-body #hpPricingName").val("");
		$("#editPricingModal .modal-body #hpPrice").val("");
		$("#editPricingModal .modal-body #hpPricingDescription").val("");
		$("#editPricingModal .modal-body #hpPricingActive").prop('checked', false);
	});
	/* edit Modal clear form */
	/* view modal start*/
	$("table#pricingTable #pricingTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewPricingModal .modal-title").text("Ücretlendirme kısmı kart bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(p, status) {
			$("#viewPricingModal .modal-body #hpPricingId").val(p.id);
			$("#viewPricingModal .modal-body #hpPricingName").val(p.name);
			$("#viewPricingModal .modal-body #hpPrice").val(p.price);
			$("#viewPricingModal .modal-body #hpPricingDescription").val(p.description);
			$("#viewPricingModal .modal-body #hpPricingActive").prop('checked', p.active);
			$("#viewPricingModal .modal-body #smmmOfisId").val(p.smmmofis_id);
		});

	});
	/* view modal end  */

	/* edit modal start*/
	$("table#pricingTable #pricingTableBody #editBtn").click(function(e) {
		e.preventDefault();
		$("#editPricingModal .modal-title").text("Ücretlendirme kısmı kart bilgileri güncelle");
		var href = $(this).attr('href');
		$.get(href, function(p, status) {
			$("#editPricingModal .modal-body #hpPricingId").val(p.id);
			$("#editPricingModal .modal-body #hpPricingName").val(p.name);
			$("#editPricingModal .modal-body #hpPrice").val(p.price);
			$("#editPricingModal .modal-body #hpPricingDescription").val(p.description);
			$("#editPricingModal .modal-body #hpPricingActive").prop('checked', p.active);
			$("#editPricingModal .modal-body #smmmOfisId").val(p.smmmofis_id);
		});

	});
	/* edit modal end  */
	/* delete pricing */
	$("table#pricingTable #pricingTableBody #deleteBtn").click(function(e) {
		e.preventDefault();
		$("#deletePricingModal .modal-title").text("Bu kart silinecek.Emin misin ?");
		var href = $(this).attr('href');
		$("#deletePricingModal .modal-footer #deletePricingConfirmBtn").attr("href", href);

		var id = $(this).closest('tr').find('td:eq(0)').text();
		$("#deletePricingModal .modal-body #hpPricingId").val(id);
		var name = $(this).closest('tr').find('td:eq(1)').text();
		$("#deletePricingModal .modal-body #hpPricingName").val(name);
		var price = $(this).closest('tr').find('td:eq(2)').text();
		$("#deletePricingModal .modal-body #hpPrice").val(price);
		var description = $(this).closest('tr').find('td:eq(3) span').text();
		$("#deletePricingModal .modal-body #hpPricingDescription").val(description);
		
		var active = $(this).closest('tr').find('td:eq(4) span').text();
		if(active==='Aktif'){
			$("#deletePricingModal .modal-body #hpPricingActive").prop('checked',true);
		}else if(active==='Pasif'){
			$("#deletePricingModal .modal-body #hpPricingActive").prop('checked',false);
		}
		
		var smmmOfisId = $(this).closest('tr').find('td:eq(5)').text();
		$("#deletePricingModal .modal-body #smmmOfisId").val(smmmOfisId);



	});
	/* delete pricing */
});


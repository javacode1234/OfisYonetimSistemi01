$("document").ready(function() {

	/* add Modal clear form */
	$("#addPricingItemBtn ").click(function(e) {
		e.preventDefault();
		$("#addPricingItemModal .modal-body #hpPricingItemId").val("");
		$("#addPricingItemModal .modal-body #hpPricingItemName").val("");
		$("#addPricingItemModal .modal-body #hpPricingItemDescription").val("");
		$("#addPricingItemModal .modal-body #hpPricingItemActive").prop('checked', false);
	});
	$("#addPricingItemFormClearBtn ").click(function(e) {
		e.preventDefault();		
		$("#addPricingItemModal .modal-body #hpPricingItemName").val("");
		$("#addPricingItemModal .modal-body #hpPricingItemDescription").val("");
		$("#addPricingItemModal .modal-body #hpPricingItemActive").prop('checked', false);
	});
	/* add Modal clear form */
	/* edit Modal clear form */
	$("#editPricingFormClearBtn ").click(function(e) {
		e.preventDefault();
		$("#editPricingItemModal .modal-body #hpPricingItemName").val("");
		$("#editPricingItemModal .modal-body #hpPricingItemDescription").val("");
		$("#editPricingItemModal .modal-body #hpPricingItemActive").prop('checked', false);
	});
	/* edit Modal clear form */
	/* view modal start*/
	$("table#pricingItemTable #pricingItemTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewPricingItemModal .modal-title").text("Ücretlendirme kısmı madde bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(p, status) {
			$("#viewPricingItemModal .modal-body #hpPricingItemId").val(p.id);
			$("#viewPricingItemModal .modal-body #hpPricingItemName").val(p.name);
			$("#viewPricingItemModal .modal-body #hpPricingItemDescription").val(p.description);
			$("#viewPricingItemModal .modal-body #hpPricingItemActive").prop('checked', p.active);
			$("#viewPricingItemModal .modal-body #pricingItemId").val(p.smmmofispricing_id);
		});

	});
	/* view modal end  */

	/* edit modal start*/
	$("table#pricingItemTable #pricingItemTableBody #editBtn").click(function(e) {
		e.preventDefault();
		$("#editPricingItemModal .modal-title").text("Ücretlendirme kısmı madde bilgileri güncelle");
		var href = $(this).attr('href');
		$.get(href, function(p, status) {
			$("#editPricingItemModal .modal-body #hpPricingItemId").val(p.id);
			$("#editPricingItemModal .modal-body #hpPricingItemName").val(p.name);
			$("#editPricingItemModal .modal-body #hpPricingItemDescription").val(p.description);
			$("#editPricingItemModal .modal-body #hpPricingItemActive").prop('checked', p.active);
			$("#editPricingItemModal .modal-body #pricingItemId").val(p.smmmofispricing_id);
		});

	});
	/* edit modal end  */
	/* delete pricing */
	$("table#pricingItemTable #pricingItemTableBody #deleteBtn").click(function(e) {
		e.preventDefault();
		$("#deletePricingItemModal .modal-title").text("Bu madde silinecek.Emin misin ?");
		var href = $(this).attr('href');
		$("#deletePricingItemModal .modal-footer #deletePricingItemConfirmBtn").attr("href", href);

		var id = $(this).closest('tr').find('td:eq(0)').text();
		$("#deletePricingItemModal .modal-body #hpPricingItemId").val(id);
		var name = $(this).closest('tr').find('td:eq(1)').text();
		$("#deletePricingItemModal .modal-body #hpPricingItemName").val(name);
		
		var description = $(this).closest('tr').find('td:eq(2) span').text();
		$("#deletePricingItemModal .modal-body #hpPricingItemDescription").val(description);
		
		var active = $(this).closest('tr').find('td:eq(3) span').text();
		if(active==='Aktif'){
			$("#deletePricingItemModal .modal-body #hpPricingItemActive").prop('checked',true);
		}else if(active==='Pasif'){
			$("#deletePricingItemModal .modal-body #hpPricingItemActive").prop('checked',false);
		}
		
		var pricingItemId = $(this).closest('tr').find('td:eq(4)').text();
		$("#deletePricingModal .modal-body #pricingItemId").val(pricingItemId);



	});
	/* delete pricing */
});


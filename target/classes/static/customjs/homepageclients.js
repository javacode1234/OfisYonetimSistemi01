$("document").ready(function() {

	/* editClientform clear */
	$('#addClientModal .modal-body #addClientFormClearBtn').click(function(e) {
		//e.preventDefault();
		$("#addClientModal .modal-body #hpcformImage").val('');
		$("#addClientModal .modal-body #hpcLogo").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#addClientModal .modal-body #hpcName").val("");
		$("#addClientModal .modal-body #hpcUrl").val("");
		$("#addClientModal .modal-body #hpcDescription").val("");
		$("#addClientModal .modal-body #hpcActive").prop('checked', false);
	});
	/* /editClientform clear */
	/* editClientform clear */
	$('#editClientModal #editClientForm #editClientFormClearBtn').click(function(e) {
		//e.preventDefault();
		$("#editClientModal .modal-body #hpcformImage").val('');
		$("#editClientModal .modal-body #hpcLogo").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#editClientModal .modal-body #hpcName").val("");
		$("#editClientModal .modal-body #hpcUrl").val("");
		$("#editClientModal .modal-body #hpcDescription").val("");
		$("#editClientModal .modal-body #hpcActive").prop('checked', false);
	});
	/* /editClientform clear */

	/* view modal start*/
	$("table#clientTable #clientTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewClientModal .modal-title").text("Kurum Bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(hpc, status) {
			$("#viewClientModal .modal-body #hpcLogo").attr('src', 'data:image/*;base64,' + hpc.stringLogo);
			$("#viewClientModal .modal-body #hpcId").val(hpc.id);
			$("#viewClientModal .modal-body #hpcName").val(hpc.name);
			$("#viewClientModal .modal-body #hpcUrl").val(hpc.url);
			$("#viewClientModal .modal-body #hpcDescription").val(hpc.description);
			$("#viewClientModal .modal-body #hpcActive").prop('checked', hpc.active);
			$("#viewClientModal .modal-body #hpcSmmmOfisId").val(hpc.smmmofis_id);
			
		});

	});
	/* view modal end  */
	/* edit modal start*/
	$("table#clientTable #clientTableBody #editBtn").click(function(e) {
		e.preventDefault();
		$("#editClientModal .modal-title").text("Kurum Bilgilerini Güncelle");
		var href = $(this).attr('href');
		$.get(href, function(hpc, status) {
			$("#editClientModal .modal-body #editClientForm").attr('action', '/cp/update-homepage-client-settings/' + hpc.id);
			$("#editClientModal .modal-body #hpcLogo").attr('src', 'data:image/*;base64,' + hpc.stringLogo);
			$("#editClientModal .modal-body #hpcId").val(hpc.id);
			$("#editClientModal .modal-body #hpcName").val(hpc.name);
			$("#editClientModal .modal-body #hpcUrl").val(hpc.url);
			$("#editClientModal .modal-body #hpcDescription").val(hpc.description);
			$("#editClientModal .modal-body #hpcActive").prop('checked', hpc.active);
			$("#editClientModal .modal-body #hpcSmmmOfisId").val(hpc.smmmofis_id);
		});

	});
	/* edit modal end  */

	/* client delete modal sart */
	$('table#clientTable #clientTableBody #deleteBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#deleteClientModal #deleteConfirmBtn').attr('href', href);
		$('#deleteClientModal .modal-title').text('Kurum kaydı silinecek. Emin misin ?');

		var no = $(this).closest('tr').find('td').eq(0).text();
		$('#deleteClientModal #hpcId').val(no);
		var isim = $(this).closest('tr').find('td').eq(2).text();
		$('#deleteClientModal #hpcName').val(isim);
		var url = $(this).closest('tr').find('td').eq(3).text();
		$('#deleteClientModal #hpcUrl').val(url);
		var description = $(this).closest('tr').find('td').eq(4).text();
		$('#deleteClientModal #hpcDescription').val(description);
		var active = $(this).closest('tr').find('td:eq(5) span').text();
		if (active === "Aktif") {
			$('#deleteClientModal #hpcActive').prop('checked', true);
		} else if (active === "Pasif") {
			$('#deleteClientModal #hpcActive').prop('checked', false);
		}

		var resimhref = $(this).closest('tr').find('td:eq(1) a img').attr('src');
		$('#deleteClientModal #hpcLogo').attr('src', resimhref.toString());
		var smmmofis_id=$(this).closest('tr').find('td').eq(6).text();
		$("#deleteClientModal .modal-body #hpcSmmmOfisId").val(smmmofis_id);

	})
	/* clieant delete modal end */



	/* addClientModal form change img satart */
	$("#addClientModal .modal-body form#addClientForm #hpcformImage").on('change', function() {
		readURLAddClient(this);
	});
	/* addClientModal form change img end */
	/* editClientModal form change img satart */
	$("#editClientModal .modal-body #hpcformImage").on('change', function() {
		readURLEditClient(this);
	});
	/* editClientModal form change img end */
});
/* addClientModal form change img satart */
function readURLAddClient(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#addClientModal .modal-body form#addClientForm img#hpcLogo").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* addClientModal change img satart */

/* editClientModal form change img satart */
function readURLEditClient(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#editClientModal .modal-body img#hpcLogo").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* editClientModal change img satart */
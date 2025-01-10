$("document").ready(function() {

	/* addTeamBtn click */
	$('#addTeamBtn').click(function() {
		$("#addTeamModal .modal-body #hptformImage").val('');
		$("#addTeamModal .modal-body #hptResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#addTeamModal .modal-body #hptId").val('');
		$("#addTeamModal .modal-body #hptName").val('');
		$("#addTeamModal .modal-body #hptMainHeader").val('');
		$("#addTeamModal .modal-body #hptHeader").val('');
		$("#addTeamModal .modal-body #hptText").val('');
		$("#addTeamModal .modal-body #hptXLink").val('');
		$("#addTeamModal .modal-body #hptFaceLink").val('');
		$("#addTeamModal .modal-body #hptInslink").val('');
		$("#addTeamModal .modal-body #hptLinkLink").val('');
		$("#addTeamModal .modal-body #hptDescription").val('');
		$("#addTeamModal .modal-body #hptActive").prop('checked', false);
	});
	/* / addTeamBtn click */
	/* addTeamBtn click */
	$('#addTeamFormResetBtn').click(function() {
		$("#addTeamModal .modal-body #hptformImage").val('');
		$("#addTeamModal .modal-body #hptResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		$("#addTeamModal .modal-body #hptId").val('');
		$("#addTeamModal .modal-body #hptName").val('');
		$("#addTeamModal .modal-body #hptMainHeader").val('');
		$("#addTeamModal .modal-body #hptHeader").val('');
		$("#addTeamModal .modal-body #hptText").val('');
		$("#addTeamModal .modal-body #hptXLink").val('');
		$("#addTeamModal .modal-body #hptFaceLink").val('');
		$("#addTeamModal .modal-body #hptInslink").val('');
		$("#addTeamModal .modal-body #hptLinkLink").val('');
		$("#addTeamModal .modal-body #hptDescription").val('');
		$("#addTeamModal .modal-body #hptActive").prop('checked', false);
	});
	/* / addTeamBtn click */
	/* editTeamBtn click */
	$('#editTeamModal #editTeamFormResetBtn').click(function() {
		$("#editTeamModal .modal-body #hptformImage").val('');
		$("#editTeamModal .modal-body #hptResim").attr('src', '../../niceadminpanel/assets/img/smmm.png');
		//$("#editTeamModal .modal-body #hptId").val('');
		$("#editTeamModal .modal-body #hptName").val('');
		$("#editTeamModal .modal-body #hptMainHeader").val('');
		$("#editTeamModal .modal-body #hptHeader").val('');
		$("#editTeamModal .modal-body #hptText").val('');
		$("#editTeamModal .modal-body #hptXLink").val('');
		$("#editTeamModal .modal-body #hptFaceLink").val('');
		$("#editTeamModal .modal-body #hptInslink").val('');
		$("#editTeamModal .modal-body #hptLinkLink").val('');
		$("#editTeamModal .modal-body #hptDescription").val('');
		$("#editTeamModal .modal-body #hptActive").prop('checked', false);
	});
	/* / editTeamBtn click */
	/* view modal start*/
	$("table#teamTable #teamTableBody #viewBtn").click(function(e) {
		e.preventDefault();
		$("#viewTeamModal .modal-title").text("Personel Bilgileri");
		var href = $(this).attr('href');
		$.get(href, function(hpt, status) {
			$("#viewTeamModal .modal-body #hptId").val(hpt.id);
			$("#viewTeamModal .modal-body #hptName").val(hpt.name);
			$("#viewTeamModal .modal-body #hptMainHeader").val(hpt.mainheader);
			$("#viewTeamModal .modal-body #hptHeader").val(hpt.header);
			$("#viewTeamModal .modal-body #hptText").val(hpt.text);
			$("#viewTeamModal .modal-body #hptXLink").val(hpt.xlink);
			$("#viewTeamModal .modal-body #hptFaceLink").val(hpt.facelink);
			$("#viewTeamModal .modal-body #hptInslink").val(hpt.inslink);
			$("#viewTeamModal .modal-body #hptLinkLink").val(hpt.linklink);
			$("#viewTeamModal .modal-body #hptDescription").val(hpt.description);
			$("#viewTeamModal .modal-body #hptActive").prop('checked', hpt.active);
			$("#viewTeamModal .modal-body #smmmOfisId").val(hpt.smmmofis_id);
			$("#viewTeamModal .modal-body #hptResim").attr('src', 'data:image/*;base64,' + hpt.stringResim);
		});

	});
	/* view modal end  */
	/* edit modal start*/
	$("table#teamTable #teamTableBody #editBtn").click(function(e) {
		e.preventDefault();
		$("#editTeamModal .modal-title").text("Personel Bilgilerini Güncelle");
		var href = $(this).attr('href');
		$.get(href, function(hpt, status) {
			$("#editTeamModal .modal-body #editTeamForm").attr('action', '/api/v1/update-homepage-team-settings/' + hpt.id);
			$("#editTeamModal .modal-body #hptResim").attr('src', 'data:image/*;base64,' + hpt.stringResim);
			$("#editTeamModal .modal-body #hptId").val(hpt.id);
			$("#editTeamModal .modal-body #hptName").val(hpt.name);
			$("#editTeamModal .modal-body #hptMainHeader").val(hpt.mainheader);
			$("#editTeamModal .modal-body #hptHeader").val(hpt.header);
			$("#editTeamModal .modal-body #hptText").val(hpt.text);
			$("#editTeamModal .modal-body #hptXLink").val(hpt.xlink);
			$("#editTeamModal .modal-body #hptFaceLink").val(hpt.facelink);
			$("#editTeamModal .modal-body #hptInslink").val(hpt.inslink);
			$("#editTeamModal .modal-body #hptLinkLink").val(hpt.linklink);
			$("#editTeamModal .modal-body #hptDescription").val(hpt.description);
			$("#editTeamModal .modal-body #hptActive").prop('checked', hpt.active);
			$("#editTeamModal .modal-body #smmmOfisId").val(hpt.smmmofis_id);

		});

	});
	/* edit modal end  */	
	/* team delete modal sart */
	$('table#teamTable #teamTableBody #deleteBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('#deleteTeamModal #deleteConfirmBtn').attr('href', href);
		$('#deleteTeamModal .modal-title').text('Personel kaydı silinecek. Emin misin ?');
		var id = $(this).closest('tr').find('td').eq(0).text();
		$('#deleteTeamModal #hptId').val(id);
		var resimhref = $(this).closest('tr').find('td:eq(1) a img').attr('src');
		$('#deleteTeamModal #hptResim').attr('src', resimhref.toString());
		var isim = $(this).closest('tr').find('td').eq(2).text();
		$('#deleteTeamModal #hptName').val(isim);
		var mainheader = $(this).closest('tr').find('td').eq(3).text();
		$('#deleteTeamModal #hptMainHeader').val(mainheader);
		var header = $(this).closest('tr').find('td').eq(4).text();
		$('#deleteTeamModal #hptHeader').val(header);
		var text = $(this).closest('tr').find('td').eq(5).text();
		$('#deleteTeamModal #hptText').val(text);
		var xlink = $(this).closest('tr').find('td').eq(6).text();
		$('#deleteTeamModal #hptXLink').val(xlink);
		var facelink = $(this).closest('tr').find('td').eq(7).text();
		$('#deleteTeamModal #hptFaceLink').val(facelink);
		var inslink = $(this).closest('tr').find('td').eq(8).text();
		$('#deleteTeamModal #hptInslink').val(inslink);
		var linklink = $(this).closest('tr').find('td').eq(9).text();
		$('#deleteTeamModal #hptLinkLink').val(linklink);
		var description = $(this).closest('tr').find('td').eq(10).text();
		$('#deleteTeamModal #hptDescription').val(description);
		var active = $(this).closest('tr').find('td:eq(11) span').text();
		if (active === "Aktif") {
			$('#deleteTeamModal #hptActive').prop('checked', true);
		} else if (active === "Pasif") {
			$('#deleteTeamModal #hptActive').prop('checked', false);
		}
		var smmmOfisId = $(this).closest('tr').find('td').eq(12).text();
		$('#deleteTeamModal #smmmOfisId').val(smmmOfisId);
	})
	/* clieant delete modal end */
	/* addTeamModal form change img satart */
	$("#addTeamModal form#addTeamForm #hptformImage").on('change', function() {
		readURLAddTeam(this);
	});
	/* addTeamModal form change img end */

	/* editTeamModal form change img satart */
	$("#editTeamModal form#editTeamForm #hptformImage").on('change', function() {
		readURLEditTeam(this);
	});
	/* editTeamModal form change img end */

});


/* addTeamModal form change img satart */
function readURLAddTeam(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#addTeamModal form#addTeamForm #hptResim").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* addTeamModal change img satart */

/* editTeamModal form change img satart */
function readURLEditTeam(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#editTeamModal form#editTeamForm #hptResim").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
/* editTeamModal change img satart */
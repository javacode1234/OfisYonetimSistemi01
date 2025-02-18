$("document").ready(function() {
		
	/* viewNoteBtn click start*/
	$("table#noteTable #noteTableBody #viewNoteBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(n, status) {
			$("#viewNoteModal .modal-body  #noteid").val(n.id);
			$("#viewNoteModal .modal-body  #subject").val(n.subject);
			$("#viewNoteModal .modal-body  #noteText").val(n.noteText);
			$("#viewNoteModal .modal-body  #createDate").val(n.createDate);
			$("#viewNoteModal .modal-body  #readDate").val(n.readDate);
			$("#viewNoteModal .modal-body  #okundu").prop('checked', n.okundu);
			$("#viewNoteModal .modal-body  #smmmofis_id").val(n.smmmofis_id);			
		});
	});
	/* viewNoteBtn click end  */
	
	/* edit modal start*/
		$("table#noteTable #noteTableBody #editNoteBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(n, status) {
				$("#editNoteModal .modal-body  #noteid").val(n.id);
				$("#editNoteModal .modal-body  #subject").val(n.subject);
				$("#editNoteModal .modal-body  #noteText").val(n.noteText);
				$("#editNoteModal .modal-body  #createDate").val(n.createDate);
				$("#editNoteModal .modal-body  #readDate").val(n.readDate);
				$("#editNoteModal .modal-body  #okundu").prop('checked', n.okundu);
				$("#editNoteModal .modal-body  #smmmofis_id").val(n.smmmofis_id);		
			});

		});
		/* edit modal end  */
				
		/* delete service */
		$("table#noteTable #noteTableBody #deleteNoteBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$("#deleteNoteModal .modal-footer #deleteNoteConfirmBtn").attr("href",href);
			var getNoteHref = $(this).prev().attr("href");
			$.get(getNoteHref, function(n, status) {
				$("#deleteNoteModal .modal-body  #noteid").val(n.id);
				$("#deleteNoteModal .modal-body  #subject").val(n.subject);
				$("#deleteNoteModal .modal-body  #noteText").val(n.noteText);
				$("#deleteNoteModal .modal-body  #createDate").val(n.createDate);
				$("#deleteNoteModal .modal-body  #readDate").val(n.readDate);				
				$("#deleteNoteModal .modal-body  #okundu").prop('checked', n.okundu);
				$("#deleteNoteModal .modal-body  #smmmofis_id").val(n.smmmofis_id);			
			});
				
		});
		/* delete service */
});


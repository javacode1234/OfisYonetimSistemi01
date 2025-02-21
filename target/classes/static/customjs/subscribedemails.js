$("document").ready(function() {
		
	/* viewBtn click start*/
	$("table#subscribedEmailTable #subscribedEmailTableBody #viewSubscribedEmailBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(n, status) {
			$("#viewSubscribedEmailModal .modal-body  #emailid").val(n.id);
			$("#viewSubscribedEmailModal .modal-body  #email").val(n.email);
			$("#viewSubscribedEmailModal .modal-body  #date").val(n.date);
			$("#viewSubscribedEmailModal .modal-body  #active").prop('checked', n.active);
			$("#viewSubscribedEmailModal .modal-body  #smmmofis_id").val(n.smmmofis_id);			
		});
	});
	/* viewBtn click end  */
	
	/* edit modal start*/
		$("table#subscribedEmailTable #subscribedEmailTableBody #editSubscribedEmailBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(n, status) {
				$("#editSubscribedEmailModal .modal-body  #emailid").val(n.id);
				$("#editSubscribedEmailModal .modal-body  #email").val(n.email);
				$("#editSubscribedEmailModal .modal-body  #date").val(n.date);
				$("#editSubscribedEmailModal .modal-body  #active").prop('checked', n.active);
				$("#editSubscribedEmailModal .modal-body  #smmmofis_id").val(n.smmmofis_id);
			});

		});
		/* edit modal end  */
				
		/* delete service */
		$("table#subscribedEmailTable #subscribedEmailTableBody #deleteSubscribedEmailBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$("#deleteSubscribedEmailModal .modal-footer #deleteSubscribedEmailConfirmBtn").attr("href",href);
			var getSubscribedEmailHref = $(this).prev().attr("href");
			$.get(getSubscribedEmailHref, function(n, status) {
				$("#deleteSubscribedEmailModal .modal-body  #emailid").val(n.id);
				$("#deleteSubscribedEmailModal .modal-body  #email").val(n.email);
				$("#deleteSubscribedEmailModal .modal-body  #date").val(n.date);
				$("#deleteSubscribedEmailModal .modal-body  #active").prop('checked', n.active);
				$("#deleteSubscribedEmailModal .modal-body  #smmmofis_id").val(n.smmmofis_id);
			});
				
		});
		/* delete service */
});


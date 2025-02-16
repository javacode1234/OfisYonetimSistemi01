$("document").ready(function() {
		
	/* viewMessageBtn click start*/
	$("table#messageTable #messageTableBody #viewMessageBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(message, status) {
			$("#viewMessageModal #messageInfoForm  #messageid").val(message.id);
			$("#viewMessageModal #messageInfoForm  #name").val(message.name);			
			$("#viewMessageModal #messageInfoForm  #email").val(message.email);
			$("#viewMessageModal #messageInfoForm  #subject").val(message.subject);
			$("#viewMessageModal #messageInfoForm  #message").val(message.message);
			$("#viewMessageModal #messageInfoForm  #sendDate").val(message.date);
			$("#viewMessageModal #messageInfoForm  #dateofread").val(message.dateofread);
			$("#viewMessageModal #messageInfoForm  #okundu").prop('checked', message.okundu);
			$("#viewMessageModal #messageInfoForm  #smmmofis_id").val(message.smmmofis_id);			
		});
	});
	/* viewMessageBtn click end  */
	
	/* edit modal start*/
		$("table#messageTable #messageTableBody #editMessageBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$.get(href, function(message, status) {
				$("#editMessageModal #messageEditForm  #messageid").val(message.id);
				$("#editMessageModal #messageEditForm  #name").val(message.name);
				$("#editMessageModal #messageEditForm  #email").val(message.email);
				$("#editMessageModal #messageEditForm  #subject").val(message.subject);
				$("#editMessageModal #messageEditForm  #message").val(message.message);
				$("#editMessageModal #messageEditForm  #sendDate").val(message.date);
				$("#editMessageModal #messageEditForm  #dateofread").val(message.dateofread);
				$("#editMessageModal #messageEditForm  #okundu").prop('checked', message.okundu);
				$("#editMessageModal #messageEditForm  #smmmofis_id").val(message.smmmofis_id);		
			});

		});
		/* edit modal end  */
				
		/* delete service */
		$("table#messageTable #messageTableBody #deleteMessageBtn").click(function(e) {
			e.preventDefault();
			var href = $(this).attr('href');
			$("#deleteMessageModal .modal-footer #deleteMessageConfirmBtn").attr("href",href);
			var getMessageHref = $(this).prev().attr("href");
			$.get(getMessageHref, function(message, status) {
				$("#deleteMessageModal .modal-body  #messageid").val(message.id);
				$("#deleteMessageModal .modal-body  #name").val(message.name);			
				$("#deleteMessageModal .modal-body  #email").val(message.email);
				$("#deleteMessageModal .modal-body  #subject").val(message.subject);
				$("#deleteMessageModal .modal-body  #message").val(message.message);
				$("#deleteMessageModal .modal-body  #sendDate").val(message.date);
				$("#deleteMessageModal .modal-body  #readDate").val(message.dateofread);				
				$("#deleteMessageModal .modal-body  #okundu").prop('checked', message.okundu);
				$("#deleteMessageModal .modal-body  #smmmofis_id").val(message.smmmofis_id);			
			});
					
			
				
				
	
		});
		/* delete service */
});


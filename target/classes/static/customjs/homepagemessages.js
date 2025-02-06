$("document").ready(function() {
		
	/* viewMessageBtn click start*/
	$("table#messagesTable #messagesTableBody #viewMessageBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(message, status) {
			$("#messageInfoForm  #messageid").val(message.id);
			$("#messageInfoForm  #name").val(message.name);			
			$("#messageInfoForm  #email").val(message.email);
			$("#messageInfoForm  #subject").val(message.subject);
			$("#messageInfoForm  #sendDate").val(message.date.toLocaleString());
			
			if(message.dateofread==null){
				$("#messageInfoForm  #readDate").val("");
			}else{
				$("#messageInfoForm  #readDate").val(message.dateofread.toLocaleString());
			}
			
			$("#messageInfoForm  #message").val(message.message);
			$("#messageInfoForm  #okundu").prop('checked', message.okundu);
			$("#messageInfoForm  #smmmofis_id").val(message.smmmofis_id);			
		});
	});
	/* viewMessageBtn click end  */
	
	/* edit modal start*/
/*		$("table#faqTable #faqTableBody #editBtn").click(function(e) {
			e.preventDefault();
			$("#editFaqModal .modal-title").text("Sorulan soru bilgileri güncelle");
			var href = $(this).attr('href');
			$.get(href, function(faq, status) {
				$("#editFaqModal .modal-body #hpFaqId").val(faq.id);
				$("#editFaqModal .modal-body #hpFaqQuestion").val(faq.question);
				$("#editFaqModal .modal-body #hpFaqAnswer").val(faq.answer);
				$("#editFaqModal .modal-body #hpFaqDescription").val(faq.description);
				$("#editFaqModal .modal-body #hpFaqActive").prop('checked', faq.active);
				$("#editFaqModal .modal-body #hpFaqSmmmOfisId").val(faq.smmmofis_id);
			});

		});*/
		/* edit modal end  */
		
		/* editFaqFormClearBtn clear btn */
/*		$("#editFaqModal .modal-body form #editFaqFormClearBtn").click(function(e) {
			$("#editFaqModal .modal-body #hpFaqQuestion").val("");
			$("#editFaqModal .modal-body #hpFaqAnswer").val("");
			$("#editFaqModal .modal-body #hpFaqDescription").val("");
			$("#editFaqModal .modal-body #hpFaqActive").prop('checked', false);
		});*/
		/* edit FaqFormClearBtn clear btn */
		
		/* delete service */
		$("table#messagesTable #messagesTableBody #deleteMessageBtn").click(function(e) {
			e.preventDefault();
			$("#deleteMessageModal .modal-title").text("Bu mesaj silinecek. Emin misin ?");
			var href = $(this).attr('href');
			$("#deleteMessageModal .modal-footer #deleteMessageConfirmBtn").attr("href",href);
			var getMessageHref = $(this).prev().attr("href");
			$.get(getMessageHref, function(message, status) {
				$("#deleteMessageModal .modal-body  #messageid").val(message.id);
				$("#deleteMessageModal .modal-body  #name").val(message.name);			
				$("#deleteMessageModal .modal-body  #email").val(message.email);
				$("#deleteMessageModal .modal-body  #subject").val(message.subject);
				$("#deleteMessageModal .modal-body  #sendDate").val(message.date);
				$("#deleteMessageModal .modal-body  #readDate").val(message.dateofread.toLocaleString());
				$("#deleteMessageModal .modal-body  #message").val(message.message);
				$("#deleteMessageModal .modal-body  #okundu").prop('checked', message.okundu);
				$("#deleteMessageModal .modal-body  #smmmofis_id").val(message.smmmofis_id);			
			});
					
			
				
				
	
		});
		/* delete service */
});


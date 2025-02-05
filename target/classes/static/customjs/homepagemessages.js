$("document").ready(function() {
	
	/* add Modal clear form */
/*	$("#addFaqBtn ").click(function(e) {
		e.preventDefault();
		$("#addFaqModal .modal-body #hpFaqQuestion").val('');
		$("#addFaqModal .modal-body #hpFaqAnswer").val('');
		$("#addFaqModal .modal-body #hpFaqDescription").val('');		
		$("#addFaqModal .modal-body #hpFaqActive").prop('checked', false);
	});
	$("#addFaqFormClearBtn ").click(function(e) {
		e.preventDefault();
		$("#addFaqModal .modal-body #hpFaqQuestion").val('');
		$("#addFaqModal .modal-body #hpFaqAnswer").val('');
		$("#addFaqModal .modal-body #hpFaqDescription").val('');
		$("#addFaqModal .modal-body #hpFaqActive").prop('checked', false);
	});*/
	/* add Modal clear form */
	
	/* editBtn click start*/
	$("table#messagesTable #messagesTableBody #viewMessageBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(message, status) {
			$("#messageInfoForm  #messageid").val(message.id);
			$("#messageInfoForm  #name").val(message.name);			
			$("#messageInfoForm  #email").val(message.email);
			$("#messageInfoForm  #subject").val(message.subject);
			$("#messageInfoForm  #sendDate").val(message.date);
			$("#messageInfoForm  #readDate").val(message.dateofread);
			$("#messageInfoForm  #message").val(message.message);
			$("#messageInfoForm  #okundu").prop('checked', message.okundu);
			$("#messageInfoForm  #smmmofis_id").val(message.smmmofis_id);			
		});
	});
	/* editBtn click end  */
	
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
/*		$("table#faqTable #faqTableBody #deleteBtn").click(function(e) {
			e.preventDefault();
			$("#deleteFaqModal .modal-title").text("Bu soru ve cevabı silinecek.Emin misin ?");
			var href = $(this).attr('href');
			$("#deleteFaqModal .modal-footer #deleteFaqConfirmBtn").attr("href",href);
				
			var id = $(this).closest('tr').find('td:eq(0)').text();
			$("#deleteFaqModal .modal-body #hpFaqId").val(id);
			var question = $(this).closest('tr').find('td:eq(1) span').text();
			$("#deleteFaqModal .modal-body #hpFaqQuestion").val(question);
			var answer = $(this).closest('tr').find('td:eq(2) span').text();
			$("#deleteFaqModal .modal-body #hpFaqAnswer").val(answer);
			var description = $(this).closest('tr').find('td:eq(3) span').text();
			$("#deleteFaqModal .modal-body #hpFaqDescription").val(description);
						
			var active = $(this).closest('tr').find('td:eq(4) span').text();
			if (active === "Aktif") {
				$('#deleteFaqModal #hpFaqActive').prop('checked', true);
			} else if (active === "Pasif") {
				$('#deleteFaqModal #hpFaqActive').prop('checked', false);
			}
			
			var smmmOfisId = $(this).closest('tr').find('td:eq(5)').text();
			$("#deleteFaqModal .modal-body #hpFaqSmmmOfisId").val(smmmOfisId);
				
				
	
		});*/
		/* delete service */
});


$("document").ready(function() {

	/* view modal start*/
	$("table#notificationTable #notificationTableBody #viewNotificationBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(n, status) {
			$("#viewNotificationModal .modal-body #notificationid").val(n.id);
			$("#viewNotificationModal .modal-body #notificationheader").val(n.header);
			$("#viewNotificationModal .modal-body #notification").val(n.notification);
			$("#viewNotificationModal .modal-body #information").val(n.information);
			$("#viewNotificationModal .modal-body #dateofpublish").val(n.dateofpublish);
			$("#viewNotificationModal .modal-body #dateofread").val(n.dateofread);
			$("#viewNotificationModal .modal-body #okundu").prop('checked', n.okundu);
			$("#viewNotificationModal .modal-body #smmmofis_id").val(n.smmmofis_id);

		});

	});
	/* view modal end  */

	/* edit modal start*/
	$("table#notificationTable #notificationTableBody #editNotificationBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(n, status) {
			$("#editNotificationModal .modal-body #notificationid").val(n.id);
			$("#editNotificationModal .modal-body #notificationheader").val(n.header);
			$("#editNotificationModal .modal-body #notification").val(n.notification);
			$("#editNotificationModal .modal-body #information").val(n.information);
			$("#editNotificationModal .modal-body #dateofpublish").val(n.dateofpublish);
			$("#editNotificationModal .modal-body #dateofread").val(n.dateofread);
			$("#editNotificationModal .modal-body #okundu").prop('checked', n.okundu);
			$("#editNotificationModal .modal-body #smmmofis_id").val(n.smmmofis_id);

		});

	});
	/* edit modal end  */

	

	/* delete Notification */
	$("table#notificationTable #notificationTableBody #deleteNotificationBtn").click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$("#deleteNotificationModal .modal-footer #deleteNotificationConfirmBtn").attr("href", href);
		var getNotificationHref = $(this).prev().attr("href");
		$.get(getNotificationHref, function(n, status) {
			$("#deleteNotificationModal .modal-body #notificationid").val(n.id);
			$("#deleteNotificationModal .modal-body #header").val(n.header);
			$("#deleteNotificationModal .modal-body #notification").val(n.notification);
			$("#deleteNotificationModal .modal-body #information").val(n.information);
			$("#deleteNotificationModal .modal-body #dateofpublish").val(n.dateofpublish);
			$("#deleteNotificationModal .modal-body #dateofread").val(n.dateofread);
			$("#deleteNotificationModal .modal-body #okundu").prop('checked', n.okundu);
			$("#deleteNotificationModal .modal-body #smmmofis_id").val(n.smmmofis_id);
		});
	});
		/* delete Notification */
});	
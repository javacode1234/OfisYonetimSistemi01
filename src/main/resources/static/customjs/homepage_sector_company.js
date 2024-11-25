$("document").ready(function(){
	
		/* view modal portfoy company start*/
		$("table#companyTable #companyTableBody #viewBtn").click(function(e) {
			e.preventDefault();
			$("#viewCompanyModal .modal-title").text("Şirket Bilgileri");
			var href = $(this).attr('href');		
			$.get(href, function(company, status){
				$("#viewCompanyModal .modal-body #companyId").val(company.id);
				$("#viewCompanyModal .modal-body #companyName").val(company.name);
				$("#viewCompanyModal .modal-body #companyUnvan").val(company.unvan);
				$("#viewCompanyModal .modal-body #companyStartDate").val(company.startDate);
				$("#viewCompanyModal .modal-body #companyWebUrl").val(company.webUrl);
				$("#viewCompanyModal .modal-body #companyMainHeader").val(company.mainheader);
				$("#viewCompanyModal .modal-body #companyHeader").val(company.header);
				$("#viewCompanyModal .modal-body #companyDescription").val(company.description);
				$("#viewCompanyModal .modal-body #companyActive").prop('checked',company.active);
				$("#viewCompanyModal .modal-body #businesSectorId").val(company.businessector_id);
				$("#viewCompanyModal .modal-body #companyResim").attr('src','data:image/*;base64,'+company.stringResim);
			});
			
		});
		/* view modal portfoy company end  */
		/* edit modal portfoy company start*/
				$("table#companyTable #companyTableBody #editBtn").click(function(e) {
					e.preventDefault();
					$("#editCompanyModal .modal-title").text("Kurum Bilgilerini Güncelle");
					var href = $(this).attr('href');		
					$.get(href, function(company, status){
						$("#editCompanyModal .modal-body #editCompanyForm").attr('action', '/api/v1/update-homepage-portfoy-company-settings/'+company.id);
						$("#editCompanyModal .modal-body #companyId").val(company.id);
						$("#editCompanyModal .modal-body #companyName").val(company.name);
						$("#editCompanyModal .modal-body #companyUnvan").val(company.unvan);
						$("#editCompanyModal .modal-body #companyStartDate").val(company.startDate);
						$("#editCompanyModal .modal-body #companyWebUrl").val(company.webUrl);
						$("#editCompanyModal .modal-body #companyMainHeader").val(company.mainheader);
						$("#editCompanyModal .modal-body #companyHeader").val(company.header);
						$("#editCompanyModal .modal-body #companyDescription").val(company.description);
						$("#editCompanyModal .modal-body #companyActive").prop('checked', company.active);
						$("#editCompanyModal .modal-body #businesSectorId").val(company.businessector_id);
						$("#editCompanyModal .modal-body #companyResim").attr('src', 'data:image/*;base64,' + company.stringResim);

					});
						
				});
				/* edit modal portfoy company end  */
				
				/* edit portfoy company clear */
				$('#editCompanyModal #editCompanyForm #editCompanyFormClearBtn').click(function(e){
					e.preventDefault();
					$("#editCompanyModal .modal-body #companyName").val('');
					$("#editCompanyModal .modal-body #companyUnvan").val('');
					$("#editCompanyModal .modal-body #companyStartDate").val('');
					$("#editCompanyModal .modal-body #companyWebUrl").val('');
					$("#editCompanyModal .modal-body #companyMainHeader").val('');
					$("#editCompanyModal .modal-body #companyHeader").val('');
					$("#editCompanyModal .modal-body #companyDescription").val('');
					$("#editCompanyModal .modal-body #companyActive").prop('checked', false);
					$("#editCompanyModal .modal-body #businesSectorId").val('defaultSelected');
					$("#editCompanyModal .modal-body #companyResim").attr('src', '');
				});
				/* edit portfoy company clear */
				
				/* portfoy company delete modal sart */
				$('table#companyTable #companyTableBody #deleteBtn').click(function(e){
					e.preventDefault();
					var href = $(this).attr('href');
					$('#deleteCompanyModal #deleteConfirmBtn').attr('href',href);
					$('#deleteCompanyModal .modal-title').text('Kurum kaydı silinecek. Emin misin ?');
							
					var id = $(this).closest('tr').find('td').eq(0).text();
					$('#deleteCompanyModal #companyId').val(id);
					
					var name = $(this).closest('tr').find('td').eq(2).text();
					$('#deleteCompanyModal #companyName').val(name);
					var unvan = $(this).closest('tr').find('td').eq(3).text();
					$('#deleteCompanyModal #companyUnvan').val(unvan);
					
					var tarih = $(this).closest('tr').find('td').eq(4).text();
					$("#deleteCompanyModal .modal-body #companyStartDate").val(tarih);
					
					var webUrl = $(this).closest('tr').find('td').eq(5).text();
					$("#deleteCompanyModal .modal-body #companyWebUrl").val(webUrl);
					
					var mainheader = $(this).closest('tr').find('td').eq(6).text();
					$('#deleteCompanyModal #companyMainHeader').val(mainheader);
					var header = $(this).closest('tr').find('td').eq(7).text();
					$('#deleteCompanyModal #companyHeader').val(header);
					var description = $(this).closest('tr').find('td').eq(8).text();
					$('#deleteCompanyModal #companyDescription').val(description);
					var active = $(this).closest('tr').find('td:eq(9) span').text();
					if(active==="Aktif"){
						$('#deleteCompanyModal #companyActive').prop('checked', true);
					}else if(active==="Pasif"){
						$('#deleteCompanyModal #companyActive').prop('checked', false);
					}
					var businesSectorId=$(this).closest('tr').find('td').eq(10).text();					
					$("#deleteCompanyModal .modal-body #businesSectorId").val(businesSectorId);
							
					var resimhref = $(this).closest('tr').find('td:eq(1) a img').attr('src');
					$('#deleteCompanyModal #companyResim').attr('src',  resimhref.toString() );
															
				})
				/* portfoy company delete modal end */
						


/* addCompanyModal form change img satart */
	$("#addCompanyModal form#addCompanyForm #companyformImage").on('change', function(){
		readURLAdd(this);
	});
/* addCompanyModal form change img end */

/* editCompanyModal form change img satart */
	$("#editCompanyModal form#editCompanyForm #companyformImage").on('change', function(){
		readURLEdit(this);
	});
/* editCompanyModal form change img end */

});


/* addCompanyModal form change img satart */
function readURLAdd(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $("#addCompanyModal form#addCompanyForm #companyResim").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }	
}
/* addCompanyModal change img satart */

/* editCompanyModal form change img satart */
function readURLEdit(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $("#editCompanyModal form#editCompanyForm #companyResim").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }	
}
/* editCompanyModal change img satart */
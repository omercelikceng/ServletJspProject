function sendAjaxQueryForTask() {

	var postData = {
		taskName : $('#taskName').val(),
		taskDescription : $('#taskDescription').val(),
		taskDate : $('#taskDate').val()
	};

	$.ajax({
		url : 'taskController',
		method : 'POST',
		data : JSON.stringify(postData),
		scriptCharset: "UTF-8" ,
		contentType : "application/json; charset=UTF-8",
		mimeType: "application/json",
		dataType : "json",
		success : function(responseText) {
			location.reload();
		}

	});
}

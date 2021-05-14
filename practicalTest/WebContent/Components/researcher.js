$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
//SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateResearcherForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	

	var type = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
	//sending data to the DC-Bus
	$.ajax({
		url : "ResearcherAPI",
		type : type,
		data : $("#formRes").serialize(),
		dataType : "text",
		complete : function(response, status) {
			location.reload(true);
			onResearcherSaveComplete(response.responseText, status);
		}
	});

});
//UPDATE==========================================
$(document).on(	"click",".btnUpdate",function(event) {
	$("#hidResearcherIDSave").val($(this).data("researcherid"));
	 $("#researcherCode").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#researcherName").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#projectCode").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#location").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "ResearcherAPI",
		type : "DELETE",
		data : "researcherID=" + $(this).data("researcherid"),
		dataType : "text",
		complete : function(response, status) {
			location.reload(true);	
			onResearcherDeleteComplete(response.responseText, status);
		}
	});
	
});

function onResearcherSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divResearcherGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidResearcherIDSave").val("");
	$("#formRes")[0].reset();
}



function onResearcherDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divResearcherGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



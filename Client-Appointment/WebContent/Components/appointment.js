//Response-Alert  
$(document).ready(function() {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
//Valid Input Checker
function validateAppointmentForm() {

    

    if ($("#userID").val().trim() == "") {
        return "Enter User ID.";
    }
    if ($("#doctorID").val().trim() == "") {
        return "Enter Doctor ID";
    }
    if ($("#appointmentDate").val().trim() == "") {
        return "Enter Date";
    }
   
    if ($("#appointmentTime").val().trim() == "") {
        return "Enter Time";
    }
    
    if ($("#tokenNo").val().trim() == "") {
        return "Enter Token No.";
    }
    
    if ($("#payType").val().trim() == "") {
        return "Enter Payment Type.";
    }
    
    if ($("#amount").val().trim() == "") {
        return "Enter Amount.";
    }
    
    return true;
}
//Save
$(document).on("click", "#btnSave", function(event) {
    //Clear alerts
    $("#alertSuccess").text("");
    $("#alertError").text("");
    $("#alertSuccess").hide();
    $("alertError").hide();
    
    //Form validation
    var status = validateAppointmentForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    var method = ($("#hIDappointmentIDSave").val() == "") ? "POST" : "PUT";
    
    $.ajax({
        url: "AppointmentAPI",
        type: method,
        data: $("#formAppointment").serialize(),
        dataType: "text",
        complete: function(response, status) {
        	
            onAppointmentSaveSuccess(response.responseText, status);
        }
    });
});

function onAppointmentSaveSuccess(response, status) {
    if (status == "success") {
    	console.log("Res:p- "+response)
        var resultset = JSON.parse(response);
        
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Saved Successfully!");
            $("#alertSuccess").show;
            $("#divAppointmentGrid").html(resultset.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(resultset.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Saving Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Saving!");
        $("#alertError").show();
    }
    $("#hIDappointmentIDSave").val("");
    $("#formAppointment")[0].reset();
}
//Update Appointment
$(document).on("click", "#btnUpdate", function(event) {
	console.log("Update Button Invoked");
    $("#hIDappointmentIDSave").val($(this).closest("tr").find('#hidAppointmentIDUpdate').val());
    $("#userID").val($(this).closest("tr").find('td:eq(0)').text());
    $("#doctorID").val($(this).closest("tr").find('td:eq(1)').text());
    $("#appointmentDate").val($(this).closest("tr").find('td:eq(2)').text());
    $("#appointmentTime").val($(this).closest("tr").find('td:eq(3)').text());
    $("#tokenNo").val($(this).closest("tr").find('td:eq(4)').text());
    $("#payType").val($(this).closest("tr").find('td:eq(5)').text());
    $("#amount").val($(this).closest("tr").find('td:eq(6)').text());
});
//Remove Appointment
$(document).on("click", "#btnRemove", function(event) {
    $.ajax({
        url: "AppointmentAPI",
        type: "DELETE",
        data: "appointmentID=" + $(this).data("itemid"),
        dataType: "text",
        complete: function(response, status) {
            onAppointmentDeleteSuccess(response.responseText, status);
        }
    })
});

function onAppointmentDeleteSuccess(response, status) {
    if (status == "success") {
        var resultset = JSON.parse(response);
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Deleted Successfully!");
            $("#alertSuccess").show;
            $("#divAppointmentGrid").html(resultset.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(resultset.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Deleting Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Deleting!");
        $("#alertError").show();
    }
}
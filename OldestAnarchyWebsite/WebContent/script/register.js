function approve(){
	let password = document.getElementById("password").value;
	let good = document.getElementById("verify").value;
	if(good!=undefined && password===good){	

		document.getElementById("password").setCustomValidity("");
		document.getElementById("verify").setCustomValidity("");
		return true;
	}else{

		document.getElementById("password").setCustomValidity("");
		document.getElementById("verify").setCustomValidity("verify doesn't match password");
		return false;
	}
}

function sendToServer(){
	if(document.getElementById("form").checkValidity){
		window.alert("Thank you for registering");
	}
}


window.onload = function () {
    document.getElementById("form").oninput = approve;
    document.getElementById("password").oninput = approve;
    document.getElementById("verify").oninput = approve;
}


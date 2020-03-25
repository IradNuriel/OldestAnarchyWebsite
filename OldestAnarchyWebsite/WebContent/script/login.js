function approve(){
	let password = document.getElementById("password").value;
	let good = document.getElementById("good").value;
	if(good!=undefined && password===good){

		document.getElementById("password").setCostumValidity("");
		document.getElementById("good").setCostumValidity("");
		
		window.alert("Thank you for registering");
	}else{

		document.getElementById("password").setCostumValidity("");
		document.getElementById("good").setCostumValidity("verify doesn't match password");
		
	}
}




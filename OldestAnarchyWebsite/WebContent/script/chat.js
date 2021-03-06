
function sendMessage(senderName) {
	let msg = document.getElementById('text').value;
	let ajaxPostRequest = new XMLHttpRequest();
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	ajaxPostRequest.open("POST", "ChatServlet");
	ajaxPostRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxPostRequest.onreadystatechange=function(){
		if (this.readyState == 4 && this.status == 200) {
			if(document.getElementById('chat').innerHTML==undefined){
				document.getElementById('chat').innerHTML=this.responseText;
			}else{
				document.getElementById('chat').innerHTML=document.getElementById('chat').innerHTML+this.responseText;
			}
		}
	}
	ajaxPostRequest.send("senderName=" + senderName + "&info=" + msg);
	document.getElementById('text').value='';
}

function listenToNewMessages(userName) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {

			if(document.getElementById('chat').innerHTML==undefined){
				document.getElementById('chat').innerHTML=this.responseText;
			}else{
				document.getElementById('chat').innerHTML=document.getElementById('chat').innerHTML+this.responseText;
			}
			listenToNewMessages(userName);
		}
	};

	
	xhttp.open("GET", "ChatServlet", true);
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	xhttp.send("senderName="+userName);
}
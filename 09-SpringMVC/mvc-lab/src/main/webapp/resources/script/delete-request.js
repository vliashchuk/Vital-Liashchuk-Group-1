	function getXmlHttp(){
	  var xmlhttp;
	  try {
	    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  } catch (e) {
	    try {
	      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    } catch (E) {
	      xmlhttp = false;
	    }
	  }
	  if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
	    xmlhttp = new XMLHttpRequest();
	  }
	  return xmlhttp;
	}
	
	function sendDeleteRequest(url, returnUrl) {
		var xmlhttp = getXmlHttp()
		xmlhttp.open('DELETE', url, false);
		xmlhttp.send(null);
		window.location.href=returnUrl;

	}
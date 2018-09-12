function getStyle(object, att) {
	return window.getComputedStyle ? getComputedStyle(object)[att] : object.currentStyle[att];
}


function $(str){
	if(typeof str === 'string'){
		return document.getElementById(str);
	} else if(typeof str === 'function'){
		window.onload = str;
	}
}

function _loadScript(url, fn) {

	//检查资源是否已经被加载，防止重复加载，浪费资源
	if (K.options.resLoadCache[url]) {
		return;
	}
	var head = document.getElementsByTagName('head')[0] || (_QUIRKS ? document.body : document.documentElement),
		script = document.createElement('script');
	head.appendChild(script);
	K.options.resLoadCache[url] = 1;
	script.src = url;
	script.charset = 'utf-8';
	script.onload = script.onreadystatechange = function() {
		if (!this.readyState || this.readyState === 'loaded') {
			if (fn) {
				fn();
			}
			script.onload = script.onreadystatechange = null;
			head.removeChild(script);
		}
	};
}

// 移除URL里的GET参数
function _chopQuery(url) {
	var index = url.indexOf('?');
	return index > 0 ? url.substr(0, index) : url;
}

function _loadStyle(url) {

	//检查资源是否已经被加载，防止重复加载，浪费资源
	if (K.options.resLoadCache[url]) {
		return;
	}

	var head = document.getElementsByTagName('head')[0] || (_QUIRKS ? document.body : document.documentElement),
		link = document.createElement('link'),
		absoluteUrl = _chopQuery(_formatUrl(url, 'absolute'));
	var links = K('link[rel="stylesheet"]', head);
	for (var i = 0, len = links.length; i < len; i++) {
		if (_chopQuery(_formatUrl(links[i].href, 'absolute')) === absoluteUrl) {
			return;
		}
	}
	head.appendChild(link);
	K.options.resLoadCache[url] = 1;
	link.href = url;
	link.rel = 'stylesheet';
}

function _ajax(url, fn, method, param, dataType) {
	method = method || 'GET'; //POST or GET
	dataType = dataType || 'json'; //json or html
	var xhr = window.XMLHttpRequest ? new window.XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
	xhr.open(method, url, true);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			if (fn) {
				var data = _trim(xhr.responseText);
				if (dataType == 'json') {
					data = _json(data);
				}
				fn(data);
			}
		}
	};
	if (method == 'POST') {
		var params = [];
		_each(param, function(key, val) {
			params.push(encodeURIComponent(key) + '=' + encodeURIComponent(val));
		});
		try {
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		} catch (e) {}
		xhr.send(params.join('&'));
	} else {
		xhr.send(null);
	}
}

K.loadScript = _loadScript;
K.loadStyle = _loadStyle;
K.ajax = _ajax;

(function () {
    function Vipspa() {

    }

    Vipspa.prototype.start = function (config) {
        var self = this;
        self.routerMap = config.router;
        self.mainView = config.view;
        self.errorTemplateId = config.errorTemplateId;
        startRouter();
        window.onhashchange = function () {
            startRouter();
        };
    };
    var messageStack = [];
    // {
    //     'id': 'home_bindcard',
    //     'content': {
    //     }
    // }
    Vipspa.prototype.getMessage = function (id) {
        var msg = {};
        $.each(messageStack, function (i, e) {
            if (e.id === id) {
                msg = e;
            }
        });
        return msg;
    };
    Vipspa.prototype.setMessage = function (obj) {
        var _obj = JSON.parse(JSON.stringify(obj));
        $.each(messageStack, function (i, e) {
            if (e.id === _obj.id) {
                e = _obj;
                return false;
            }
        });
        messageStack.push(_obj);
    };
    Vipspa.prototype.delMessage = function (id) {
        if (typeof id === 'undefined') {
            return false;
        }
        var index = 0;
        $.each(messageStack, function (i, e) {
            if (e.id === id) {
                index = i;
            }
        });
        $.each(messageStack, function (i, e) {
            if (i > index) {
                messageStack[i - 1] = e;
            }
        });
    };
    Vipspa.prototype.clearMessage = function () {
        messageStack = [];
    };

    Vipspa.prototype.stringifyParam = function (routerUrl, paramObj) {
        var paramStr = '', hash;
        for (var i in  paramObj) {
            paramStr += i + '=' + encodeURIComponent(paramObj[i]) + '&';
        }
        if (paramStr === '') {
            hash = routerUrl;
        }
        else {
            paramStr = paramStr.substring(0, paramStr.length - 1);
            hash = routerUrl + '?' + paramStr;
        }
        return hash;
    };

    Vipspa.prototype.stringifyPattern = function (routerUrl, paramObj) {
        var paramStr = '', hash;
        for (var i = 0; i < paramObj.length; i++) {
            paramStr += paramObj[i] + '/';
        }
        if (paramStr === '') {
            hash = routerUrl;
        } else {
            paramStr = paramStr.substring(0, paramStr.length - 1);
            hash = routerUrl + '!!' + paramStr;
        }
        return hash;
    };

    Vipspa.prototype.parse = function (routerHash) {
        var hash = typeof routerHash === 'undefined' ? location.hash : routerHash;
        var obj = {
            url: '',
            param: {},
            rest: ''
        };
        var param = {}, url = '', rest = '';
        var pIndex = hash.indexOf('?');
        var restIndex = hash.indexOf('!!');
        if (hash === '') {
            return obj;
        }

        if (pIndex > -1 && restIndex > -1) {
            url = hash.substring(1, pIndex < restIndex ? pIndex : restIndex);
            var paramStr = hash.substring(pIndex + 1);
            var paramArr = paramStr.split('&');

            $.each(paramArr, function (i, e) {
                var item = e.split('='),
                    key,
                    val;
                key = item[0];
                val = item[1];
                if (key !== '') {
                    param[key] = decodeURIComponent(val);
                }
            });
        }
        else {
            if (restIndex <= -1)
                url = hash.substring(1);
            else
                url = hash.substring(1, restIndex);
            param = {};
        }

        if (restIndex > -1) {
            rest = hash.substring(restIndex + 2, hash.length);
        }
        return {
            url: url,
            param: param,
            rest: rest
        };
    };

    function routerAction(routeObj) {
        var routerItem = vipspa.routerMap[routeObj.url];
        if (typeof routerItem === 'undefined') {
            var defaultsRoute = vipspa.routerMap.defaults;
            routerItem = vipspa.routerMap[defaultsRoute];
            location.hash = defaultsRoute;
            return false;
        }
        var ajaxUrl = routerItem.templateUrl;
        if (typeof routeObj.rest !== 'undefined' && routeObj.rest !== '') {
            ajaxUrl = routerItem.templateUrl + '/' + routeObj.rest;
        }
        $.ajax({
            type: 'GET',
            url: ajaxUrl,
            cache: false,
            dataType: 'html',
            success: function (data, status, xhr) {
                var container = $(vipspa.mainView);
                container.css({
                    opacity: '0.0'
                }).html(data).delay(50).animate({
                    opacity: '1.0'
                }, 500);
                common.hashChange($);
                loadScript(routerItem.controller);
            },
            error: function (xhr, errorType, error) {
                if ($(vipspa.errorTemplateId).length === 0) {
                    return false;
                }
                var errHtml = $(vipspa.errorTemplateId).html();
                errHtml = errHtml.replace(/{{errStatus}}/, xhr.status);
                errHtml = errHtml.replace(/{{errContent}}/, xhr.responseText);
                $(vipspa.mainView).html(errHtml);
            }
        });
    }

    function startRouter() {
        var hash = location.hash;
        var routeObj = vipspa.parse(hash);
        routerAction(routeObj);
    }

    function loadScript(src, callback) {
        var script = document.createElement('script'),
            loaded;
        script.setAttribute('src', src);
        script.onreadystatechange = script.onload = function () {
            script.onreadystatechange = null;
            document.documentElement.removeChild(script);
            script = null;
            if (!loaded) {
                if (typeof callback === 'function')
                    callback();
            }
            loaded = true;
        };
        document.documentElement.appendChild(script);
    }

    window.vipspa = new Vipspa();
})();
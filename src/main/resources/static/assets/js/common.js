var common = {
    $: layui.$
    , layui: {}
    , status: {
        ok: 200
    }

    , url: {
        prefix: "/management"
        , manage_index: "/management/index"
    }

    , ajax: function (url, data, success) {
        layui.$.ajax({
            type: 'post'
            , dataType: 'json'
            , url: url
            , data: data
            , success: success
            , error: function () {
                layer.msg("发生未知错误！");
            }
        })
    }

    , okHandle: function (json, index, tableId, ok) {
        if (json.code === common.status.ok) {
            var okMsg = ok !== undefined ? ok : json.message;
            layer.msg(okMsg);
            if (index)
                layer.close(index);
            if (tableId)
                layui.table.reload(tableId);
        } else {
            layer.msg(json.message);
        }
    }

    , okMsgHandle: function (json, ok) {
        if (json.code === common.status.ok) {
            var okMsg = ok !== undefined ? ok : json.message;
            layer.msg(okMsg);
        } else {
            layer.msg(json.message);
        }
    }

    , dateFormatter: function (date, split, show) {
        var realLen = arguments.length;
        var symbol = realLen >= 2 ? arguments[1] : "-";
        var showTime = realLen === 3 ? arguments[2] : true;
        var resD = '';
        if (date instanceof Object) {
            resD = date.year + symbol
                + (date.monthValue < 10 ? ("0" + date.monthValue) : date.monthValue) + symbol
                + (date.dayOfMonth < 10 ? ("0" + date.dayOfMonth) : date.dayOfMonth) + " ";
            if (showTime) {
                if (date.hour >= 0 && date.minute >= 0 && date.second >= 0) {
                    resD += (date.hour < 10 ? ("0" + date.hour) : date.hour) + ":"
                        + (date.minute < 10 ? ("0" + date.minute) : date.minute) + ":"
                        + (date.second < 10 ? ("0" + date.second) : date.second);
                }
            }
            return resD;
        }
        else if (date !== null && date !== "") {
            var d = new Date(date);
            return d.toLocaleString()
        }
        return "不合法的日期";
    }

    , hashChange: function ($) {
        var hash = location.hash;
        var restIndex = hash.indexOf("!!");
        if (restIndex > -1) {
            hash = hash.substring(0, restIndex);
        }
        var $layuiThis = $("li.layui-nav-item a[href=" + hash + "]");
        if ($layuiThis !== undefined && $layuiThis.length > 0) {
            var $layItem = $layuiThis.last();
            $layItem.addClass("layui-this").css("color", "#ffffff");
            $(".layui-nav-itemed").removeClass("layui-nav-itemed");
            $layItem.parents("li.layui-nav-item").addClass("layui-nav-itemed");
            $layItem.addClass("layui-this")
        }
        $("li.layui-nav-item").click(function () {
            $(this).siblings("li").removeClass("layui-nav-itemed");
        })
    }

    , coffee4Me: function () {
        layer.open({
            type: 1,
            title: false,
            closeBtn: false,
            area: ['640px', '400px'],
            shade: 0.8,
            id: 'QRCODE_PAY',
            resize: false,
            shadeClose: true,
            btnAlign: 'c',
            moveType: 1,
            content: '<div style="padding: 60px; background-color: #5b6275; color: #fff; font-weight: 300;" class="layui-row">' +
            '<div class="layui-col-md6">' +
            '   <img src="/static/assets/img/alipay.png" style="width: 250px;height: 250px;">' +
            '   <p style="text-align: center;" class="layui-admin-mt10">支付宝</p>' +
            '</div> ' +
            '<div class="layui-col-md6">' +
            '   <img src="/static/assets/img/wechat.png" style="width: 250px;height: 250px;">' +
            '   <p style="text-align: center;" class="layui-admin-mt10">微信</p>' +
            '</div>' +
            '</div>'
        });
    }

    , stickyIt: function () {
        var agent = navigator.userAgent;
        var isNotWebkit = (agent.indexOf("Edge/") > -1) || (agent.indexOf("Firefox/") > -1);
        if (isNotWebkit) {
            if ($("#main-body")) {
                $("#main-body").find("div.layui-row.layui-col-space10").removeClass("animated fadeInUp");
            }
            if ($("#note-body")) {
                $("#note-body").removeClass("animated fadeInUp");
            }
        }
    }

    , sticky: function () {
        var listHeight = $(".layui-container>.layui-row>.layui-col-md9").outerHeight(true);
        var stickHeight = $(".layui-container #affix-side").parents(".layui-col-md3").outerHeight(true);
        return stickHeight < listHeight;
    }

    , getParam: function (paramName) {
        var name, value;
        var str = location.href;
        var num = str.indexOf("?");
        str = str.substr(num + 1);
        var arr = str.split("&");
        for (var i = 0; i < arr.length; i++) {
            num = arr[i].indexOf("=");
            if (num > 0) {
                name = arr[i].substring(0, num);
                if (name === paramName) {
                    value = arr[i].substr(num + 1);
                    return value;
                }
            }
        }
    }
    , setCookie: function (name, value) {
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }
    , getCookie: function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    , delCookie: function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
};

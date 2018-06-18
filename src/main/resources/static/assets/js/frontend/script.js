layui.config({
    base: '/static/assets/js/frontend/'
}).extend({
    clock: 'clock'
});

layui.use(['layer', 'laytpl', 'util', 'clock'], function () {
    common.layui.layer = layui.layer;
    var util = layui.util;
    var clock = layui.clock;

    clock.now();
    util.fixbar({
        bar1: true
        , click: function (type) {
            console.log(type);
            if (type === 'bar1') {
                location.href = "/message";
            }
        }
        , css: {right: 10, bottom: 25}
        , bgcolor: '#9F9F9F'

    });

});

$(function ($) {
    var $body = $("body");
    $body.on("click", ".layui-timeline-title", function () {
        var $this = $(this);
        var $timelineBody = $this.next(".timeline-body");
        $timelineBody.slideToggle()
    });
    $body.on("click", "#note-operate #expand", function () {
        $(".timeline-body").slideUp()
    });
    $body.on("click", "#note-operate #collaspan", function () {
        $(".timeline-body").slideDown()
    });

    $("#side-nav").click(function () {
        var $sideNav = $(".nav-header .layui-nav-side");
        if ($sideNav.css("width") !== "0px") {
            $sideNav.animate({
                width: "0"
            }, 200)
        } else {
            $sideNav.animate({
                width: "200px"
            }, 200);
            layer.open({
                type: 1
                , title: false
                , shade: [0.6, '#f8f8f8']
                , shadeClose: true
                , closeBtn: 0
            })

        }
    });
    $body.click(function () {
        var $sideNav = $(".nav-header .layui-nav-side");
        if ($sideNav.css("width") !== "0px") {
            $sideNav.animate({
                width: "0"
            }, 200)
        }
    });

    bottomNav();
    $(window).resize(function () {
        bottomNav();
    });

});

function bottomNav() {
    var bodyHeight = $("body").outerHeight(true);
    var windowHeight = $(window).outerHeight(true);
    var $bNav = $("div.layui-fluid.bottom-nav");
    if (bodyHeight < windowHeight) {
        $bNav.css({
            position: "fixed",
            width: "100%",
            bottom: 0
        })
    } else {
        $bNav.css({
            position: "",
            width: "",
            bottom: "'"
        })
    }
}
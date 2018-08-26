var util = require('../../utils/util.js')
var app = getApp();
Page({
  data: {
    postList: {},
    requestUrl: "",
    topList: {},
    page: 1,
    isEmpty: true,
  },

  onLoad: function () {
    var requestUrl = app.globalData.apiBase +
      "/api/wx/index";
    this.data.requestUrl = requestUrl;
    var dataUrl = requestUrl + "?page=0&limit=5";
    util.ajax(dataUrl, "get", null, this.processData)
    var topUrl = app.globalData.apiBase +
      "/api/wx/top";
    util.ajax(topUrl, "get", null, this.processTopData)
  },

  processData: function (indexData) {
    var totalData = {};
    //如果要绑定新加载的数据，那么需要同旧有的数据合并在一起
    if (!this.data.isEmpty) {
      totalData = this.data.postList.concat(indexData);
    }
    else {
      totalData = indexData;
      this.data.isEmpty = false;
    }
    this.setData({
      postList: totalData
    });

    this.data.page += 1;
    wx.hideNavigationBarLoading();
    wx.stopPullDownRefresh();
  },

  processTopData: function (data) {
    this.setData({
      topList: data
    });
  },

  onPostTap: function (event) {
    var id = event.currentTarget.dataset.id;
    var title = event.currentTarget.dataset.title;
    wx.navigateTo({
      url: "/pages/post-detail/post-detail?id=" + id + "&title=" + title
    })
  },

  onSwiperTap: function (event) {
    // target 和currentTarget
    // target指的是当前点击的组件 和currentTarget 指的是事件捕获的组件
    // target这里指的是image，而currentTarget指的是swiper
    var id = event.target.dataset.id;
    console.log(id);
    wx.navigateTo({
      url: "/pages/post-detail/post-detail?id=" + id + "&title=主页"
    })
  },

  onPullDownRefresh: function (event) {
    var refreshUrl = this.data.requestUrl +
      "?page=0&limit=5";
    this.data.techList = {};
    this.data.isEmpty = true;
    this.data.page = 1;
    util.ajax(refreshUrl, "get", null, this.processData);
    wx.showNavigationBarLoading();
  },

  // 上滑加载更多数据
  onReachBottom: function (event) {
    var nextUrl = this.data.requestUrl +
      "?page=" + this.data.page + "&limit=5";
    util.ajax(nextUrl, "get", null, this.processData)
    wx.showNavigationBarLoading()
  },
})
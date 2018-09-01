var util = require('../../utils/util.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    techList: {},
    requestUrl: "",
    page: 1,
    isEmpty: true,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var requestUrl = app.globalData.apiBase +
      "/api/wx/index";
    this.data.requestUrl = requestUrl;
    var dataUrl = requestUrl + "?page=0&limit=5";
    util.ajax(dataUrl, "get", null, this.processData)
  },

  processData: function (techData) {
    var totalData = {};
    //如果要绑定新加载的数据，那么需要同旧有的数据合并在一起
    if (!this.data.isEmpty) {
      totalData = this.data.techList.concat(techData);
    }
    else {
      totalData = techData;
      this.data.isEmpty = false;
    }
    this.setData({
      techList: totalData
    });

    this.data.page += 1;
    wx.hideNavigationBarLoading();
    wx.stopPullDownRefresh();
  },

  onTechTap: function (event) {
    var id = event.currentTarget.dataset.id;
    var title = event.currentTarget.dataset.title;
    wx.navigateTo({
      url: "/pages/post-detail/post-detail?id=" + id + "&title=" + title
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
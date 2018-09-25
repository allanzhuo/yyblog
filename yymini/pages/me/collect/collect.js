var app = getApp();
var util = require('../../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    collectList: {},
  },

  onDetail: function (event) {
    var id = event.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/post-detail/post-detail?id=" + id + "&title=我的收藏"
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.showLoading({
      title: '加载中'
    });
    var dataUrl = app.globalData.apiBase +
      "/api/wx/list/collect?userId=" + app.globalData.userId;
    util.ajax(dataUrl, "get", null, this.processData);
  },

  processData: function (data) {
    this.setData({
      collectList: data
    });
    wx.hideNavigationBarLoading();
    wx.stopPullDownRefresh();
    wx.hideLoading();
  },

  onLike: function (event) {
    var id = event.currentTarget.dataset.id;
    var collect = false;
    var dataUrl = app.globalData.apiBase +
      "/api/wx/collect?id=" + id + "&userId=" + app.globalData.userId + "&collect=" + collect;
    util.ajax(dataUrl, "get", null, this.processCollectData);
  },

  processCollectData: function (data) {
    var refreshUrl = app.globalData.apiBase +
      "/api/wx/list/collect?userId=" + app.globalData.userId;
    util.ajax(refreshUrl, "get", null, this.processData);
    wx.showToast({
      title: "取消成功",
      duration: 1000,
      icon: "success"
    })
  },

})
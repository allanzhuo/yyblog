var WxParse = require('../../wxParse/wxParse.js');
var util = require('../../utils/util.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    postData: {},
    navigateTitle: "",
    id: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.share) {
      this.setData({
        share: options.share
      });
    }
    var id = options.id;
    var title = options.title;
    this.data.navigateTitle = title;
    this.data.id = id;
    var dataUrl = app.globalData.apiBase +
      "/api/wx/" + id;
    util.ajax(dataUrl, "get", null, this.processData)
  },
  
  processData: function (detailData) {
    WxParse.wxParse('article', 'html', detailData.content, this);
    this.setData({
      postData: detailData
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.setNavigationBarTitle({
      title: this.data.navigateTitle
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: this.data.postData.title,
      path: '/pages/post-detail/post-detail?id=' + this.data.id + "&title=" + '小卖铺的老爷爷' + "&share=1" 
    }
  },
  /**
  * 回到首页(分享的时候)
  */
  onBackHome: function () {
    wx.reLaunch({
      url: '/pages/launch/launch?share=1"'
    })
  }
})
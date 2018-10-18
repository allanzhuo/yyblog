var util = require('../../../utils/util.js');
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        email: '',
        feedback: ''
    },
  onInputEmail(e) {
        this.setData({
            email: e.detail.value
        })
    },
    onInputTextarea(e) {
        this.setData({
            feedback: e.detail.value
        })
    },
    submit() {
        const contact = this.data.email
        const feedback = this.data.feedback
        if (!contact || !feedback) {
            wx.showToast({
                title: '内容为空',
            })
            return;
        } else {
          wx.showToast({
            title: '系统维护中，请方便先到GitHub反馈~',
            duration: 2000,
            icon: "none"
          })
          return;
        }
        var dataUrl = app.globalData.apiBase +
        "/api/wx/feedback";
        util.ajax(dataUrl, "post", this.data, this.processData);
    },

    processData: function () {
      wx.showToast({
        title: '提交反馈成功',
      })
      wx.hideNavigationBarLoading();
      wx.stopPullDownRefresh();
    },

})
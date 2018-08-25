var app = getApp();
Page({

  data: {
    userInfo: "",
  },

  onLoad(options) {
    let that = this;
    wx.getUserInfo({
      success: function(res) {
        app.globalData.userInfo = JSON.parse(res.rawData);
        that.setData({
          userInfo: app.globalData.userInfo
        });
        if (options.share) {
          that.login('auth');
        } else {
          that.login();
        }
      },
      fail: function(res) {
        console.log('授权失败');
      }
    });
  },

  direct() {
    let url = "/pages/posts/post";
    wx.switchTab({
      url,
    })
  },

  onGetUserInfo() {
    var that = this;
    wx.getSetting({
      success: function(res) {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function(res) {
              app.globalData.userInfo = res.userInfo;
              that.login('auth');
            },
            fail: function() {
              console.log('系统错误')
            }
          })
        } else {
          wx.showToast({
            title: "授权失败",
            duration: 1000,
            icon: "none"
          })
        }
      },
      fail: function() {
        console.log('获取用户信息失败');
      }
    })
  },

  login(auth) {
    let that = this;
    //调用微信登录接口
    wx.login({
      success: function(res) {
        wx.request({
          url: app.globalData.apiBase + '/api/wx/login?code=' + res.code + '&nickname=' + app.globalData.userInfo.nickName +
            '&avatar=' + app.globalData.userInfo.avatarUrl,
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            //userId
            if (res.data.code == 200) {
              app.globalData.userId = res.data.data;
              console.log('获取用户信息=' + res.data.data);
              if (auth == 'auth') {
                that.direct();
              } else {
                let timer = setTimeout(() => {
                  clearTimeout(timer)
                  that.direct()
                }, 1500)
              }
            }
          }
        })
      }
    })
  },
})
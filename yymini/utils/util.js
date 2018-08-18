function ajax(url, method, data, callBack) {
  wx.request({
    url: url,
    method: method,
    data: data,
    header: {
      "Content-Type": "json"
    },
    success: function (res) {
      callBack(res.data);
    },
    fail: function (error) {
      console.log(error)
    }
  })
}

module.exports = {
  ajax: ajax
}
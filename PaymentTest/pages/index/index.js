// pages/Main/Main.js
var app = getApp()
Page({
    data: {
        r: '',
    },
    onLoad: function (options) {
        console.log("程序启动")
    },
    scanCodeClick: function () {
        onlyFromCamera: true,
            wx.scanCode({
                //success使用箭头函数
                success: (res) => {
                    var _this = this;
                    console.log(res.result);
                    _this.setData({r: res.result});
                    wx.navigateTo({
                        url: '/pages/pay/pay?money=' + res.result
                    })
                },
                fail: function (res) {
                },
                complete: function (res) {
                }
            })
    },
})

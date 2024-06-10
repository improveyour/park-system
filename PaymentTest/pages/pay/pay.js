//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
        showPayPwdInput: false,  //是否展示密码输入层
        pwdVal: '',  //输入的密码
        payFocus: true, //文本框焦点
        money: '',
        plate: '',
    },
    onLoad: function (options) {
        console.log(options)
        console.log(options.money)
        console.log(options.plate)
        let that = this
        that.setData({
            money: options.money,
            plate: options.plate
        })
    },
    /**
     * 显示支付密码输入层
     */
    showInputLayer: function () {
        this.setData({showPayPwdInput: true, payFocus: true});
    },
    /**
     * 隐藏支付密码输入层
     */
    hidePayLayer: function () {
        var val = this.data.pwdVal;
        this.setData({showPayPwdInput: false, payFocus: false, pwdVal: ''}, function () {
            wx.showToast({
                title: '支付成功',
                icon: 'success',
                duration: 2000//持续的时间
            })
            this.send()
            wx.navigateTo({
                url: '/pages/index/index'
            })
        });

    },
    /**
     * 获取焦点
     */
    getFocus: function () {
        this.setData({payFocus: true});
    },
    /**
     * 输入密码监听
     */
    inputPwd: function (e) {
        this.setData({pwdVal: e.detail.value});
        if (e.detail.value == "123456") {
            this.hidePayLayer();
        }
    },
    click: function () {
        this.showInputLayer();
        var that = this;
        console.log(that.data.money)
        var moneyParam = that.data.money
        var plateParam = that.data.plate
        console.log(moneyParam)
        console.log(plateParam)
    },

    send: function () {
        var that = this;
        var moneyParam = that.data.money
        var plateParam = that.data.plate
        wx.request({
            data: {
                money: moneyParam,
                plate: plateParam
            },
            url: 'http://192.168.43.79:8080/park/pay',  //要访问的地址（win+R,然后输入ipconfig可查看自己电脑的Ip地址）
            method: 'GET',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
                var data = res.data
                console.log(data);
                var datasplit = data.split('&');
                console.log(datasplit[1]);

            },
            fail: function (res) {
                console.log(".....fail.....");
            }
        })
    }
})
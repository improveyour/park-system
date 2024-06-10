const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        login: {
            show: false,
            avatar: 'https://img0.baidu.com/it/u=3204281136,1911957924&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
        }
    },
    // 登录监听
    chooseAvatar(e) {
        this.setData({
            login: {
                show: true,
                avatar: e.detail.avatarUrl,
            }
        })
    },
    // 修改信息
    basicClick() {
        var loginStatus = this.data.login.show
        //初始化加载，先判断用户登录状态
        if (loginStatus == true) {
            wx.navigateTo({
                url: '/pages/changeInfo/changeInfo'
            })
        } else {
            // 如果没登录
            wx.showModal({
                title: '提示',
                content: '您还未登录，请先登录',
                success: function (res) {
                    if (res.confirm) {//这里是点击了确定以后
                        console.log('用户点击确定')
                    } else {//这里是点击了取消以后
                        console.log('用户点击取消')
                    }
                }
            })
        }
    },
    // 缴费记录
    searchInfo() {
        var loginStatus = this.data.login.show
        //初始化加载，先判断用户登录状态
        if (loginStatus == true) {
            wx.navigateTo({
                url: '/pages/getPayHistory/getPayHistory'
            })
        } else {
            // 如果没登录
            wx.showModal({
                title: '提示',
                content: '您还未登录，请先登录',
                success: function (res) {
                    if (res.confirm) {//这里是点击了确定以后
                        console.log('用户点击确定')
                    } else {//这里是点击了取消以后
                        console.log('用户点击取消')
                    }
                }
            })
        }
    },

    //绑定车牌
    changePlate() {
        var loginStatus = this.data.login.show
        //初始化加载，先判断用户登录状态
        if (loginStatus == true) {
            wx.navigateTo({
                url: '/pages/changePlate/changePlate'
            })
        } else {
            // 如果没登录
            wx.showModal({
                title: '提示',
                content: '您还未登录，请先登录',
                success: function (res) {
                    if (res.confirm) {//这里是点击了确定以后
                        console.log('用户点击确定')
                    } else {//这里是点击了取消以后
                        console.log('用户点击取消')
                    }
                }
            })
        }
    },

    // 退出监听
    exitClick() {
        let that = this;
        wx.showModal({
            title: '提示',
            content: '确定退出登录吗？',
            success(res) {
                if (res.confirm) {
                    that.setData({
                        login: {
                            show: false,
                            avatar: 'https://img0.baidu.com/it/u=3204281136,1911957924&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
                        }
                    })
                }
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})

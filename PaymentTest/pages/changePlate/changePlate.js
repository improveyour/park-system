Page({
    /**
     * 页面的初始数据
     */
    data: {
        // 对象数据
        phone: '18620703096',
        array: [{
            plate: '京KBT355'
        }, {
            plate: '粤A636YK'
        }]

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this
        wx.request({
            data: {
                phone: '18620703096'
            },
            url: 'http://192.168.43.79:8080/park/wxGetAllPlateByPhone',  //要访问的地址（win+R,然后输入ipconfig可查看自己电脑的Ip地址）
            method: 'GET',
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success: function (res) {
                var data = res.data
                console.log(data[0].carPlate)
                console.log(data[1].carPlate)

            },
            fail: function (res) {
                console.log(".....fail.....");
            }
        })
    },
    //获取输入的用户名
    inputPlate(e) {
        this.setData({
            plate: e.detail.value
        })
        console.log(this.data.plate)
    },

    //绑定按钮
    saveMessage: function () {
        var newPlate = this.data.plate;
        var newPhone = this.data.phone;
        var that = this
        console.log(newPlate)
        console.log(newPhone)
        wx.request({
            data: {
                plate: newPlate,
                phone: newPhone
            },
            url: 'http://192.168.1.212:8080/park/wxBindPlate',  //要访问的地址（win+R,然后输入ipconfig可查看自己电脑的Ip地址）
            method: 'GET',
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success: function (res) {
                wx.showModal({
                    title: '提示',
                    content: '确认修改？',
                    success: function (res) {
                        if (res.confirm) {//这里是点击了确定以后
                            //返回上一个页面
                            wx.showToast({
                                title: '操作成功！', // 标题
                                icon: 'success',  // 图标类型，默认success
                                duration: 1500  // 提示窗停留时间，默认1500ms
                            })
                            setTimeout(() => {
                                wx.navigateBack()
                            }, 1500)
                        } else {//这里是点击了取消以后
                            console.log('用户点击取消')
                        }
                    }
                })
            },
            fail: function (res) {
                console.log(".....fail.....");
            }
        })
    }
})

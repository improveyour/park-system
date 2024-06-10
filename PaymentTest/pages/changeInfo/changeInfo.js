Page({
    /**
     * 页面的初始数据
     */
    data: {
        // 对象数据
        username: '',
        password: '',
        phone: ''

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //发送get请求
        var that = this
        wx.request({
            data: {
                username: 'username',
                password: 'password',
                phone: '18620703096'
            },
            url: 'http://192.168.43.79:8080/park/wxGetInfo',  //要访问的地址（win+R,然后输入ipconfig可查看自己电脑的Ip地址）
            method: 'GET',
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success: function (res) {
                var p = res.data
                console.log(p);
                var data = p.split('&');
                that.setData({
                    //收到数据后使用decodeURIComponent()解码
                    username: data[1],
                    password: data[3],
                    phone: data[5]
                })
            },
            fail: function (res) {
                console.log(".....fail.....");
            }
        })
    },
    //获取输入的用户名
    inputUsername(e) {
        this.setData({
            username: e.detail.value
        })
        console.log(this.data.username)
    },

    //获取输入的密码
    inputPassword(e) {
        this.setData({
            password: e.detail.value
        })
        console.log(this.data.password)
    },

    //获取输入的电话
    inputPhone(e) {
        this.setData({
            phone: e.detail.value
        })
        console.log(this.data.phone)
    },

    //保存按钮
    saveMessage: function () {
        var newUsername = this.data.username;
        var newPassword = this.data.password;
        var newPhone = this.data.phone;

        var that = this
        wx.request({
            data: {
                username: newUsername,
                password: newPassword,
                phone: newPhone
            },
            url: 'http://192.168.1.212:8080/park/wxChangeInfo',  //要访问的地址（win+R,然后输入ipconfig可查看自己电脑的Ip地址）
            method: 'GET',
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success: function (res) {
                var p = res.data
                console.log(p);
                var data = p.split('&');
                that.setData({
                    //收到数据后使用decodeURIComponent()解码
                    username: data[1],
                    password: data[3],
                    phone: data[5]
                })
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

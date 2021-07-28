let vm_header = new Vue({
    el:"#vm_header",
    data:{
        categories:[],isLogin:false,wd:""
    },
    created:function () {
        //发出获取所有分类数据的请求
        axios.get("/selectcategory").then(function (response) {
            vm_header.categories = response.data;
        }).catch(function (err) {
            alert(err);
        })
        //发出判断是否登录过的请求
        axios.get("/checklogin").then(function (response) {
            //服务器返回true表示登录过  false没登录过
            vm_header.isLogin = response.data;
        }).catch(function (err) {
            alert(err);
        })
    },
    methods:{
        logout:function () {
            if (confirm("您确认退出登录吗?")){
                //发出退出登录的异步请求
                axios.get("/logout").then(function (response) {
                    location.reload();//刷新页面
                }).catch(function (err) {
                    alert(err);
                })
            }
        },
        findById:function (id) {
            axios.get("/findbycid?id="+id).then(function (response) {
                //把得到的数据给到页面中显示作品的数组
                vm_product.product_arr = response.data;
            }).catch(function (err) {
                alert(err);
            })
        },
        search:function (wd) {
            location.href="home.html?wd="+wd;
        }
    }
})
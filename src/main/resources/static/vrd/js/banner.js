let vm_banner = new Vue({
    el:"#myCarousel",
    data:{
        banners:[]
    },
    created:function () {
        //发出异步请求获取轮播图数据
        axios.get("/selectbanner").then(function (response) {
            vm_banner.banners = response.data;
        }).catch(function (err) {
            alert(err);
        })
    },
    methods:{
        deleteById:function (id) {
            if (!confirm("您确认删除此轮播图吗?")){
                return;
            }
            //向 /deletebanner?id=xxx发出请求
            axios.get("/deletebanner?id="+id).then(function (response) {
                location.reload();
            }).catch(function (err) {
                alert(err);
            })
        },
        upload:function () {
            //得到上传的数据  切记需要修改form表单的编码类型enctype
            let data = new FormData($("form")[0]);
            //发出异步请求
            axios.post("/uploadbanner",data).then(function (response) {
                location.reload();
            }).catch(function (err) {
                alert(err);
            })
        }
    }
})
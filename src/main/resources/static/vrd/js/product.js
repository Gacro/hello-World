let vm_product=new Vue({
    el:"#vm_product",
    data:{
        product_arr:[]
        ,view_arr:[]
        ,like_arr:[]
    },

    created:function () {
        let str=location.search;
        if (str.indexOf("wd")!=-1){
            let wd=str.split("=")[1];
            axios.get("/findbywd?wd="+wd).then(function (response) {
                    vm_product.product_arr=response.data;
            })
        }
        else if (str.indexOf("cid")!=-1){
           let cid=str.split("=")[1];
           axios.get("/findbycid?id="+cid).then(function (response) {
               vm_product.product_arr=response.data;
           }).catch(function (err) {
                alert(err);
           })
        }else {
            axios.get("/selectproduct").then(function (response) {
                vm_product.product_arr=response.data;

            }).catch(function (err) {
                alert(err);
            });


        }
        axios.get("/viewList").then(function (response) {
            vm_product.view_arr=response.data;
        }).catch(function (err) {
            alert(err);
        })

        axios.get("/likeList").then(function (response) {
            vm_product.like_arr=response.data;
        }).catch(function (err) {
            alert(err);
        })
    },
    updated:function () {//此方法当Vue中变量发生改变页面更新后执行
        $(".grid").imagesLoaded().progress(function () {
            $(".grid").masonry({
                itemSelector:".grid-item",
                columnWidth:210
            })
            $(".grid-item").hover(function () {

                 //得到鼠标移入div里面得蓝条div
                $(this).children("div").stop().fadeIn(500);
            },function () {
                $(this).children("div").stop().fadeOut(500);
            });
        });
    }

})
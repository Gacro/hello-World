let vm_detail=new Vue({
    el:"#vm_detail",
    data:{
        product:{},isLogin:false
    },
    created:function () {
        axios.get("/checklogin").then(function (response) {
          vm_detail.isLogin=response.data;
        }).catch(function (err) {
            alert(err);
        })
        //获取地址栏中得作品id
        let id=location.search.split("=")[1];
        axios.get("/selectbyid?id="+id).then(function (response) {
              vm_detail.product=response.data;
              //时间格式的转换
            vm_detail.product.created=moment(vm_detail.product.created).format("YYYY年MM月DD日  HH:mm:ss");

        }).catch(function (err) {
            alert(err);
        })
    },
    methods:{
        likeById:function (id) {
           axios.get("/likebyid?id="+id).then(function (response) {
               if (response.data==1){
                   vm_detail.product.likeCount++;
               }else {
                   alert("点过了不能点!");
               }

           }).catch(function (err) {
               alert(err);
           })
        },
        deleteById:function (id) {
          axios.get("/delete?id="+id).then(function (response) {
              if (!confirm("您确认删除么?")){
                  return;
              }
            location.href="home.html";
          }).catch(function (err) {
              alert(err);
          })
        }
    }
})
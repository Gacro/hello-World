package cn.tedu.vrd03.controller;

import cn.tedu.vrd03.entity.Product;
import cn.tedu.vrd03.mapper.ProductMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.HeadlessToolkit;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
public class ProductController {
    @Autowired(required = false)
    ProductMapper pMapper;
    @Value("${imageDirPath}")
    private String imageDirPath;

    @RequestMapping("/send")
    public void send(Product product, MultipartFile file){
        System.out.println("product = " + product + ", file = " + file);
        //得到文件的原始名
        String fileName=file.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        fileName= UUID.randomUUID()+suffix;
        System.out.println("唯一文件名"+fileName);
        SimpleDateFormat f=new SimpleDateFormat("/yyyy/MM/dd");
        Date date=new Date();
        String dataPath=f.format(date);
        System.out.println(dataPath);
        String path=imageDirPath+dataPath;
        File dirFile=new File(path);
        //如果不存在就创建文件夹
        if (!dirFile.exists()){
            dirFile.mkdirs(); //必须加s,因为是创建多个文件夹
        }
        try{
             file.transferTo(new File(path+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        product.setUrl(dataPath+fileName);
        product.setCreated(date);
        pMapper.insert(product);
    }
    @RequestMapping("/selectproduct")
    public List<Product>selectProduct(){
        return pMapper.selectAll();
    }
    @RequestMapping("/viewList")
    public List<Product> viewList(){ return pMapper.viewList();}
    @RequestMapping("/likeList")
    public List<Product>likeList(){return  pMapper.likeList();}
    @RequestMapping("/findbycid")
    public List<Product> findByCid(int id){
        return pMapper.findByCid(id);
    }
    @RequestMapping("/findbywd")
    public List<Product>findByWd(String wd){ return pMapper.findByWd(wd);}
    @RequestMapping("/selectbyid")
    public Product finndById(int id){
        pMapper.viewById(id);
        return pMapper.findById(id);
    }
    @RequestMapping("/likebyid")
    public int likeById(int id , HttpSession session){
        //从Session中取出曾经保存郭的id
        String likeId=(String)session.getAttribute("like"+id);
        if(likeId==null) {
            pMapper.likeById(id);
            session.setAttribute("like"+id,"like"+id);
            return 1;//代表没点过
        }
        return 2;//点过了,不能在点赞了
    }
    @RequestMapping("/delete")
    public void deleteById(int id){
        Product p=pMapper.findById(id);
        String filePath=imageDirPath+p.getUrl();
        new File(filePath).delete();
        pMapper.deleteById(id);
    }

}

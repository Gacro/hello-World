package cn.tedu.vrd03.controller;

import cn.tedu.vrd03.entity.Banner;
import cn.tedu.vrd03.entity.Category;
import cn.tedu.vrd03.entity.Product;
import cn.tedu.vrd03.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class BannerController {
    @Autowired(required = false)
    BannerMapper bMapper;
    @Value("${imageDirPath}")
    private String imageDirPath;
    @RequestMapping("/selectbanner")
    public List<Banner> selectbanner(){
        return bMapper.selectAll();
    }
    @RequestMapping("/deletebanner")
    public void deleteById(int id){
       String url=bMapper.findUrlById(id);
        String Path=imageDirPath+url;
        new File(Path).delete();
        bMapper.deleteById(id);
    }

    @RequestMapping("/uploadbanner")
    public void upload(Banner banner,MultipartFile file){
       String fileName=file.getOriginalFilename();
       String suffix=fileName.substring(fileName.lastIndexOf("."));
       fileName=UUID.randomUUID()+suffix;
       String path=imageDirPath+"/"+fileName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        banner.setUrl("/"+fileName);
        bMapper.insert(banner);

    }
    }


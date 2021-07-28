package cn.tedu.vrd03.controller;


import cn.tedu.vrd03.entity.Category;
import cn.tedu.vrd03.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired(required = false)
    CategoryMapper cMapper;
       @RequestMapping("/selectcategory")
    public List<Category> selectcategory(){
           return cMapper.selectAll();
       }
}

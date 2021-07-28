package cn.tedu.vrd03.mapper;

import cn.tedu.vrd03.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CategoryMapper {
    @Select("select id,name from category")
    List<Category> selectAll();
}

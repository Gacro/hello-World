package cn.tedu.vrd03.mapper;

import cn.tedu.vrd03.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
@Insert("insert into product values(null,#{title},#{author},#{intro},#{url},0,0,#{created},#{categoryId})")
void insert(Product product);
@Select("select id,title,url,viewCount,likeCount from product")
List<Product> selectAll();
@Select("select id,title,url,author,viewCount,likeCount  from product order by viewCount desc  limit 0,4")
List<Product> viewList();
@Select("select id,title,url,author,viewCount,likeCount  from product order by likeCount desc  limit 0,4")
List<Product> likeList();
    @Select("select id,title,url,viewCount,likeCount from product where categoryId=#{id}")
    List<Product> findByCid(int id);
    @Select("select id,title,url,viewCount,likeCount from product where title like concat('%',#{wd},'%')")
    List<Product> findByWd(String wd);
@Select("select id,title,author,intro,url,viewCount,likeCount,created from product where id=#{id}")
    Product findById(int id);
@Update("update product set likeCount=likeCount+1 where id=#{id}")
void likeById(int id);
    @Update("update product set viewCount=viewCount+1 where id=#{id}")
    void viewById(int id);
@Delete("delete from product where id=#{id}")
    int deleteById(int id);
}

package cn.tedu.vrd03.mapper;

import cn.tedu.vrd03.entity.Banner;
import cn.tedu.vrd03.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("select id,url from banner")
    List<Banner> selectAll();
    @Delete("delete from banner where id=#{id}")
    int deleteById(int id);
@Select("select url from banner where id=#{id}")
    String findUrlById(int id);
@Insert("insert into banner values(null,#{url})")
    void insert(Banner banner);
}

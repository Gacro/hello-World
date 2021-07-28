package cn.tedu.vrd03.mapper;

import cn.tedu.vrd03.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
@Select("select id,username,password from user where username=#{username}")
User login(String username);
}

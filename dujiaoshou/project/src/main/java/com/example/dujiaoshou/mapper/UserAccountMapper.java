package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAccountMapper {
  UserAccount findByUsername(@Param("username") String username);
  int insert(UserAccount u);
}

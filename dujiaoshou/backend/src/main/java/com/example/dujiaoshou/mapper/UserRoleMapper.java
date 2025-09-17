package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
  int insert(UserRole ur);
  List<UserRole> findByUser(@Param("userId") Long userId);
}

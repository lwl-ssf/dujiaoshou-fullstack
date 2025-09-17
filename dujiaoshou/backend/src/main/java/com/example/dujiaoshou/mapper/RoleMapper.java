package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
  Role findByName(@Param("name") String name);
  int insert(Role r);
}

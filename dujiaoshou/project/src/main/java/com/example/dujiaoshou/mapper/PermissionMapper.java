package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper {
  Permission findByCode(@Param("code") String code);
  int insert(Permission p);
}

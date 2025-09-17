package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.RolePermission;
import com.example.dujiaoshou.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
  int insert(RolePermission rp);
  List<RolePermission> findByRole(@Param("roleId") Long roleId);
}

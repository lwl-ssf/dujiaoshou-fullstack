package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
  int insert(Order o);
  Order findBySn(@Param("sn") String sn);
  Order findBySnForUpdate(@Param("sn") String sn);
  int update(Order o);
  List<Order> selectAll();
}

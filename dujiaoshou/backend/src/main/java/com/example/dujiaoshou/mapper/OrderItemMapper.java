package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
  int insert(OrderItem item);
}

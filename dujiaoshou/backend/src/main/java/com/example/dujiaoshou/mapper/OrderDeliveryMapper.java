package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.OrderDelivery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDeliveryMapper {
  int insert(OrderDelivery d);
  List<OrderDelivery> listByOrderId(Long orderId);
}

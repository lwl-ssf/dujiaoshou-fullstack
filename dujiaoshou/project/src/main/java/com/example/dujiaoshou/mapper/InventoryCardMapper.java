package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.InventoryCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InventoryCardMapper {
  InventoryCard selectFirstAvailableByProduct(@Param("productId") Long productId);
  InventoryCard selectFirstAvailableByProductForUpdate(@Param("productId") Long productId);
  long countByProductAndStatus(@Param("productId") Long productId, @Param("status") String status);
  int updateStatusAndOrder(@Param("id") Long id, @Param("status") String status, @Param("orderId") Long orderId);
  int insert(InventoryCard c);
  List<InventoryCard> findByOrderId(@Param("orderId") Long orderId);
}

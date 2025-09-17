package com.example.dujiaoshou.mapper;

import com.example.dujiaoshou.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
  List<Product> selectAll();
  Product selectById(Long id);
  int insert(Product p);
  int update(Product p);
  int delete(Long id);
}

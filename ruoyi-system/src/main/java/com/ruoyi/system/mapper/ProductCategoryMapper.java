package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface ProductCategoryMapper
{

    List<ProductCategory> selectProductCategoryList(Integer pageNum, Integer pageSize);

    @Options(useGeneratedKeys = true, keyProperty = "id")
//    @Insert("INSERT INTO product_category(name, index,status) VALUES(#{name}, #{index},#{status})")
    long insertProductCategory(ProductCategory productCategory);

    int updateProductCategory(ProductCategory productCategory);
}

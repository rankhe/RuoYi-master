package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ProductCategory;
import com.ruoyi.system.mapper.ProductCategoryMapper;
import com.ruoyi.system.mapper.ProductInfoMapper;
import com.ruoyi.system.service.IProductCategoryService;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductICategoryServiceImpl implements IProductCategoryService
{

    @Autowired ProductCategoryMapper productCategoryMapper;

    @Override public List<ProductCategory> selectProductCategoryList(Integer pageNum,Integer pageSize)
    {
        return productCategoryMapper.selectProductCategoryList(pageNum,pageSize);
    }

    @Override public Long createProductCategory(ProductCategory productCategory)
    {
        return productCategoryMapper.insertProductCategory(productCategory);
    }
}

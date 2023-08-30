package com.ruoyi.system.service;

import com.ruoyi.system.domain.ProductCategory;

import java.util.List;

public interface IProductCategoryService
{
    /**
     *
     * @return
     */
    List<ProductCategory> selectProductCategoryList(Integer pageNum,Integer pageSize);

    /**
     *
     * @param productCategory
     * @return
     */
    Long createProductCategory(ProductCategory productCategory);

}

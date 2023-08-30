package com.ruoyi.system.mapper;

import net.sf.ehcache.util.ProductInfo;

import java.util.List;

public interface ProductInfoMapper
{

    List<ProductInfo> selectProductInfoList(Long batchId);
}

package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.ProductInfoMapper;
import com.ruoyi.system.service.IProductInfoService;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements IProductInfoService
{

    @Autowired ProductInfoMapper productInfoMapper;

    @Override public List<ProductInfo> selectProductInfoList(Long batchId)
    {
        return productInfoMapper.selectProductInfoList(batchId);
    }
}

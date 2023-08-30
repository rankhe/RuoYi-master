package com.ruoyi.system.service;

import com.ruoyi.system.domain.ProductBatchInfo;

import java.util.List;

public interface IProductBatchInfoService
{
    /**
     * 产品融资信息
     *
     * @param ownerUserId 批次所属userId
     * @return 融资历史集合
     */
    public List<ProductBatchInfo> selectProductBatchInfoList(Long ownerUserId);
}

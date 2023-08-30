package com.ruoyi.system.service;

import com.ruoyi.system.domain.ProductQuotationInfo;

import java.util.List;

public interface IProductQuotationService
{
    /**
     * 产品融资信息
     *
     * @param batchId 产品批次号
     * @return 融资历史集合
     */
    public List<ProductQuotationInfo> selectProductQuotationInfoList(Long batchId);
}

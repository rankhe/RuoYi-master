package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ProductQuotationInfo;

import java.util.List;

public interface ProductQuotationInfoMapper
{

    List<ProductQuotationInfo> selectProductQuotationInfoList(Long userId,Long prodId,Long batchId);
}

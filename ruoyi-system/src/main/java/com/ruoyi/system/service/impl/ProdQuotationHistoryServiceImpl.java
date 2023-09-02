package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProdQuotationHistoryMapper;
import com.ruoyi.system.domain.ProdQuotationHistory;
import com.ruoyi.system.service.IProdQuotationHistoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品报价信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Service
public class ProdQuotationHistoryServiceImpl implements IProdQuotationHistoryService 
{
    @Autowired
    private ProdQuotationHistoryMapper prodQuotationHistoryMapper;

    /**
     * 查询产品报价信息
     * 
     * @param id 产品报价信息主键
     * @return 产品报价信息
     */
    @Override
    public ProdQuotationHistory selectProdQuotationHistoryById(Long id)
    {
        return prodQuotationHistoryMapper.selectProdQuotationHistoryById(id);
    }

    /**
     * 查询产品报价信息列表
     * 
     * @param prodQuotationHistory 产品报价信息
     * @return 产品报价信息
     */
    @Override
    public List<ProdQuotationHistory> selectProdQuotationHistoryList(ProdQuotationHistory prodQuotationHistory)
    {
        return prodQuotationHistoryMapper.selectProdQuotationHistoryList(prodQuotationHistory);
    }

    /**
     * 新增产品报价信息
     * 
     * @param prodQuotationHistory 产品报价信息
     * @return 结果
     */
    @Override
    public int insertProdQuotationHistory(ProdQuotationHistory prodQuotationHistory)
    {
        prodQuotationHistory.setCreateTime(DateUtils.getNowDate());
        return prodQuotationHistoryMapper.insertProdQuotationHistory(prodQuotationHistory);
    }

    /**
     * 修改产品报价信息
     * 
     * @param prodQuotationHistory 产品报价信息
     * @return 结果
     */
    @Override
    public int updateProdQuotationHistory(ProdQuotationHistory prodQuotationHistory)
    {
        prodQuotationHistory.setUpdateTime(DateUtils.getNowDate());
        return prodQuotationHistoryMapper.updateProdQuotationHistory(prodQuotationHistory);
    }

    /**
     * 批量删除产品报价信息
     * 
     * @param ids 需要删除的产品报价信息主键
     * @return 结果
     */
    @Override
    public int deleteProdQuotationHistoryByIds(String ids)
    {
        return prodQuotationHistoryMapper.deleteProdQuotationHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品报价信息信息
     * 
     * @param id 产品报价信息主键
     * @return 结果
     */
    @Override
    public int deleteProdQuotationHistoryById(Long id)
    {
        return prodQuotationHistoryMapper.deleteProdQuotationHistoryById(id);
    }
}

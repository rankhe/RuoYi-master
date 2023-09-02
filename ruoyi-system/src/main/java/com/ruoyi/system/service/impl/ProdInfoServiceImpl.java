package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProdInfoMapper;
import com.ruoyi.system.domain.ProdInfo;
import com.ruoyi.system.service.IProdInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Service
public class ProdInfoServiceImpl implements IProdInfoService 
{
    @Autowired
    private ProdInfoMapper prodInfoMapper;

    /**
     * 查询产品信息
     * 
     * @param id 产品信息主键
     * @return 产品信息
     */
    @Override
    public ProdInfo selectProdInfoById(Long id)
    {
        return prodInfoMapper.selectProdInfoById(id);
    }

    /**
     * 查询产品信息列表
     * 
     * @param prodInfo 产品信息
     * @return 产品信息
     */
    @Override
    public List<ProdInfo> selectProdInfoList(ProdInfo prodInfo)
    {
        return prodInfoMapper.selectProdInfoList(prodInfo);
    }

    /**
     * 新增产品信息
     * 
     * @param prodInfo 产品信息
     * @return 结果
     */
    @Override
    public int insertProdInfo(ProdInfo prodInfo)
    {
        prodInfo.setCreateTime(DateUtils.getNowDate());
        return prodInfoMapper.insertProdInfo(prodInfo);
    }

    /**
     * 修改产品信息
     * 
     * @param prodInfo 产品信息
     * @return 结果
     */
    @Override
    public int updateProdInfo(ProdInfo prodInfo)
    {
        prodInfo.setUpdateTime(DateUtils.getNowDate());
        return prodInfoMapper.updateProdInfo(prodInfo);
    }

    /**
     * 批量删除产品信息
     * 
     * @param ids 需要删除的产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProdInfoByIds(String ids)
    {
        return prodInfoMapper.deleteProdInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息信息
     * 
     * @param id 产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProdInfoById(Long id)
    {
        return prodInfoMapper.deleteProdInfoById(id);
    }
}

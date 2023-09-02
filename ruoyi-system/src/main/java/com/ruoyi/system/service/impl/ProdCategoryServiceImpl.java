package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProdCategoryMapper;
import com.ruoyi.system.domain.ProdCategory;
import com.ruoyi.system.service.IProdCategoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Service
public class ProdCategoryServiceImpl implements IProdCategoryService 
{
    @Autowired
    private ProdCategoryMapper prodCategoryMapper;

    /**
     * 查询产品分类
     * 
     * @param id 产品分类主键
     * @return 产品分类
     */
    @Override
    public ProdCategory selectProdCategoryById(Long id)
    {
        return prodCategoryMapper.selectProdCategoryById(id);
    }

    /**
     * 查询产品分类列表
     * 
     * @param prodCategory 产品分类
     * @return 产品分类
     */
    @Override
    public List<ProdCategory> selectProdCategoryList(ProdCategory prodCategory)
    {
        return prodCategoryMapper.selectProdCategoryList(prodCategory);
    }

    /**
     * 新增产品分类
     * 
     * @param prodCategory 产品分类
     * @return 结果
     */
    @Override
    public int insertProdCategory(ProdCategory prodCategory)
    {
        prodCategory.setCreateTime(DateUtils.getNowDate());
        return prodCategoryMapper.insertProdCategory(prodCategory);
    }

    /**
     * 修改产品分类
     * 
     * @param prodCategory 产品分类
     * @return 结果
     */
    @Override
    public int updateProdCategory(ProdCategory prodCategory)
    {
        prodCategory.setUpdateTime(DateUtils.getNowDate());
        return prodCategoryMapper.updateProdCategory(prodCategory);
    }

    /**
     * 批量删除产品分类
     * 
     * @param ids 需要删除的产品分类主键
     * @return 结果
     */
    @Override
    public int deleteProdCategoryByIds(String ids)
    {
        return prodCategoryMapper.deleteProdCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品分类信息
     * 
     * @param id 产品分类主键
     * @return 结果
     */
    @Override
    public int deleteProdCategoryById(Long id)
    {
        return prodCategoryMapper.deleteProdCategoryById(id);
    }
}

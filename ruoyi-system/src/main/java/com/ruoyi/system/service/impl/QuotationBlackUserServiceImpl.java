package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.enums.CommonStatus;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QuotationBlackUserMapper;
import com.ruoyi.system.domain.QuotationBlackUser;
import com.ruoyi.system.service.IQuotationBlackUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 报价黑名单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-07
 */
@Service
public class QuotationBlackUserServiceImpl implements IQuotationBlackUserService
{
    @Autowired
    private QuotationBlackUserMapper quotationBlackUserMapper;

    /**
     * 查询报价黑名单信息
     * 
     * @param id 报价黑名单信息主键
     * @return 报价黑名单信息
     */
    @Override
    public QuotationBlackUser selectQuotationBlackUserById(Long id)
    {
        return quotationBlackUserMapper.selectQuotationBlackUserById(id);
    }

    /**
     * 查询报价黑名单信息列表
     * 
     * @param quotationBlackUser 报价黑名单信息
     * @return 报价黑名单信息
     */
    @Override
    public List<QuotationBlackUser> selectQuotationBlackUserList(QuotationBlackUser quotationBlackUser)
    {
        return quotationBlackUserMapper.selectQuotationBlackUserList(quotationBlackUser);
    }

    /**
     * 新增报价黑名单信息
     * 
     * @param quotationBlackUser 报价黑名单信息
     * @return 结果
     */
    @Override
    public int insertQuotationBlackUser(QuotationBlackUser quotationBlackUser)
    {
        quotationBlackUser.setCreateTime(DateUtils.getNowDate());
        return quotationBlackUserMapper.insertQuotationBlackUser(quotationBlackUser);
    }

    /**
     * 修改报价黑名单信息
     * 
     * @param quotationBlackUser 报价黑名单信息
     * @return 结果
     */
    @Override
    public int updateQuotationBlackUser(QuotationBlackUser quotationBlackUser)
    {
        quotationBlackUser.setUpdateTime(DateUtils.getNowDate());
        return quotationBlackUserMapper.updateQuotationBlackUser(quotationBlackUser);
    }

    /**
     * 批量删除报价黑名单信息
     * 
     * @param ids 需要删除的报价黑名单信息主键
     * @return 结果
     */
    @Override
    public int deleteQuotationBlackUserByIds(String ids)
    {
        Arrays.stream(Convert.toLongArray(ids)).forEach(id -> {
                    QuotationBlackUser quotationBlackUser = quotationBlackUserMapper.selectQuotationBlackUserById(id);
                    quotationBlackUser.setStatus(CommonStatus.DEFAULT.getCode().intValue());
                    this.updateQuotationBlackUser(quotationBlackUser);
                }
        );
        return quotationBlackUserMapper.deleteQuotationBlackUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价黑名单信息信息
     * 
     * @param id 报价黑名单信息主键
     * @return 结果
     */
    @Override
    public int deleteQuotationBlackUserById(Long id)
    {
         QuotationBlackUser quotationBlackUser = quotationBlackUserMapper.selectQuotationBlackUserById(id);
         quotationBlackUser.setStatus(CommonStatus.DEFAULT.getCode().intValue());
         this.updateQuotationBlackUser(quotationBlackUser);        return quotationBlackUserMapper.deleteQuotationBlackUserById(id);
    }
}

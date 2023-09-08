package com.ruoyi.product.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品报价信息对象 prod_quotation_history
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
public class ProdQuotationHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品分类id */
    @Excel(name = "产品分类id")
    private Long prodId;

    /** 产品批次ID */
    @Excel(name = "产品批次ID")
    private Long batchId;

    /** 产品批次 */
    @Excel(name = "产品批次")
    private String batchNo;

    /** 报价人*/
    @Excel(name = "报价人")
    private Long quoterUserId;

    /** 报价人手机号 */
    @Excel(name = "报价人手机号")
    private Long quoterUserMobile;

    /** 放标人 */
    @Excel(name = "放标人")
    private Long ownerUserId;



    /** 报价人微信昵称 */
    @Excel(name = "报价人微信昵称")
    private String nickName;

    /** 意向数量 */
    @Excel(name = "意向数量")
    private Long count;

    /** 报单价 */
    @Excel(name = "报单价")
    private Long price;

    /** 状态1 正常 */
    @Excel(name = "状态")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProdId(Long prodId) 
    {
        this.prodId = prodId;
    }

    public Long getProdId() 
    {
        return prodId;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public Long getBatchId()
    {
        return batchId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice()
    {
        return price;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public Long getQuoterUserId()
    {
        return quoterUserId;
    }

    public void setQuoterUserId(Long quoterUserId)
    {
        this.quoterUserId = quoterUserId;
    }

    public Long getQuoterUserMobile()
    {
        return quoterUserMobile;
    }

    public void setQuoterUserMobile(Long quoterUserMobile)
    {
        this.quoterUserMobile = quoterUserMobile;
    }

    public Long getOwnerUserId()
    {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId)
    {
        this.ownerUserId = ownerUserId;
    }

    public void setBatchId(Long batchId)
    {
        this.batchId = batchId;
    }


    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("prodId", getProdId())
                .append("quoterUserId", getQuoterUserId())
                .append("ownerUserId", getOwnerUserId())
                .append("quoterUserMobile", getQuoterUserMobile())
                .append("batchId", getBatchId())
                .append("batchNo",getBatchNo())
                .append("nickName", getNickName())
                .append("count", getCount())
                .append("price", getPrice())
                .append("status", getStatus())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .toString();
    }
}
